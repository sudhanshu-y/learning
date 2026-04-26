// Four thread functions
// fizz()      : Prints "fizz" for numbers divisible by 3 but not 5.
// buzz()      : Prints "buzz" for numbers divisible by 5 but not 3.
// fizzbuzz()  : Prints "fizzbuzz" for numbers divisible by both 3 and 5, which means divisible by 15.
// number()    : Prints the number itself when it's not divisible by either 3 or 5.

// Example:
// For n=15, the output must be exactly "1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz".

public class FizzBuzzSynchronized {

    private int limit;
    private int n;

    public FizzBuzzSynchronized(int limit){
        this.limit = limit;
        this.n = 1;
    }
    
    public synchronized void fizz() throws InterruptedException {
        while (n <= limit) {
            while (n <= limit && !(n % 3 == 0 && n % 5 != 0)) {
                wait();
            }
            if (n > limit) break;

            System.out.print("fizz ");
            n++;
            notifyAll();
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (n <= limit) {
            while (n <= limit && !(n % 5 == 0 && n % 3 != 0)) {
                wait();
            }
            if (n > limit) break;

            System.out.print("buzz ");
            n++;
            notifyAll();
        }
    }

    public synchronized void fizzBuzz() throws InterruptedException {
        while (n <= limit) {
            while (n <= limit && !(n % 3 == 0 && n % 5 == 0)) {
                wait();
            }
            if (n > limit) break;

            System.out.print("fizzbuzz ");
            n++;
            notifyAll();
        }
    }

    public synchronized void number() throws InterruptedException {
        while (n <= limit) {
            while (n <= limit && (n % 3 == 0 || n % 5 == 0)) {
                wait();
            }
            if (n > limit) break;

            System.out.print(n + " ");
            n++;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        FizzBuzzSynchronized fb = new FizzBuzzSynchronized(15);

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
