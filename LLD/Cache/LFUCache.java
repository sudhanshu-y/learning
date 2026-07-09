import java.util.HashMap;

public class LFUCache<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        int freq = 1;

        Node<K, V> next = null;
        Node<K, V> prev = null;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoublyLinkedList<K, V> {
        Node<K, V> head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);

            head.next = tail;
            tail.prev = head;
        }

        void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.next.prev = node;

            head.next = node;
            node.prev = head;

            size++;
        }

        void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        // LRU eviction
        Node<K, V> removeFromTail() {
            if (size <= 0) return null;

            Node<K, V> lruNode = tail.prev;
            removeNode(lruNode);
            return lruNode;
        }
    }

    private final int capacity;
    private int minFreq;
    private final HashMap<K, Node<K, V>> cache;
    private final HashMap<Integer, DoublyLinkedList<K, V>> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<>(capacity);
        this.freqMap = new HashMap<>();
    }

    private void updateFrequency(Node<K, V> node) {
        int oldFreq = node.freq;
        DoublyLinkedList<K, V> oldDoublyLinkedList = freqMap.get(oldFreq);
        oldDoublyLinkedList.removeNode(node);

        /*
        * 'minFreq' tracks the absolute lowest frequency currently occupying the cache.
        * If the node we just upgraded belonged to that lowest frequency bucket (oldFreq == minFreq),
        * AND it was the last standing node in that bucket (oldDoublyLinkedList.size == 0),
        * then that specific frequency bucket is now entirely vacant.
        *
        * Because this node's frequency is scaling up to 'minFreq + 1', and no other nodes 
        * exist at 'minFreq', the new lowest occupied frequency in the entire cache is 
        * guaranteed to shift up by exactly 1.
        */
        if (oldFreq == minFreq && oldDoublyLinkedList.size == 0) {
            minFreq++; 
        }

        node.freq++;
        // Get the new higher frequency list (or create it if it's the first time anyone reached it)
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList<>()).addToHead(node);
    }

    public void put(K key, V value) {
        if (capacity <= 0) return;

        // Node already exists
        Node<K, V> existing = cache.get(key);
        if (existing != null) {
            existing.value = value;
            updateFrequency(existing);
            return;
        }

        // if capacity reached
        if (cache.size() >= capacity) {
            DoublyLinkedList<K, V> minFreqList = freqMap.get(minFreq);
            Node<K, V> removedNode = minFreqList.removeFromTail();
            if (removedNode != null) {
                cache.remove(removedNode.key);
            }
        }

        // Add new node
        Node<K, V> newNode = new Node<>(key, value);
        cache.put(key, newNode);
        minFreq = 1;
        freqMap.computeIfAbsent(newNode.freq, k -> new DoublyLinkedList<>()).addToHead(newNode);
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        updateFrequency(node);
        return node.value;
    }

    public static void main(String[] args) {
        System.out.println("--- Starting LFU Cache Testing ---");
        
        // Initialize a cache with capacity 2
        LFUCache<Integer, String> lfu = new LFUCache<>(2);

        // 1. Fill cache up to capacity
        lfu.put(1, "Value One"); // [1 (freq=1)]
        lfu.put(2, "Value Two"); // [2 (freq=1)], [1 (freq=1)]

        // 2. Access key 1 multiple times to raise its frequency
        System.out.println("Get 1: " + lfu.get(1)); // Expected: Value One (freq of 1 becomes 2)
        System.out.println("Get 1: " + lfu.get(1)); // Expected: Value One (freq of 1 becomes 3)

        // At this stage:
        // Key 1 has frequency 3
        // Key 2 has frequency 1

        // 3. Add a new key (Key 3). Since Key 2 has lower frequency than Key 1, Key 2 must be evicted.
        System.out.println("Putting key 3...");
        lfu.put(3, "Value Three"); 

        System.out.println("Get 1 (Popular): " + lfu.get(1)); // Expected: Value One
        System.out.println("Get 2 (Evicted): " + lfu.get(2)); // Expected: null
        System.out.println("Get 3 (New):     " + lfu.get(3)); // Expected: Value Three

        System.out.println("\n--- Testing LRU Tie-Breaker ---");
        
        // Clear and restart test for frequency ties
        LFUCache<Integer, String> tieCache = new LFUCache<>(2);
        tieCache.put(10, "A"); // [10 (freq=1)]
        tieCache.put(20, "B"); // [20 (freq=1)], [10 (freq=1)]

        // Make both equal frequency by reading them once
        tieCache.get(10); // freq = 2
        tieCache.get(20); // freq = 2
        
        // Right now, frequency tie: 10 (freq=2), 20 (freq=2). 
        // However, 20 was accessed *more recently* than 10. 10 is the LRU item.
        
        System.out.println("Putting key 30 (Forces eviction on tie)...");
        tieCache.put(30, "C");

        System.out.println("Get 10 (Oldest tie evicted): " + tieCache.get(10)); // Expected: null
        System.out.println("Get 20 (Preserved):          " + tieCache.get(20)); // Expected: B
        System.out.println("Get 30 (New):                 " + tieCache.get(30)); // Expected: C
    }
}