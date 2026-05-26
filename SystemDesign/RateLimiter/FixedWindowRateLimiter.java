public class FixedWindowRateLimiter {
    private final long maxRequestsPerWindow;
    private final long windowSizeInMillis;

    private long requestCount;
    private long currentWindowStart;

    public FixedWindowRateLimiter(long maxRequestsPerWindow, long windowSizeInMillis){
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestCount = 0;
        this.currentWindowStart = System.currentTimeMillis();
    }

    // T(n) = O(1)
    // S(n) = O(1)
    public synchronized boolean allowRequest(){
        long now = System.currentTimeMillis();

        if(now - currentWindowStart >= windowSizeInMillis){
            requestCount = 0;
            currentWindowStart = now;
        }

        if(requestCount < maxRequestsPerWindow){
            requestCount++;
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {

        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(5, 10000);

        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " -> " + (allowed ? "ALLOWED" : "REJECTED"));
            Thread.sleep(1000);
        }
    }
}
