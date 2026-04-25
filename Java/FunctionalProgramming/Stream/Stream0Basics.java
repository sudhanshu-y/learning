import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream0Basics {
    static int counter = 0;

    static void wasCalled() {
        counter++;
        System.out.println("wasCalled invoked, counter = " + counter);
    }

    public static void main(String[] args) {

        // Lazy Invocation

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");

        // Build the pipeline, but no terminal op yet
        Stream<String> stream = list.stream()
        .filter(
            element -> {
                wasCalled();
                return element.contains("2");
            }
        );

        // At this point NOTHING has run!
        System.out.println("Before terminal op, counter = " + counter);

        // Now invoke a terminal op
        String firstMatch = stream.findFirst().orElse("Not found");

        System.out.println("After terminal op, counter = " + counter);
        System.out.println("Result = " + firstMatch);

        // Order of Execution matters 
        List<String> names = Arrays.asList("Anna", "Bob", "Alice", "Charlie", "Alex");

        names.stream()
        .map(String::toUpperCase)     // runs on every element
        .filter(name -> name.startsWith("A")) // filter after mapping   
        .forEach(System.out::println);

        names.stream()
        .filter(name -> name.startsWith("A")) // reduces the stream first
        .map(String::toUpperCase)            // applies only to survivors
        .forEach(System.out::println);
    }
}

