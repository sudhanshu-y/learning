import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private int count = 0;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    
    public void increment(){
        writeLock.lock();
        try{
            count++;
            System.out.println(Thread.currentThread().getName()+" count incremented to : "+ count);
        }finally{
            writeLock.unlock();
        }
    }

    public int getCounter(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" count read : "+ count);
            return count;
        }finally{
            readLock.unlock();
        }
    }

    public static void main(String args[]) throws InterruptedException{
        
        ReentrantReadWriteLockExample counter = new ReentrantReadWriteLockExample();

        // Create multiple writer threads
        Runnable writetask = () -> {
            for(int i=0;i<10;i++){
                counter.increment();
            }
        };
        Thread tw1 = new Thread(writetask, "WriterThread-1");
        Thread tw2 = new Thread(writetask, "WriterThread-2");

        // Create multiple reader threads
        Runnable readTask = () -> {
            for(int i=0;i<5;i++){
                counter.getCounter();
            }
        };

        Thread tr1 = new Thread(readTask, "ReaderThread-1");
        Thread tr2 = new Thread(readTask, "ReaderThread-2");

        tw1.start();
        tw2.start();
        tr1.start();
        tr2.start();

        tw1.join();
        tw2.join();
        tr1.join();
        tr2.join();

        System.out.println("Final count: "+ counter.count);

    }
}