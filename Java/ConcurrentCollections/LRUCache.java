import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;
    
    public LRUCache(int capacity){
        // super(initialCapacity, loadFactor, accessOrder)
        super(capacity, 0.75f, true);
        this.capacity=capacity;
    }

    public V getValue(K key){
        return super.getOrDefault(key, null);
    }

    public void putValue(K key, V value){
        super.put(key, value);
    }

    public boolean removeEldestEntry(Map.Entry<K, V> eldest){
        // This method is called by put() and putAll()
        // It tells the map to remove the oldest entry when size exceeds capacity.
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.putValue(1, "A");
        cache.putValue(2, "B");
        cache.putValue(3, "C");

        cache.getValue(1); // Access 1

        cache.putValue(4, "D"); // Evicts key 2

        System.out.println(cache);
    }
}
