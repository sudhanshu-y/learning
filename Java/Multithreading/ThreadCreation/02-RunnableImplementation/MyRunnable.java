public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        System.out.println("Current thread: " + Thread.currentThread().getName());
        // Current thread: main

        MyRunnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable);
        t1.start();
        // Current thread: Thread-0
    }
}
