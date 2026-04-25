public class OddEvenSynchronizedFlag {

    private int n;
    private boolean oddTurn;

    public OddEvenSynchronizedFlag(int n){
        this.n = n;
        this.oddTurn = true;
    }

    public synchronized void odd() throws InterruptedException{
        for(int i=1;i<=n;i+=2){
            while (!oddTurn) wait();
            System.out.print(i+" ");
            oddTurn=!oddTurn;
            notifyAll();
        }
    }

    public synchronized void even() throws InterruptedException{
        for(int i=2;i<=n;i+=2){
            while (oddTurn) wait();
            System.out.print(i+" ");
            oddTurn=!oddTurn;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        
        OddEvenSynchronizedFlag oe = new OddEvenSynchronizedFlag(5);

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
