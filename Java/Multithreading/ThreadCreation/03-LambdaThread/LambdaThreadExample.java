public class LambdaThreadExample {
    public static void main(String[] args) {
        System.out.println("Current thread: " + Thread.currentThread().getName());
        // Current thread: main

        Thread t1 = new Thread(
            ()->System.out.println("Current thread: " + Thread.currentThread().getName())
        );

        t1.start();
        // Current thread: Thread-0
    }
}
