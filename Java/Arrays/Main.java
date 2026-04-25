import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] arr = {5, 2, 9, 1, 7};
        int[][] multi = {{1, 2}, {3, 4, 5}};

        // toString()
        System.out.println("Original Array: " + Arrays.toString(arr));
        // [5, 2, 9, 1, 7]

        // deepToString() – for multidimensional arrays
        System.out.println("Multi-dimensional Array: " + Arrays.deepToString(multi));
        // [[1, 2], [3, 4, 5]]

        // sort()
        Arrays.sort(arr);
        System.out.println("After Sorting: " + Arrays.toString(arr));
        // [1, 2, 5, 7, 9]

        // parallelSort() – uses multithreading
        int[] pArr = {8, 3, 9, 2, 1};
        Arrays.parallelSort(pArr);
        System.out.println("Parallel Sorted: " + Arrays.toString(pArr));
        // [1, 2, 3, 8, 9]

        // binarySearch() – works only on sorted arrays
        int index = Arrays.binarySearch(arr, 7);
        System.out.println("Index of 7: " + index);
        // 3

        // copyOf()
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copy: " + Arrays.toString(copy));
        // [1, 2, 5, 7, 9]

        System.out.println("Copy (larger size): " + Arrays.toString(Arrays.copyOf(arr, 10)));
        // [1, 2, 5, 7, 9, 0, 0, 0, 0, 0]

        // copyOfRange()
        int[] range = Arrays.copyOfRange(arr, 1, 4);
        System.out.println("Copy of Range 1–4: " + Arrays.toString(range));
        // [2, 5, 7]

        // equals()
        int[] arr2 = {1, 2, 5, 7, 9};
        System.out.println("arr equals arr2: " + Arrays.equals(arr, arr2));
        // true

        // deepEquals()
        int[][] multi2 = {{1, 2}, {3, 4, 5}};
        System.out.println("Deep Equals: " + Arrays.deepEquals(multi, multi2));
        // true

        // stream()
        IntStream stream = Arrays.stream(arr);
        System.out.println("Sum using stream(): " + stream.sum());
        // 24

        // asList()
        List<String> names = Arrays.asList("John", "Amit", "Bob");
        System.out.println("Names: " + names);
        // [John, Amit, Bob]
    }
}
