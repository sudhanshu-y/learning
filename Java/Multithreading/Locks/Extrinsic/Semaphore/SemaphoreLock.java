import java.util.concurrent.Semaphore;

public class SemaphoreLock {
    public static void main(String[] args) {
        // Only 2 slots available
        Semaphore slot = new Semaphore(2);

        Runnable task = () -> {
            try {
                slot.acquire(); // Grab a chair
                System.out.println(Thread.currentThread().getName() + " is working.");
                Thread.sleep(1000); 
                System.out.println(Thread.currentThread().getName() + " is leaving.");
                slot.release(); // Leave the chair
            } catch (InterruptedException e) {}
        };

        // Start 4 threads (only 2 can work at a time)
        for (int i = 0; i < 4; i++) 
            new Thread(task).start();
    }
}
