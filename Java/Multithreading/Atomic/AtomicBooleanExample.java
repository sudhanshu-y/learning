import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    // Flag used to ensure initialization happens only once
    static AtomicBoolean initialized = new AtomicBoolean(false);

    public static void main(String[] args) {

        Runnable task = () -> {
            if (initialized.compareAndSet(false, true)) {
                System.out.println(Thread.currentThread().getName() + " performed initialization.");
            } else {
                System.out.println(Thread.currentThread().getName() + " skipped initialization.");
            }
        };

        for (int i = 0; i < 4; i++) {
            new Thread(task).start();
        }
        /*
        Thread-0 skipped initialization.
        Thread-1 skipped initialization.
        Thread-2 performed initialization.
        Thread-3 skipped initialization.
        */
    }
}