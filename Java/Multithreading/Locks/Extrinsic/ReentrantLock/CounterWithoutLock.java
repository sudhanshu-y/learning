public class CounterWithoutLock {

    private int count = 0;

    public void increment(){
        count++;
    }

    public int getCount(){
        return this.count;
    }
    public static void main(String[] args) throws InterruptedException {

        CounterWithoutLock counter = new CounterWithoutLock();

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

        System.out.println("Final count (without lock): " + counter.getCount());
    }
}