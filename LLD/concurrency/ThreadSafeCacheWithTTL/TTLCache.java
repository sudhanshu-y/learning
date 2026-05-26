import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TTLCache<K, V> {

    private static class CacheEntry<V> {
        private final V value;
        private final long expiryTime;

        public CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        public boolean isExpired(long now) {
            return now > expiryTime;
        }

        public V getValue() {
            return value;
        }
    }

    private final ConcurrentHashMap<K, CacheEntry<V>> cache;
    private final ScheduledExecutorService cleaner;

    public TTLCache(long cleanupIntervalMillis) {
        this.cache = new ConcurrentHashMap<>();
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
        cache.put(key, new CacheEntry<>(value, expiry));
    }

    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null) return null;

        long now = System.currentTimeMillis();

        if (entry.isExpired(now)) {
            cache.remove(key);
            return null;
        }

        return entry.getValue();
    }

    public void remove(K key) {
        cache.remove(key);
    }

    // Check each element and remove if expired.
    // Time Complexity: O(n)
    private void cleanup() {
        long now = System.currentTimeMillis();

        cache.forEach((key, entry) -> {
            if (entry != null && entry.isExpired(now)) {
                cache.remove(key);
            }
        });
    }

    // Used when application stops. 
    public void shutdown() {
        cleaner.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {

        TTLCache<String, String> cache = new TTLCache<>(2000);

        // Putting key1 with TTL 3 seconds...
        cache.put("key1", "value1", 3000);
        System.out.println("Immediately get: " + cache.get("key1"));

        Thread.sleep(4000);

        System.out.println("After 4 seconds: " + cache.get("key1"));

        cache.shutdown();
    }
}