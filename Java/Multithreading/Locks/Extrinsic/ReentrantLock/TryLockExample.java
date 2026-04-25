import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

    private final Lock lock = new ReentrantLock();

    public void accessResource(String threadName) {
        if (lock.tryLock()) {
            try {
                System.out.println(threadName + " acquired the lock");
                Thread.sleep(2000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(threadName + " releasing the lock");
                lock.unlock();
            }
        } else {
            System.out.println(threadName + " could not acquire the lock");
        }
    }

    public static void main(String[] args) {

        TryLockExample example = new TryLockExample();

        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            example.accessResource(name);
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}