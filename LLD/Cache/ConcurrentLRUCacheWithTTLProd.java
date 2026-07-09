import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCacheWithTTLProd<K, V> {

    static class Node<K, V> {
        final K key;
        V value;

        long expiryTime;

        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value, long ttlMillis){
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired(){
            return System.currentTimeMillis() > expiryTime;
        }

    }

    private ConcurrentHashMap<K, Node<K, V>> cache;
    private final int capacity;

    private final Node<K, V> head;
    private final Node<K, V> tail;

    private final ReentrantLock lock;

    private final PriorityQueue<Node<K, V>> expiryQueue;
    private final ScheduledExecutorService executorService;

    public ConcurrentLRUCacheWithTTLProd(int capacity){
        this.capacity = capacity;
        cache = new ConcurrentHashMap<>(capacity);

        head = new Node<K, V>(null, null, 0);
        tail = new Node<K, V>(null, null, 0);
        head.expiryTime = Long.MAX_VALUE;
        tail.expiryTime = Long.MAX_VALUE;
        head.next = tail;
        tail.prev = head;

        lock = new ReentrantLock();

        expiryQueue = new PriorityQueue<>(Comparator.comparingLong(node -> node.expiryTime));

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::cleanupExpiredNodes, 1, 1, TimeUnit.SECONDS);

    }

    public void removeNode(Node<K, V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addToHead(Node<K, V> node){
        node.next = head.next;
        node.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    public void evictLRU(){
        Node<K, V> lru = tail.prev;
        if(lru!=head){
            removeNode(lru);
            cache.remove(lru.key, lru);
        }
    }

    public void evictTTL(Node<K, V> node){
        lock.lock();
        try{
            if(cache.remove(node.key, node)){
                removeNode(node);
            }
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value, long ttlMillis){
        Node<K, V> newNode = new Node<>(key, value, ttlMillis);
        

        lock.lock();
        try{
            Node<K, V> oldNode = cache.put(key, newNode);

            if(oldNode!=null){
                removeNode(oldNode);
            }
            addToHead(newNode);

            expiryQueue.offer(newNode);

            if(cache.size() > capacity){
                evictLRU();
            }

        } finally{
            lock.unlock();
        }
    }

    public V get(K key){
        Node<K, V> node = cache.get(key);

        if(node == null){
            return null;
        }

        if(node.isExpired()){
            evictTTL(node);
            return null;
        }

        lock.lock();
        try{
            if (cache.get(key) == node) {
                removeNode(node);
                addToHead(node);
            }
        } finally {
            lock.unlock();
        }


        return node.value;
    }

    public void cleanupExpiredNodes(){
        // long now = System.currentTimeMillis();
        lock.lock();
        try{
            while(!expiryQueue.isEmpty()){
                Node<K, V> peek = expiryQueue.peek();
                // since sorted, next nodes will also be not expired.
                if(!peek.isExpired()){
                    break;
                }
                expiryQueue.poll();
                if(cache.remove(peek.key, peek)){
                    removeNode(peek);
                    System.out.println("TTL Evicted: " + peek.key);
                }
            }
        }finally{
            lock.unlock();
        }
    }

    public void shutdown(){
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLRUCacheWithTTLProd<String, String> cache = new ConcurrentLRUCacheWithTTLProd<>(2);

        System.out.println("=== Put A ===");
        cache.put("A", "Apple", 3000);

        System.out.println("\n=== Put B ===");
        cache.put("B", "Banana", 5000);

        System.out.println("\n=== Access A ===");
        System.out.println("A = " + cache.get("A"));

        System.out.println("\n=== Put C (should evict B) ===");
        cache.put("C", "Cherry", 5000);

        System.out.println("\nB = " + cache.get("B")); // null
        System.out.println("A = " + cache.get("A"));
        System.out.println("C = " + cache.get("C"));

        System.out.println("\nWaiting 4 seconds...");

        Thread.sleep(4000);


        System.out.println("A = " + cache.get("A"));
        System.out.println("C = " + cache.get("C"));

        cache.shutdown();
    }
}
