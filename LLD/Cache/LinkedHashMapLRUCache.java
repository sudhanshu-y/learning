import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LinkedHashMapLRUCache(int capacity) {
        // LinkedHashMap(initialCapacity, loadFactor, accessOrder)
        // accessOrder = true --> accessOrder
        // accessOrder = false --> insertionOder
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // called every time put is called. 
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
    // interface: Map --> in that another interface: Entry<K, V>
    // size() method is there in HashMap<K, V>.
    // LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> extends HashMap<K, V> : size()

    public static void main(String[] args) {

        LinkedHashMapLRUCache<Integer, String> cache = new LinkedHashMapLRUCache<>(2);

        System.out.println("=== Put 1, 2 ===");
        cache.put(1, "One");
        cache.put(2, "Two");
        System.out.println(cache);

        System.out.println("\n=== Access 1 ===");
        cache.get(1);
        System.out.println(cache);

        System.out.println("\n=== Put 3 ===");
        cache.put(3, "Three");
        System.out.println(cache);

        System.out.println("\nContains 1: " + cache.containsKey(1));
        System.out.println("Contains 2: " + cache.containsKey(2));
        System.out.println("Contains 3: " + cache.containsKey(3));
    }
}