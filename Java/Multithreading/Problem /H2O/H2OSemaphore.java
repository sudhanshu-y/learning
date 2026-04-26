// There are two kinds of threads: hydrogen and oxygen. 
// The goal is to form water molecules by combining them. 

// Valid: HHO, HOH, OHH 

import java.util.concurrent.Semaphore;

public class H2OSemaphore {

    private Semaphore hydrogen = new Semaphore(2);
    private Semaphore oxygen = new Semaphore(0);

    public void hydrogen() throws InterruptedException {
        hydrogen.acquire();

        System.out.print("H");

        // signal oxygen that one H is ready
        oxygen.release();
    }

    public void oxygen() throws InterruptedException {
        // wait for 2 hydrogens
        oxygen.acquire(2);

        System.out.print("O");

        // reset for next molecule
        hydrogen.release(2);
    }

    public static void main(String[] args) {
        H2OSemaphore h2o = new H2OSemaphore();

        // Example threads
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    h2o.hydrogen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    h2o.oxygen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}