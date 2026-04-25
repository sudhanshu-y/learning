public class VolatileExample {

    // volatile ensures that the write by the main thread is immediately visible to the worker thread.
    volatile boolean flag = true;

    public void startTask(){
        while(flag){
            System.out.println("Task Running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
       System.out.println("Task terminated.");
    }

    public void stopTask(){
        this.flag=false;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample example = new VolatileExample();

        Thread t1 = new Thread(() -> example.startTask());
        t1.start();

        Thread.sleep(5000);

        System.out.println("Stopping task...");
        example.stopTask();

        t1.join();  
    }
}
