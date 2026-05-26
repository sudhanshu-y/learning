import java.util.Arrays;

public class BS1BinarySearch {

    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target)
                return mid;
            else if (target > arr[mid])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right)
            return -1;

        int mid = (left + right) / 2;

        if (arr[mid] == target)
            return mid;
        else if (target > arr[mid])
            return binarySearchRecursive(arr, target, mid + 1, right);

        return binarySearchRecursive(arr, target, left, mid - 1);
    }

    public static void main(String[] args) {

        int[] arr = {3, 4, 6, 7, 9, 12, 16, 17};
        int target = 6;

        System.out.println(Arrays.toString(arr));

        // Iterative Binary Search
        int index1 = binarySearchIterative(arr, target);
        System.out.println("Iterative Search Index: " + index1);

        // Recursive Binary Search
        int index2 = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Recursive Search Index: " + index2);
    }
}