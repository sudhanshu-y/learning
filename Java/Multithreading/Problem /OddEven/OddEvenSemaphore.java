import java.util.concurrent.Semaphore;

public class OddEvenSemaphore {

    private final int limit;
    private int n;
    private Semaphore oddTurn = new Semaphore(1);
    private Semaphore evenTurn = new Semaphore(0);

    public OddEvenSemaphore(int limit){
        this.limit = limit;
        this.n = 1;
    }

    public void odd() throws InterruptedException{
        while(n<=limit){
            oddTurn.acquire();
            // Because if n = 10 handled by even(), and another thread is already at while(n%2==0) of odd(). 
            // So it will print extra elemenet. 
            if(n>limit){
                oddTurn.release();
                break;
            }
            System.out.print(n++ +" ");
            evenTurn.release();
        }
    }

    public void even() throws InterruptedException{
        while(n<=limit){
            evenTurn.acquire();
            if(n>limit){
                evenTurn.release();
                break;
            }
            System.out.print(n++ +" ");
            oddTurn.release();
        }
    }
    
    public static void main(String[] args) {

        OddEvenSemaphore oe = new OddEvenSemaphore(10);

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
