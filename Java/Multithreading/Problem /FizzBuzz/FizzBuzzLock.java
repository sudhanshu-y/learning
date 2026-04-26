import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzzLock {

    private int limit;
    private int n;
    private Lock lock;
    private Condition condition;

    public FizzBuzzLock(int limit) {
        this.limit = limit;
        this.n = 1;
        this.lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void fizz() throws InterruptedException {
        lock.lock();
        try {
            while (n <= limit) {
                while (n <= limit && !(n % 3 == 0 && n % 5 != 0)) {
                    condition.await();
                }
                if (n > limit) break;

                System.out.print("fizz ");
                n++;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void buzz() throws InterruptedException {
        lock.lock();
        try {
            while (n <= limit) {
                while (n <= limit && !(n % 5 == 0 && n % 3 != 0)) {
                    condition.await();
                }
                if (n > limit) break;

                System.out.print("buzz ");
                n++;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void fizzBuzz() throws InterruptedException {
        lock.lock();
        try {
            while (n <= limit) {
                while (n <= limit && !(n % 3 == 0 && n % 5 == 0)) {
                    condition.await();
                }
                if (n > limit) break;

                System.out.print("fizzbuzz ");
                n++;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void number() throws InterruptedException {
        lock.lock();
        try {
            while (n <= limit) {
                while (n <= limit && (n % 3 == 0 || n % 5 == 0)) {
                    condition.await();
                }
                if (n > limit) break;

                System.out.print(n + " ");
                n++;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FizzBuzzLock fb = new FizzBuzzLock(15);

        Thread t1 = new Thread(() -> {
            try {
                fb.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fb.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                fb.fizzBuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                fb.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}