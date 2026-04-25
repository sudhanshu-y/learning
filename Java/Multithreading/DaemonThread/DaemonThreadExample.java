public class DaemonThreadExample {
    public static void main(String args[]) {
        System.out.println(Thread.currentThread().getName() + " Start");

        Thread t = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName() + " Running");
                System.out.println(Thread.currentThread().getName() + " isDaemon Thread? : " + Thread.currentThread().isDaemon());
            }
        );

        t.setDaemon(true);  // Set thread 't' as daemon
        t.start();             // Start the daemon thread

        System.out.println(Thread.currentThread().getName() + " End");
        System.out.println(Thread.currentThread().getName() + " isDaemon Thread? : " + Thread.currentThread().isDaemon());
    }
}
