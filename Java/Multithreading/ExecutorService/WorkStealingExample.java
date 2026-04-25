import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingExample {
    public static void main(String[] args) throws InterruptedException {
        
        // Creates a work-stealing pool using all available CPU cores
        ExecutorService executor = Executors.newWorkStealingPool();
        
        System.out.println("Available Cores: " + Runtime.getRuntime().availableProcessors());

        // Submit 10 independent tasks
        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Processing task " + taskNumber + " on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}