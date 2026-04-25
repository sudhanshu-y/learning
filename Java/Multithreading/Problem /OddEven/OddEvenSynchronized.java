public class OddEvenSynchronized {

    private int limit;
    private int n;

    public OddEvenSynchronized(int limit){
        this.limit = limit;
        this.n = 1;
    }

    public synchronized void odd() throws InterruptedException{
        while(n<=limit){
            while(n%2==0){
                wait();
            }
            // because if n = 10 handled by even(), and another thread is already at while(n%2==0) of odd(). 
            // So it will print extra elemenet. 
            if(n<=limit){
                System.out.print(n+" ");
                n++;
                notifyAll();
            }
        }
    }

    public synchronized void even() throws InterruptedException{
        while(n<=limit){
            while(n%2!=0){
                wait();
            }
            if(n<=limit){
                System.out.print(n+" ");
                n++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        OddEvenSynchronized oe = new OddEvenSynchronized(10);

        Thread t1 = new Thread(() -> {
            try {
                oe.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                oe.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        
    }
}
