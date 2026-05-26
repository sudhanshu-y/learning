import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue;
    private int capacity;

    public BlockingQueue(int capacity){
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(T value) throws InterruptedException{
        while (queue.size()==capacity) {
            System.out.println("Blocked put");
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        while (queue.size()==0) {
            System.out.println("Blocked take");
            wait();
        }
        T value = queue.poll();
        notifyAll();
        return value;
    }

    public synchronized int size(){
        return queue.size();
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<>(3);

        Runnable producer = () -> {
            for(int i=0;i<10;i++){
                try {
                    bq.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(1000);
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}