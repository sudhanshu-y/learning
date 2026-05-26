public class SlidingWindowCounter {

    private final long maxRequestsPerWindow;
    private final long windowSizeInMillis;

    private long currentWindowCount;
    private long previousWindowCount;

    private long currentWindowStart;

    public SlidingWindowCounter(long maxRequestsPerWindow, long windowSizeInMillis) {
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.windowSizeInMillis = windowSizeInMillis;
        this.previousWindowCount = 0;
        this.currentWindowCount = 0;
        this.currentWindowStart = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(){
        long now = System.currentTimeMillis();
        long elapsedTime = now - currentWindowStart;

        // // Window rollover
        if(elapsedTime >= windowSizeInMillis){
            previousWindowCount = currentWindowCount;
            currentWindowCount = 0;
            currentWindowStart = now;
            elapsedTime = 0;
        }

        //// Calculate overlap ratio
        double overlapRatio = (double) (windowSizeInMillis - elapsedTime)/windowSizeInMillis;
        
        // Estimated rolling count
        double estimatedCount = currentWindowCount + previousWindowCount * overlapRatio;

        if(estimatedCount < maxRequestsPerWindow){
            currentWindowCount++;
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {

        SlidingWindowCounter limiter = new SlidingWindowCounter(5, 10000);

        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " -> " + (allowed ? "ALLOWED" : "REJECTED"));
            Thread.sleep(1000);
        }
    }
}
