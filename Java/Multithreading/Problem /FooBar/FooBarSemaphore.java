import java.util.concurrent.Semaphore;

public class FooBarSemaphore {

    private int n;
    private Semaphore fooTurn;
    private Semaphore barTurn;

    public FooBarSemaphore(int n){
        this.n=n;
        this.fooTurn = new Semaphore(1);
        this.barTurn = new Semaphore(0);
    }

    public void foo() throws InterruptedException {
        for(int i=0;i<n;i++){
            fooTurn.acquire();
            System.out.print("foo");
            barTurn.release();
        }
    }

    public void bar() throws InterruptedException {
        for(int i=0;i<n;i++){
            barTurn.acquire();
            System.out.print("bar");
            fooTurn.release();
        }
    }

    public static void main(String[] args) {

        FooBarSemaphore fb = new FooBarSemaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                fb.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fb.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        t1.start();
        t2.start();
    }
}