import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    private Semaphore fooSem = new Semaphore(1);
    private Semaphore barSem = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooSem.acquire();
            System.out.print("foo");
            barSem.release();
        }
    }

    public void bar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barSem.acquire();
            System.out.print("bar");
            fooSem.release();
        }
    }
}

public class Main {
    public static void main(String[] args) {

        FooBar fb = new FooBar(5);

        Thread t1 = new Thread(() -> {
            try {
                fb.foo();
            } catch (InterruptedException e) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                fb.bar();
            } catch (InterruptedException e) {}
        });

        t1.start();
        t2.start();
    }
}