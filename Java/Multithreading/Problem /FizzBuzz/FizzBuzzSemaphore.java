import java.util.concurrent.Semaphore;

public class FizzBuzzSemaphore {

    private int limit;
    private int n;
    private Semaphore fizzTurn;
    private Semaphore buzzTurn;
    private Semaphore fizzBuzzTurn;
    private Semaphore numberTurn;

    public FizzBuzzSemaphore(int limit) {
        this.limit = limit;
        this.n = 1;
        this.fizzTurn = new Semaphore(0);
        this.buzzTurn = new Semaphore(0);
        this.fizzBuzzTurn = new Semaphore(0);
        this.numberTurn = new Semaphore(1);
    }

    public void releaseNext() {

        if (n > limit) {
            fizzTurn.release();
            buzzTurn.release();
            fizzBuzzTurn.release();
            numberTurn.release();
            return;
        }

        if (n % 3 == 0 && n % 5 != 0) {
            fizzTurn.release();
        } else if (n % 3 != 0 && n % 5 == 0) {
            buzzTurn.release();
        } else if (n % 3 == 0 && n % 5 == 0) {
            fizzBuzzTurn.release();
        } else {
            numberTurn.release();
        }
    }

    public void fizz() throws InterruptedException {
        while (n <= limit) {
            fizzTurn.acquire();
            if (n > limit) break;

            System.out.print("fizz ");
            n++;
            releaseNext();
        }
    }

    public void buzz() throws InterruptedException {
        while (n <= limit) {
            buzzTurn.acquire();
            if (n > limit) break;

            System.out.print("buzz ");
            n++;
            releaseNext();
        }
    }

    public void fizzBuzz() throws InterruptedException {
        while (n <= limit) {
            fizzBuzzTurn.acquire();
            if (n > limit) break;

            System.out.print("fizzbuzz ");
            n++;
            releaseNext();
        }
    }

    public void number() throws InterruptedException {
        while (n <= limit) {
            numberTurn.acquire();
            if (n > limit) break;

            System.out.print(n + " ");
            n++;
            releaseNext();
        }
    }

    public static void main(String[] args) {
        FizzBuzzSemaphore fb = new FizzBuzzSemaphore(15);

        Thread t1 = new Thread(() -> {
            try {
                fb.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fb.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                fb.fizzBuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                fb.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}