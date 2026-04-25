import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterWithReentrantLock {

    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try{
            count++;
        }finally{
            lock.unlock();
        }
    }

    public int getCount(){
        return this.count;
    }
    
    public static void main(String[] args) throws InterruptedException {

        CounterWithReentrantLock counter = new CounterWithReentrantLock();

        Runnable task = () -> {
            for(int i=0;i<2000;i++){
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count (with lock): " + counter.getCount());
    }
}

