import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogRateLimiter {

    private final long maxRequestsPerWindow;
    private final long windowSizeInMillis;
    private final Queue<Long> requestLog;

    public SlidingWindowLogRateLimiter(long maxRequestsPerWindow, long windowSizeInMillis){
        this.maxRequestsPerWindow = maxRequestsPerWindow; 
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestLog = new LinkedList<>();
    }

    // T(n) = O(n)
    // S(n) = O(noOfRequests)
    public synchronized boolean allowRequest(){
        long now = System.currentTimeMillis();
        long windowStart = now - windowSizeInMillis;

        // Remove timestamps that are outside of the current window
        while (!requestLog.isEmpty() && requestLog.peek() <= windowStart) {
            requestLog.poll();
        }

        if(requestLog.size() < maxRequestsPerWindow){
            requestLog.offer(now);
            return true;
        }

        return false; // exceeded the limit for this window
    }

    public static void main(String[] args) throws InterruptedException {

        SlidingWindowLogRateLimiter limiter = new SlidingWindowLogRateLimiter(5, 10000);

        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " -> " + (allowed ? "ALLOWED" : "REJECTED"));
            Thread.sleep(1000);
        }
    }
}
