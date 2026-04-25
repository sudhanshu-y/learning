import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream4Reduce {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(2, 3, 4, 5);
        System.out.println("List: " + list);
        // List: [2, 3, 4, 5]

        // SUM using reduce(identity, accumulator)
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum (identity=0): " + sum);
        // Sum (identity=0): 14

        // Sum with identity=10
        int sum1 = list.stream().reduce(10, (a, b) -> a + b);
        System.out.println("Sum (identity=10): " + sum1);
        // Sum (identity=10): 24

        // SUM using reduce(BinaryOperator only)
        Optional<Integer> sum2 = list.stream().reduce((a, b) -> a + b);
        System.out.println("Sum (Optional): " + sum2.get());
        // Sum (Optional): 14

        // Multiplication of all elements
        Optional<Integer> mulVal = list.stream().reduce((a, b) -> a * b);
        System.out.println("Multiplication: " + mulVal.get());
        // Multiplication: 120

        // Maximum value
        Optional<Integer> maxVal = list.stream().reduce(Integer::max);
        System.out.println("Max Value: " + maxVal.get());
        // Max Value: 5

        // Sum value using method reference
        Integer sumVal = list.stream().reduce(0, Integer::sum);
        System.out.println("Sum Value: " + sumVal);
        // Sum Value: 14

        // Concatenate strings
        List<String> words = Arrays.asList("Java", "Stream", "API");
        String result = words.stream().reduce("", (str1, str2) -> str1 + str2);
        System.out.println("Words: " + words);
        // Words: [Java, Stream, API]
        System.out.println("Concatenated Result: " + result);
        // Concatenated Result: JavaStreamAPI

        // Parallel Stream reduce() with combiner
        Integer totalLength = words.parallelStream().reduce(
            0,
            (len, word) -> len + word.length(),
            (len1, len2) -> len1 + len2
        );
        System.out.println("Total length of words = " + totalLength);
        // Total length of words = 13

        // Parallel sum of integers
        List<Integer> intList = Arrays.asList(1, 2, 3);
        Integer totalSum = intList.parallelStream().reduce(
            0,
            (partialSum, x) -> partialSum + x,
            (pSum1, pSum2) -> pSum1 + pSum2
        );
        System.out.println("Total Sum = " + totalSum);
        // Total Sum = 6

        // Debug version of reduce with logs
        Integer totalSumDebug = intList.parallelStream().reduce(
            0,
            (partialSum, x) -> {
                System.out.println("x = " + x + " & partialSum = " + partialSum);
                return partialSum + x;
            },
            (pSum1, pSum2) -> {
                System.out.println("pSum1 = " + pSum1 + " & pSum2 = " + pSum2);
                return pSum1 + pSum2;
            }
        );
        System.out.println("Total Sum (Debug) = " + totalSumDebug);
        // Total Sum (Debug) = 6
    }
}
