import java.util.concurrent.atomic.AtomicLong;

public class LeakyBucketRateLimiter {

    private final long capacity;            // Maximum bucket capacity
    private final long leakRate;            // Leak rate (requests per second)
    private final AtomicLong currentLevel;  // Current amount of water in bucket
    private long lastLeakTimestamp;         // Last time leakage was calculated

    public LeakyBucketRateLimiter(long capacity, long leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentLevel = new AtomicLong(0);
        this.lastLeakTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {

        // First leak old requests
        leak();

        // Check if bucket has capacity
        if (currentLevel.get() < capacity) {
            currentLevel.incrementAndGet();
            return true;
        }

        // Bucket full
        return false;
    }

    // Leak water based on elapsed time
    private void leak() {

        long now = System.currentTimeMillis();
        long timeElapsed = now - lastLeakTimestamp;

        // leakRate is requests/sec
        long leakedAmount = (timeElapsed * leakRate) / 1000; // ms to s

        if (leakedAmount > 0) {
            long newWaterLevel = Math.max(0,currentLevel.get() - leakedAmount);
            currentLevel.set(newWaterLevel);
            lastLeakTimestamp = now;
        }
    }

    public long getCurrentWaterLevel() {
        return currentLevel.get();
    }

    public static void main(String[] args) throws InterruptedException {

        // Capacity = 5 requests
        // Leak rate = 1 request/sec
        LeakyBucketRateLimiter limiter = new LeakyBucketRateLimiter(5, 1);

        for (int i = 1; i <= 15; i++) {

            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " -> " + (allowed ? "ALLOWED" : "REJECTED") + " | Current Water Level = " + limiter.getCurrentWaterLevel());

            // Incoming request every 200ms
            Thread.sleep(200);
        }
    }
}