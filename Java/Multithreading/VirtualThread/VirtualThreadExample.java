import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExample {

    public static void main(String[] args) throws Exception {

        // Using Thread.startVirtualThread()
        Thread vt1 = Thread.startVirtualThread(() -> {
            System.out.println("Virtual Thread 1 running: " + Thread.currentThread());
        });
        vt1.join();

        // Using Thread.ofVirtual().start()
        Thread vt2 = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual Thread 2 running: " + Thread.currentThread());
        });
        vt2.join();

        // Using ExecutorService with virtual threads
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 5; i++) {
                int taskId = i;

                executor.submit(() -> {
                    System.out.println(
                        "Executor Virtual Thread Task " + taskId + " running: " + Thread.currentThread()
                    );
                });
            }
        }

        System.out.println("Main thread finished");
    }
}
