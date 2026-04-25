import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {

        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int noOfRequests = 1000;

        Runnable task = () -> {
            counter.increment();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < noOfRequests; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Expected: " + noOfRequests);
        System.out.println("Actual:   " + counter.getCount());
    }
}