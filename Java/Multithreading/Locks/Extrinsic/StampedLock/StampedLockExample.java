import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private int count = 0;

    private final StampedLock lock = new StampedLock();
    
    public void increment(){
        long stamp = lock.writeLock();
        try{
            count++;
            System.out.println(Thread.currentThread().getName()+" count incremented to : "+ count);
        }finally{
            lock.unlock(stamp);
        }
    }

    public int getCounter(){
        long stamp = lock.tryOptimisticRead(); // Attempt an optimistic read
        int currentCount = count;

        // If a write occurred during the optimistic read, fall back to a pessimistic read lock
        if(!lock.validate(stamp)){
            stamp = lock.readLock(); // Acquire a read lock
            try{
                currentCount=count; // Read the value again under the read lock
            }finally{
                lock.unlock(stamp); // Release the read lock
            }
        }

        System.out.println(Thread.currentThread().getName() + " read count: " + currentCount);
        return currentCount;
    }

    public static void main(String args[]) throws InterruptedException{
        
        StampedLockExample counter = new StampedLockExample();

        // Create multiple writer threads
        Runnable writetask = () -> {
            for(int i=0;i<20;i++){
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

        tr1.join();
        tr2.join();
        tw1.join();
        tw2.join();

        System.out.println("Final count: "+ counter.count);
    }
}