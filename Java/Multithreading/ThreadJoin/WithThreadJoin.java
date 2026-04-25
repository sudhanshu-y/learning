public class WithThreadJoin {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " Started");

        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Started");
            try {
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Finished");
        });

        t.start();

        try {
            t.join(); // Main thread waits for t to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " Finished");
    }
}
