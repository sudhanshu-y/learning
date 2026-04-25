import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Create a simple thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // RUNNABLE TASK: No return value
        Runnable myRunnable = () -> {
            try {
                System.out.println("Runnable is running on: " + Thread.currentThread().getName());
                Thread.sleep(500); // Checked exception must be caught here
                System.out.println("Runnable finished. (No result returned)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // CALLABLE TASK: Returns a value
        Callable<Integer> myCallable = () -> {
            System.out.println("Callable is running on: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return 42;
        };

        try {
            System.out.println("Submitting tasks...");
            
            // execute() takes a Runnable. It returns nothing.
            executor.execute(myRunnable); 
            
            // submit() takes a Callable. It returns a Future object.
            Future<Integer> futureResult = executor.submit(myCallable);

            // Wait for the Callable to finish and get the result
            // .get() will block the main thread until the Callable is done
            Integer result = futureResult.get(); 
            System.out.println("Callable returned result: " + result);

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
