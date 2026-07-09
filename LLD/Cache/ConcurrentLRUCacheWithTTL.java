import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCacheWithTTL <K, V>{

    public static class Node<K, V>{
        final K key; 
        V value;
        long expiryTime;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value, long ttlMillis){
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        public boolean isExpired(){
            return System.currentTimeMillis() > expiryTime;
        }

    }

    private final ConcurrentHashMap<K, Node<K, V>> cacheMap;
    private final int capacity;

    private final ReentrantLock listLock = new ReentrantLock();

    // Dummy head and tail pointers for the LRU list
    private final Node<K, V> head;
    private final Node<K, V> tail;
    
    public ConcurrentLRUCacheWithTTL(int capacity){
        this.capacity = capacity;
        cacheMap = new ConcurrentHashMap<>(capacity);

        this.head = new Node<>(null, null, 0);
        this.tail = new Node<>(null, null, 0);
        this.head.expiryTime = Long.MAX_VALUE;
        this.tail.expiryTime = Long.MAX_VALUE;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private void removeNode(Node<K, V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node<K, V> node){
        node.next = head.next;
        node.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    private void evictLRU(){
        Node<K, V> lruNode = tail.prev;
        if(lruNode!=head){ // make sure that cache is not empty. 
            removeNode(lruNode);
            cacheMap.remove(lruNode.key);
        }
    }

    public void evictTTL(Node<K, V> node){
        listLock.lock();
        try{
            if(cacheMap.remove(node.key, node)){
                removeNode(node);
            }
        }finally{
            listLock.unlock();
        }
    }

    // Inserts or updates an entry in the cache with a specified TTL.
    public void put(K key, V value, long ttlMillis){ 
        Node<K, V> newNode = new Node<>(key, value, ttlMillis);

        listLock.lock();
        try{
            // get existing value is exists 
            Node<K, V> oldNode = cacheMap.put(key, newNode); // returns previously associated node
            if(oldNode!=null){
                removeNode(oldNode);
            }
            addToHead(newNode);

            if(cacheMap.size()>capacity){
                evictLRU();
            }

        } finally{
            listLock.unlock();
        }
    }

    // Retrieves an item from the cache + Evaluates expiration before returning.
    public V get(K key){
        Node<K, V> node = cacheMap.get(key);

        if(node == null) return null;

        if(node.isExpired()){
            evictTTL(node);
            return null;
        }

        listLock.lock();
        try{
            // Re-verify that the node wasn't concurrently removed
            if(cacheMap.get(key) == node){
                removeNode(node);
                addToHead(node);
            }
        } finally{
            listLock.unlock();
        }

        return node.value;
    }
    public static void main(String[] args) throws InterruptedException {
        
        ConcurrentLRUCacheWithTTL<String, String> cache = new ConcurrentLRUCacheWithTTL<>(2);

        System.out.println("=== Put A and B ===");
        cache.put("A", "Apple", 5000);
        cache.put("B", "Banana", 5000);

        System.out.println("A = " + cache.get("A")); // Apple
        System.out.println("B = " + cache.get("B")); // Banana

        System.out.println("\n=== Access A so it becomes MRU ===");
        cache.get("A");

        System.out.println("\n=== Insert C (should evict B) ===");
        cache.put("C", "Cherry", 5000);

        System.out.println("A = " + cache.get("A")); // Apple
        System.out.println("B = " + cache.get("B")); // null (evicted)
        System.out.println("C = " + cache.get("C")); // Cherry

        System.out.println("\n=== TTL Expiration Test ===");
        cache.put("D", "Date", 1000);

        System.out.println("D immediately = " + cache.get("D"));

        Thread.sleep(1500);

        System.out.println("D after TTL = " + cache.get("D")); // null

        System.out.println("\n=== Update Existing Key ===");
        cache.put("A", "Apricot", 5000);

        System.out.println("A = " + cache.get("A")); // Apricot
    }
    
}
