import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExample {

    // Used for generating unique IDs
    static AtomicLong idGenerator = new AtomicLong(1000);

    public static void main(String[] args) {

        Runnable task = () -> {
            long id = idGenerator.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " generated ID: " + id);
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
        /*
        Thread-0 generated ID: 1001
        Thread-1 generated ID: 1002
        Thread-2 generated ID: 1003
        Thread-4 generated ID: 1005
        Thread-3 generated ID: 1004
        */
    }
}