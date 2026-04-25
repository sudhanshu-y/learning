import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamProblems {

    public static void main(String[] args) {

        // Word frequency from a list
        List<String> wordList = Arrays.asList("apple", "banana", "apple","orange", "banana", "apple", "strawberry");

        Map<String, Long> wordFrequency = wordList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("Word Frequency: " + wordFrequency);
        // Word Frequency: {banana=2, orange=1, apple=3, strawberry=1}


        // Character frequency from a String
        String input = "banana";

        Map<Character, Long> charFrequency = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("Character Frequency from String: " + charFrequency);
        // Character Frequency from String: {a=3, b=1, n=2}


        // Reverse each word in a sentence
        String sentence = "Java Stream Interview Questions";
        System.out.println("Sentence: " + sentence);
        // Sentence: Java Stream Interview Questions

        String reversedSentence = Arrays.stream(sentence.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println("Reversed Words: " + reversedSentence);
        // Reversed Words: avaJ maertS weivretnI snoitseuQ


        // Find the longest string in a list
        String longestWord = wordList.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");
        System.out.println("Longest Word: " + longestWord);
        // Longest Word: strawberry


        // Find the second highest number in a list
        List<Integer> numberList = Arrays.asList(10, -30, 20, 40, -50, -50, 20);
        System.out.println("Numbers: " + numberList);
        // Numbers: [10, -30, 20, 40, -50, -50, 20]

        int secondHighest = numberList.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);

        System.out.println("Second Highest: " + secondHighest);
        // Second Highest: 20


        // Find duplicate elements in a list
        List<Integer> duplicateNumbers = numberList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Duplicate Numbers: " + duplicateNumbers);
        // Duplicate Numbers: [-50, 20]


        // Find first non-repeated character
        String text = "swiss";

        Character firstNonRepeated = text.chars()
                .mapToObj(c -> (char) c)
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                    )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First Non-Repeated Character: " + firstNonRepeated);
        // First Non-Repeated Character: w


        // Sort strings by length
        List<String> names = Arrays.asList("John", "Alexander", "Bob", "Chris");

        List<String> sortedByLength = names.stream()
                .sorted(Comparator.comparing(String::length))
                .toList();

        System.out.println("Sorted by Length: " + sortedByLength);
        // Sorted by Length: [Bob, John, Chris, Alexander]

    }
}
