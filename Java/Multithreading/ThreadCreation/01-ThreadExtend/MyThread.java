public class MyThread extends Thread {
    // Less flexible (due to single inheritance)

    @Override
    public void run(){
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        System.out.println("Current thread: " + Thread.currentThread().getName());
        //Current thread: main

        MyThread t1 = new MyThread();
        t1.start(); // Start the thread
        //Current thread: Thread-0
    }
}
