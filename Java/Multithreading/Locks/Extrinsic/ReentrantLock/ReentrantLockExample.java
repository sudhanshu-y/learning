import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CounterProdReentrantLock {

    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();   // first lock
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock in increment()");
            add();
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing lock in increment()");
            lock.unlock();   // first unlock
        }
    }

    private void add() {
        lock.lock();   // SAME thread locking again
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock again in add()");
            count++;
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing lock in add()");
            lock.unlock();   // second unlock
        }
    }

    public int getCount() {
        return count;
    }
}

public class ReentrantLockExample {

    public static void main(String[] args) throws InterruptedException {

        CounterProdReentrantLock counter = new CounterProdReentrantLock();

        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}