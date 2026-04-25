// Reverse a given Array

import java.util.Arrays;

public class ReverseArray {

    // Iterative Approach
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static void reverseIterative(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int left = 0, right = arr.length - 1;

        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    // Recursive Approach
    // Time Complexity: O(N)
    // Space Complexity: O(N) (recursion stack)
    public static void reverseRecursive(int[] arr, int left, int right) {
        if (left >= right) return;

        swap(arr, left, right);
        reverseRecursive(arr, left + 1, right - 1);
    }

    // Swap helper
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};

        System.out.println("Original: " + Arrays.toString(arr));

        reverseIterative(arr);
        System.out.println("After Iterative Reverse: " + Arrays.toString(arr));

        reverseRecursive(arr, 0, arr.length - 1);
        System.out.println("After Recursive Reverse: " + Arrays.toString(arr));
    }
}