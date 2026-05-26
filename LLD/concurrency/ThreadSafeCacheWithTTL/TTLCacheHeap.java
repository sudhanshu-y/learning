import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TTLCacheHeap<K, V> {

    // K, V - both are required as we will be using ConcurrentHashMap, PriorityQueue both.
    // If item is added/removed --> handle in both.
    // So, in order to cordinatate with both we need K as well.
    private static class CacheEntry<K, V> {
        private final K key;
        private final V value;
        private final long expiryTime;

        public CacheEntry(K key, V value, long expiryTime) {
            this.key = key;
            this.value = value;
            this.expiryTime = expiryTime;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
        public long getExpiryTime() { return expiryTime; }
    }

    private final ConcurrentHashMap<K, CacheEntry<K, V>> map;
    private final PriorityQueue<CacheEntry<K, V>> minHeap;
    private final ScheduledExecutorService cleaner;

    public TTLCacheHeap(long cleanupIntervalMillis) {
        this.map = new ConcurrentHashMap<>();
        this.minHeap = new PriorityQueue<>(Comparator.comparingLong(CacheEntry::getExpiryTime));
        this.cleaner = Executors.newSingleThreadScheduledExecutor();

        cleaner.scheduleAtFixedRate(
                this::cleanup,
                cleanupIntervalMillis,
                cleanupIntervalMillis,
                TimeUnit.MILLISECONDS
        );
    }

    public void put(K key, V value, long ttlMillis) {
        long expiry = System.currentTimeMillis() + ttlMillis;

        CacheEntry<K, V> entry = new CacheEntry<>(key, value, expiry);

        map.put(key, entry);

        // PriorityQueue is not thread-safe
        synchronized (minHeap) {
            minHeap.offer(entry);
        }
    }

    public V get(K key) {
        CacheEntry<K, V> entry = map.get(key);
        if (entry == null) return null;

        long now = System.currentTimeMillis();

        if (entry.getExpiryTime() < now) {
            map.remove(key);
            return null;
        }

        return entry.getValue();
    }

    public void remove(K key) {
        map.remove(key);
        // Lazy Removal from Heap
        // Heap removal is handled into cleanup()
        // As heap removal is O(n), it causes performance. 
    }

    private void cleanup() {
        long now = System.currentTimeMillis();

        synchronized (minHeap) {
            // While because - multiple items may have already expired.
            while (!minHeap.isEmpty()) {
                CacheEntry<K, V> top = minHeap.peek();

                if (top.getExpiryTime() > now) break;

                minHeap.poll();
                CacheEntry<K, V> current = map.get(top.getKey());

                if (current != null && current.getExpiryTime() <= now) {
                    map.remove(top.getKey());
                }
            }
        }
    }

    public void shutdown() {
        cleaner.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {

        TTLCacheHeap<String, String> cache = new TTLCacheHeap<>(1000);

        System.out.println("Putting key1 with TTL 2 seconds...");
        cache.put("key1", "value1", 2000);

        System.out.println("Putting key2 with TTL 5 seconds...");
        cache.put("key2", "value2", 5000);

        System.out.println("Initial values:");
        System.out.println("key1: " + cache.get("key1"));
        System.out.println("key2: " + cache.get("key2"));

        Thread.sleep(3000);

        System.out.println("\nAfter 3 seconds:");
        System.out.println("key1: " + cache.get("key1")); // should be null
        System.out.println("key2: " + cache.get("key2")); // should still exist

        Thread.sleep(3000);

        System.out.println("\nAfter 6 seconds:");
        System.out.println("key2: " + cache.get("key2")); // should be null

        cache.shutdown();
    }
}