import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimmiterQueue {

    private final BlockingQueue<Integer> bucket;
    private final int capacity;
    private final ScheduledExecutorService leakScheduler;

    public LeakyBucketRateLimmiterQueue(int capacity, int leakRatePerSecond) {
        this.capacity = capacity;
        this.bucket = new ArrayBlockingQueue<>(capacity);
        this.leakScheduler = Executors.newSingleThreadScheduledExecutor();

        // Start the "leaking" process at fixed intervals
        long leakIntervalMs = 1000 / leakRatePerSecond;

        leakScheduler.scheduleAtFixedRate(
                this::leak,
                0,
                leakIntervalMs,
                TimeUnit.MILLISECONDS
        );
    }

    /**
     * Tries to add a request to the bucket.
     * Returns true if successful, false if the bucket is full (overflow).
     */
    public boolean tryConsume() {
        // offer() is thread-safe and non-blocking
        return bucket.offer(1);
    }

    private void leak() {
        // Removes the head of the bucket,
        // simulating processing a request
        Integer removed = bucket.poll();

        if (removed != null) {
            System.out.println("Leaked one request | Current Bucket Size = "+ bucket.size());
        }
    }

    public void shutdown() {
        leakScheduler.shutdown();
    }

    public int getCurrentSize() {
        return bucket.size();
    }

    public static void main(String[] args)
            throws InterruptedException {

        // capacity = 5
        // leak rate = 2 requests/sec
        LeakyBucketRateLimmiterQueue limiter = new LeakyBucketRateLimmiterQueue(5, 2);

        for (int i = 1; i <= 15; i++) {

            boolean allowed = limiter.tryConsume();
            System.out.println("Request " + i + " -> " + (allowed ? "ACCEPTED" : "REJECTED") + " | Bucket Size = " + limiter.getCurrentSize());

            // Simulate burst traffic
            Thread.sleep(100);
        }

        // Wait to observe leaking
        Thread.sleep(5000);

        limiter.shutdown();
    }
}