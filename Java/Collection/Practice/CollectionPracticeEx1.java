import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionPracticeEx1 {

    public static void main(String[] args) {

        // 1. Remove duplicates from a list
        List<Integer> list = Arrays.asList(1, 3, 3, 1, 5, 2, 6);
        System.out.println("Original List: " + list); 
        // Original List: [1, 3, 3, 1, 5, 2, 6]

        Set<Integer> set = new HashSet<>(list);
        System.out.println("Without Duplicates (Unordered): " + set);
        // Without Duplicates (Unordered): [1, 2, 3, 5, 6]

        Set<Integer> orderedSet = new LinkedHashSet<>(list);
        System.out.println("Without Duplicates (Ordered): " + orderedSet);
        // Without Duplicates (Ordered): [1, 3, 5, 2, 6]

        System.out.println("--------------------------------------------------");

        // 2. Find frequency of characters in a String (ignore case)
        String str = "Banana".toLowerCase();
        Map<Character, Integer> charFreq = new HashMap<>();

        for (char c : str.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }

        System.out.println("Character Frequency: " + charFreq);
        // Character Frequency: {a=3, b=1, n=2}

        System.out.println("--------------------------------------------------");

        // 3. Sort a HashMap by keys and values
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(3, "Cpp");
        map.put(2, "Python");

        System.out.println("Sorted by Key:");
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

        System.out.println("Sorted by Value:");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        System.out.println("--------------------------------------------------");

        // 4. Find first non-repeated character
        Map<Character, Integer> charFreqMap = new LinkedHashMap<>();

        for (char c : str.toCharArray()) {
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }

        for (var entry : charFreqMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First Non-Repeated Character: " + entry.getKey());
                break;
            }
        }
        // First Non-Repeated Character: b

        System.out.println("--------------------------------------------------");

        // 5. Custom sorting using Comparator (ASC & DESC)
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 3);

        numbers.sort(Comparator.naturalOrder());
        System.out.println("Ascending Order: " + numbers);
        // Ascending Order: [1, 2, 3, 5, 9]

        numbers.sort(Comparator.reverseOrder());
        System.out.println("Descending Order: " + numbers);
        // Descending Order: [9, 5, 3, 2, 1]

        numbers.sort((a, b) -> (a-b));
        System.out.println("Ascending Order: " + numbers);
        // Ascending Order: [1, 2, 3, 5, 9]

        numbers.sort((a, b) -> b-a);
        System.out.println("Descending Order: " + numbers);
        // Descending Order: [9, 5, 3, 2, 1]

        System.out.println("--------------------------------------------------");

        // 6. Find most frequent element in an array
        int[] arr = {1, 2, 3, 1, 2, 1, 4};
        System.out.println("Array: "+Arrays.toString(arr));
        // Array: [1, 2, 3, 1, 2, 1, 4]

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int x : arr) {
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        }

        int mostFrequent = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Most Frequent Element: " + mostFrequent);
        // Most Frequent Element: 1

        System.out.println("--------------------------------------------------");

        // 7. Convert List to Map with duplicate keys (grouping)
        List<String> names = Arrays.asList("apple", "banana", "apple", "orange", "banana");

        Map<String, Long> nameCountMap = names.stream().collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        System.out.println("List to Map with Duplicates:");
        System.out.println(nameCountMap);
        // {orange=1, banana=2, apple=2}
    }
}