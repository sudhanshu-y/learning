import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream1Basics {
    public static void main(String[] args) {

        // 1. Empty Stream
        Stream<String> emptyStream = Stream.empty();
        System.out.println("Empty stream count: " + emptyStream.count()); // 0

        // 2. Stream from Collection (List.of)
        List<String> names1 = List.of("Alice", "Bob", "Charlie", "David", "Eve", "Charmy");
        Stream<String> nameStream = names1.stream();
        System.out.println("Stream from List.of:");
        nameStream.forEach(System.out::println);

        // 3. Stream from Collection (Arrays.asList)
        List<String> names2 = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Charmy");
        System.out.println("Stream from Arrays.asList:");
        names2.stream().forEach(System.out::println);

        // 4. Stream from Array
        String[] fruits = {"Apple", "Banana", "Orange"};
        Stream<String> fruitStream1 = Arrays.stream(fruits);
        Stream<String> fruitStream2 = Stream.of(fruits); // same result
        System.out.println("Stream from Array:");
        fruitStream1.forEach(System.out::println);
        fruitStream2.forEach(System.out::println);

        // 5. Filter Example
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original List: " + list);

        // Filtering even numbers
        List<Integer> evenList = list.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("Even numbers: " + evenList);

        // Filtering odd numbers
        List<Integer> oddList = list.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
        System.out.println("Odd numbers: " + oddList);

        // 6. Mapping (Squares of numbers)
        List<Integer> squares = list.stream().map(n -> n * n).toList();
        System.out.println("Squares: " + squares);

        // 7. Reduction (Sum of list)
        int listSum = list.stream().reduce(0, Integer::sum);
        int listSum2 = list.stream().reduce(0, (a, b)->(a+b)); // same result
        System.out.println("Sum: " + listSum);
        System.out.println("Sum: " + listSum2);

        // 8. Sorting in ascending/descending order
        List<Integer> sortedList = list.stream().sorted().toList();
        System.out.println("Sorted List: " + sortedList);

        List<Integer> reversedList = list.stream().sorted(Collections.reverseOrder()).toList();
        System.out.println("Reversed Sorted List: " + reversedList);

        List<Integer> reversedList2 = list.stream().sorted((a, b)-> a-b).toList();
        System.out.println("Reversed Sorted List2: " + reversedList2);

        // 9. Using streams with Strings
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Charmy");
        System.out.println("Names: " + names);

        // Filter names starting with 'C' (case-insensitive)
        List<String> cNames = names.stream().filter(name -> name.toLowerCase().startsWith("c")).toList();
        System.out.println("Names starting with 'C': " + cNames);

        // Convert to Uppercase (map)
        List<String> upperNames = names.stream().map(name -> name.toUpperCase()).toList();
        System.out.println("Uppercase names: " + upperNames);

        // Using method reference
        List<String> upperNames2 = names.stream().map(String::toUpperCase).toList();
        System.out.println("Uppercase names2: " + upperNames2);

        // Count names with length > 3
        long countGT3 = names.stream().filter(name -> name.length() > 3).count();
        System.out.println("Names with length > 3: " + countGT3);

        // 10. Limit and Skip
        System.out.println("First 3 elements (limit):");
        Stream.of("A", "B", "C", "D", "E").limit(3).forEach(System.out::println);

        System.out.println("Skipping first 2 elements:");
        Stream.of("A", "B", "C", "D", "E").skip(2).forEach(System.out::println);

        System.out.println("Skip 2, then take 2:");
        names.stream().skip(2).limit(2).forEach(System.out::println);

        // 11. Finding Elements
        Optional<String> aName = names.stream().filter(name -> name.contains("C")).findFirst();
        System.out.println("First name containing 'A': " + aName.orElse("Not Found"));

        Optional<String> anyAname = names.stream().filter(name -> name.contains("A")).findAny();
        System.out.println("Any name containing 'A': " + anyAname.orElse("Not Found"));

        // 12. Grouping with Collectors
        Map<Integer, List<String>> groupedByLength = names.stream()
        .collect(
            Collectors.groupingBy(
                String::length
            )
        );
        System.out.println("Grouped by length: " + groupedByLength);

        // 13. Distinct Elements
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinctNumbers = numbersWithDuplicates.stream().distinct().toList();
        System.out.println("Distinct numbers: " + distinctNumbers);

        // 14. FlatMap (Flatten nested lists)
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("A", "B"),
            Arrays.asList("C", "D"),
            Arrays.asList("E", "F")
        );
        List<String> flatList = nestedList.stream().flatMap(List::stream).toList();
        // List::stream converts each inner list into a stream
        System.out.println("Flattened list: " + flatList);

        // 15. Matching
        boolean anyStartsWithA = names.stream().anyMatch(name -> name.startsWith("A"));
        boolean allHaveLengthGT2 = names.stream().allMatch(name -> name.length() > 2);
        boolean noneStartsWithZ = names.stream().noneMatch(name -> name.startsWith("Z"));
        System.out.println("Any name starts with 'A': " + anyStartsWithA);
        System.out.println("All names have length > 2: " + allHaveLengthGT2);
        System.out.println("No name starts with 'Z': " + noneStartsWithZ);

        // 16. Min and Max
        Optional<Integer> minNum = list.stream().min(Integer::compareTo);
        Optional<Integer> maxNum = list.stream().max(Integer::compareTo);
        System.out.println("Min number: " + minNum.orElse(null));
        System.out.println("Max number: " + maxNum.orElse(null));
    }
}