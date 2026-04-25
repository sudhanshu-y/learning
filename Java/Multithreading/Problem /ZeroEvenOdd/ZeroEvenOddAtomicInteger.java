import java.util.concurrent.atomic.AtomicInteger;

public class ZeroEvenOddAtomicInteger {

    private int n;
    private AtomicInteger status = new AtomicInteger(0);
    // status = 0 ==> zero turn
    // status = 1 ==> odd turn
    // status = 2 ==> even turn

    public ZeroEvenOddAtomicInteger(int n) {
        this.n = n;
    }

    public void zero() {
        for (int i = 1; i <= n; i++) {
            while (status.get() != 0) {
                Thread.yield();
            }

            System.out.print(0);

            if (i % 2 == 0) {
                status.set(2);
            } else {
                status.set(1);
            }
        }
    }

    public void odd() {
        for (int i = 1; i <= n; i += 2) {
            while (status.get() != 1) {
                Thread.yield();
            }

            System.out.print(i);
            status.set(0);
        }
    }

    public void even() {
        for (int i = 2; i <= n; i += 2) {
            while (status.get() != 2) {
                Thread.yield();
            }

            System.out.print(i);
            status.set(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ZeroEvenOddAtomicInteger zeo = new ZeroEvenOddAtomicInteger(5);

        Thread t1 = new Thread(() -> zeo.zero());
        Thread t2 = new Thread(() -> zeo.odd());
        Thread t3 = new Thread(zeo::even);

        t1.start();
        t2.start();
        t3.start();
    }
}