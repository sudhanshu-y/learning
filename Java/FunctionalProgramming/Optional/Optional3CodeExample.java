import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Optional3CodeExample {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // Example 1: Optional.stream() → Stream of 0 or 1 element
        // ------------------------------------------------------------
        Optional<String> name1 = Optional.ofNullable("Rohan");
        Optional<String> name2 = Optional.ofNullable(null);

        Stream<String> nameStream1 = name1.stream();   // Stream("Rohan")
        Stream<String> nameStream2 = name2.stream();   // Stream() (empty)

        // Print stream contents in uppercase
        nameStream1.map(String::toUpperCase).forEach(System.out::println);
        // ROHAN

        nameStream2.map(String::toUpperCase).forEach(System.out::println);
        // (no output)

        // ------------------------------------------------------------
        // Example 2: Convert List<Optional<Integer>> → List<Integer>
        // ------------------------------------------------------------
        List<Optional<Integer>> numbers = Arrays.asList(
            Optional.empty(),    
            Optional.of(10),
            Optional.of(20),
            Optional.empty(),
            Optional.of(-30),
            Optional.of(50)
        );

        List<Integer> result = numbers.stream()
            .flatMap(Optional::stream)
            .toList();

        // [Optional.empty(), Optional(10), Optional(20), Optional.empty(), Optional(-30), Optional(50)]
        // map(Optional::stream) --> Stream<Stream<Integer>>
        // [Stream(), Stream(10), Stream(20), Stream(), Stream(-30), Stream(50)]
        
        
        // [Optional.empty(), Optional(10), Optional(20), Optional.empty(), Optional(-30), Optional(50)]
        // flatMap(Optional::stream) --> Stream<Integer>
        // [10, 20, -30, 50]
        
        System.out.println(result);
        // [10, 20, -30, 50]

        // ------------------------------------------------------------
        // Example 3: Return First Non-Empty Optional from a List
        // ------------------------------------------------------------

        Integer firstNonEmpty = numbers.stream()
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(Integer.MIN_VALUE);

        System.out.println("First non-empty number: " + firstNonEmpty);
        // First non-empty number: 10
    }
}
