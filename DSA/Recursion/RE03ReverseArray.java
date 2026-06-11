import java.util.Arrays;

public class RE03ReverseArray {

    // Reverse array using recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void reverseRecursive(int[] arr, int left, int right) {
        if (left >= right) return;
        swap(arr, left, right);
        reverseRecursive(arr, left + 1, right - 1);
    }

    // Swap two elements in array
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6};
        System.out.println("Original Array: " + Arrays.toString(arr));

        reverseRecursive(arr, 0, arr.length - 1);
        System.out.println("Reversed Array: " + Arrays.toString(arr));
    }
}