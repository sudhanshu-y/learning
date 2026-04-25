public class FooFooBarSynchronized {

    private int n;
    private int state;
    // state = 0, 1 --> Foo
    // state = 2 --> Bar

    public FooFooBarSynchronized(int n){
        this.n= n;
        this.state=0;
    }

    public synchronized void foo() throws InterruptedException{
        for(int i=0;i<2*n;i++){
            while(state==2){
                wait();
            }
            System.out.print("foo");
            state++;
            notifyAll();
        }
    }

    public synchronized void bar() throws InterruptedException{
        for(int i=0;i<n;i++){
            while(state!=2){
                wait();
            }
            System.out.print("bar");
            state=0;
            notifyAll();
        }
    }

    public static void main(String[] args) {

        FooFooBarSynchronized fb = new FooFooBarSynchronized(5);
        
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
