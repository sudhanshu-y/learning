import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CoreFunctionalInterfaces {
    public static void main(String[] args) {
        
        // Predicate<T> — takes a value, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Function<T, R> — takes one argument, returns a result
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Lambda': " + stringLength.apply("Lambda"));
        System.out.println("Length of 'Function': " + stringLength.apply("Function"));

        // Consumer<T> — takes a value, returns nothing (void)
        Consumer<String> printUpper = str -> System.out.println(str.toUpperCase());
        printUpper.accept("hello world");
        printUpper.accept("java functional interfaces");

        // Supplier<T> — takes nothing, returns a value
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value 1: " + randomValue.get());
        System.out.println("Random value 2: " + randomValue.get());

        // Combining in an example:
        System.out.println("\n--- Combined Example ---");
        Supplier<Integer> numberSupplier = () -> (int)(Math.random() * 100);
        Predicate<Integer> isLarge = n -> n > 50;
        Function<Integer, String> describe = n -> "Number " + n + (isLarge.test(n) ? " is large" : " is small");
        Consumer<String> print = msg -> System.out.println(msg);

        for (int i = 0; i < 5; i++) {
            Integer num = numberSupplier.get();
            String description = describe.apply(num);
            print.accept(description);
        }
    }
}
