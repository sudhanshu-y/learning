// Anonymous Class vs Lambda Expression

// Custom Functional Interface
interface Greeting {
    void sayHello();
}

public class AnonymousClassVsLambda {
    public static void main(String[] args) {

        // Example 1: Greeting Interface (Custom Functional Interface)
        Greeting greet1 = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from Anonymous Class!");
            }
        };

        Greeting greet2 = () -> System.out.println("Hello from Lambda Expression!");

        greet1.sayHello();
        greet2.sayHello();

        // Example 2: Runnable Interface (Built-in Functional Interface)
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running from Anonymous Class!");
            }
        };

        Runnable task2 = () -> System.out.println("Running from Lambda Expression!");

        task1.run();
        task2.run();
    }
}


