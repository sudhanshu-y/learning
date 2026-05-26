import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueReentrantLock<T> {

    private Queue<T> queue;
    private int capacity;

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public BlockingQueueReentrantLock(int capacity){
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(T value) throws InterruptedException{
        lock.lock();
        try{
            while (queue.size()==capacity) {
                System.out.println("Blocked put");
                full.await();
            }
            queue.add(value);
            empty.signal();
        } finally{
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try{
            while (queue.isEmpty()) {
                empty.await();
            }
            T value = queue.poll();
            full.signal();
            return value;
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueueReentrantLock<Integer> bq = new BlockingQueueReentrantLock<>(3);

        Runnable producer = () -> {
            for(int i=0;i<10;i++){
                try {
                    bq.put(i);
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable consumer = () -> {
            while (true) {
                Integer value;
                try {
                    value = bq.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}