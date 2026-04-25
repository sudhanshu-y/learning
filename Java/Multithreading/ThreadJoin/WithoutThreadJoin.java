public class WithoutThreadJoin {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " Started");

        Thread t = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName() + " Started");
                try {
                    Thread.sleep(2000); // Simulates some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Finished");
            }
        );

        t.start();

        System.out.println(Thread.currentThread().getName() + " Finished");
    }
}
