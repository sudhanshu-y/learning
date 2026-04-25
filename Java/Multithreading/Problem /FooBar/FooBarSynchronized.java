public class FooBarSynchronized {

    private int n;
    private boolean flagFooTurn;

    public FooBarSynchronized(int n){
        this.n=n;
        this.flagFooTurn=true;
    }

    // flag = false --> print foo
    public synchronized void foo() throws InterruptedException{
        for(int i=0;i<n;i++){
            // if not foo turn --> Wait
            while(!flagFooTurn){
                wait();
            }
            System.out.print("foo");
            flagFooTurn=!flagFooTurn;
            notifyAll();
        }
    }

    // flag = true --> print bar
    public synchronized void bar() throws InterruptedException{
        for(int i=0;i<n;i++){
            // if not bar turn --> Wait
            while(flagFooTurn){
                wait();
            }
            System.out.print("bar");
            flagFooTurn=!flagFooTurn;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        FooBarSynchronized fb = new FooBarSynchronized(5);

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