import java.util.HashMap;

public class LRUCache<K, V> {

    static class Node<K, V> {
        final K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<K, Node<K, V>> cache;
    final private int capacity;

    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache(int capacity){
        cache = new HashMap<>(capacity);
        this.capacity = capacity;

        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        this.head.next = this.tail;
        this.tail.prev = this.head;
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
        Node<K, V> lruNode = tail.prev;
        if(lruNode!=head){
            removeNode(lruNode);
            cache.remove(lruNode.key);
        }
    }

    public void put(K key, V value){
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> oldNode = cache.put(key, newNode);

        if(oldNode!=null){
            removeNode(oldNode);
        }
        addToHead(newNode);

        if(cache.size() > capacity){
            evictLRU();
        }

    }

    public V get(K key){
        Node<K, V> node = cache.get(key);
        
        if(node==null){
            return null;
        }

        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public static void main(String[] args) {

        LRUCache<String, String> cache = new LRUCache<>(2);

        System.out.println("=== Put A ===");
        cache.put("A", "Apple");

        System.out.println("\n=== Put B ===");
        cache.put("B", "Banana");

        System.out.println("\n=== Get A (A becomes MRU) ===");
        System.out.println("A = " + cache.get("A"));

        System.out.println("\n=== Put C (should evict B) ===");
        cache.put("C", "Cherry");

        System.out.println("\n=== Verify Values ===");
        System.out.println("A = " + cache.get("A"));
        System.out.println("B = " + cache.get("B")); // null
        System.out.println("C = " + cache.get("C"));

        System.out.println("\n=== Update Existing Key A ===");
        cache.put("A", "Apricot");

        System.out.println("A = " + cache.get("A"));
    }
}
