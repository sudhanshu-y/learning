// Token Bucket ==> Bucket Capacity, Refill Rate
// tokens = tokens + refill_rate * time_passed

// The bucket doesn’t refill continuously in the background.
// It refills only when a request comes in

public class TokenBucketRateLimiter {

   private final long capacity;
   private final double refillRatePerMillis;

   private double currentTokens;
   private long lastRefillTimeInMillis;

   public TokenBucketRateLimiter(long capacity, double refillRatePerMillis) {
       this.capacity = capacity;
       this.refillRatePerMillis = refillRatePerMillis;
       this.currentTokens = capacity;
       this.lastRefillTimeInMillis = System.currentTimeMillis();
   }

   private void refill() {
       long now = System.currentTimeMillis();

       // Ensure bucket never exceeds capacity
       currentTokens = Math.min(
               capacity,
               currentTokens + (now - lastRefillTimeInMillis) * refillRatePerMillis
       );

       lastRefillTimeInMillis = now;
   }

   public synchronized boolean allowRequest() {
       refill();

       if (currentTokens >= 1) {
           currentTokens--;
           return true;
       }

       return false;
   }

   public static void main(String[] args) throws InterruptedException {

       // capacity = 5 tokens, refill = 1 token per 100 ms
       TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 0.25);

       for (int i = 1; i <= 15; i++) {
           boolean allowed = limiter.allowRequest();
           System.out.println("Request " + i + " → " + (allowed ? "ALLOWED" : "BLOCKED"));
           Thread.sleep(1);
       }
   }
}