import java.util.concurrent.Semaphore;

public class ZeroEvenOddSemaphore {

    private final int n;
    private Semaphore zeroTurn;
    private Semaphore oddTurn;
    private Semaphore evenTurn;

    public ZeroEvenOddSemaphore(int n) {
        this.n = n;
        this.zeroTurn = new Semaphore(1);
        this.oddTurn = new Semaphore(0);
        this.evenTurn = new Semaphore(0);
    }

    public void zero() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroTurn.acquire();
            System.out.print(0);

            if (i % 2 == 0) {
                evenTurn.release();
            } else {
                oddTurn.release();
            }
        }
    }

    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddTurn.acquire();
            System.out.print(i);
            zeroTurn.release();
        }
    }

    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenTurn.acquire();
            System.out.print(i);
            zeroTurn.release();
        }
    }

    public static void main(String[] args) {

        ZeroEvenOddSemaphore zeo = new ZeroEvenOddSemaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                zeo.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                zeo.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                zeo.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}