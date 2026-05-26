import java.util.Arrays;

public class RotateArray {

    // Right Rotation
    // Reverse the whole array
    // Reverse first k elements
    // Reverse remaining n - k elements
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k = k % n;

        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    // Left Rotation
    // Reverse first k elements
    // Reverse remaining n - k elements
    // Reverse whole array
    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        k = k % n;

        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    // Reverse array
    public static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.println("Original Array:  " + Arrays.toString(arr));

        rotateLeft(arr, k);
        System.out.println("Left Rotate:  " + Arrays.toString(arr));

        rotateRight(arr, k);
        System.out.println("Right Rotate: " + Arrays.toString(arr));
    }
}