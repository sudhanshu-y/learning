import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCacheWithTTL<K, V> {

    private static class CacheEntry<V>{
        V value;
        long expiryTime;

        public CacheEntry(V value, long ttlMillis){
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        private boolean isExpired(){
            return System.currentTimeMillis() > this.expiryTime;
        }
    }

    private final LinkedHashMap<K, CacheEntry<V>> cache;
    private final int capacity;

    public LinkedHashMapLRUCacheWithTTL(int capacity){
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, CacheEntry<V>>(capacity, 0.75f, true){
            @Override
            public boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> entry){
                return size() > LinkedHashMapLRUCacheWithTTL.this.capacity;
            }
        };
    }

    public void put(K key, V value, long ttlMillis){
        this.cache.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public V get(K key){
        CacheEntry<V> entry = cache.get(key);

        if(entry == null){
            return null;
        }

        if(entry.isExpired()){
            cache.remove(key);
            return null;
        }

        return entry.value;
    }

    public static void main(String[] args)
            throws InterruptedException {

        LinkedHashMapLRUCacheWithTTL<Integer, String> cache = new LinkedHashMapLRUCacheWithTTL<>(2);

        cache.put(1, "One", 5000);
        cache.put(2, "Two", 5000);

        System.out.println(cache.get(1));

        cache.put(3, "Three", 5000);

        System.out.println(cache.get(2)); // null (LRU)

        cache.put(4, "Four", 1000);

        System.out.println(cache.get(4));

        Thread.sleep(1500);

        System.out.println(cache.get(4)); // null (TTL)
    }
}
