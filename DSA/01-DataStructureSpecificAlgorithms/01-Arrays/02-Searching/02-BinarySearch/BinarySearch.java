import java.util.Arrays;

public class BinarySearch {

    // Iterative Binary Search
    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == value)
                return mid;
            else if (arr[mid] < value)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // Recursive Binary Search
    public static int recursiveBinarySearch(int[] arr, int left, int right, int value) {

        if (left > right) return -1;

        int mid = (left + right) / 2;

        if (arr[mid] == value) 
            return mid;
        else if (arr[mid] < value)
            return recursiveBinarySearch(arr, mid + 1, right, value);

        return recursiveBinarySearch(arr, left, mid - 1, value);
    }

    public static void main(String[] args) {
        int[] arr = {45, 12, 78, 34, 23};

        System.out.println("Original array: " + Arrays.toString(arr));

        // Arrays must be sorted
        Arrays.sort(arr);
        System.out.println("Sorted array:   " + Arrays.toString(arr));

        // Iterative binary search
        int index1 = binarySearch(arr, 34);
        System.out.println("Iterative Search Index: " + index1);

        // Recursive binary search
        int index2 = recursiveBinarySearch(arr, 0, arr.length - 1, 34);
        System.out.println("Recursive Search Index: " + index2);
    }
}
