import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenAtomicInteger {

    private int n;
    private AtomicInteger status;

    public OddEvenAtomicInteger(int n) {
        this.n = n;
        status = new AtomicInteger(1);
    }

    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (status.get() != 1) {
                Thread.yield();
            }
            System.out.print(i + " ");
            status.set(2);
        }
    }

    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (status.get() != 2) {
                Thread.yield();
            }
            System.out.print(i + " ");
            status.set(1);
        }
    }

    public static void main(String[] args) {

        OddEvenAtomicInteger oe = new OddEvenAtomicInteger(10);

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