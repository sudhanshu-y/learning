import java.util.Arrays;

public class MoveZerosToEnd {

    public static void moveZerosToEnd(int[] arr) {
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j++] = arr[i];
            }
        }

        for (int i = j; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    public static void moveZerosToEndOptimal(int[] arr) {
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                swap(arr, i, j);                
                j++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 0, 1, 0, 4, 0};
        int[] arr2 = arr1.clone();

        System.out.println("Before: " + Arrays.toString(arr1));

        moveZerosToEnd(arr1);
        System.out.println("After (overwrite): " + Arrays.toString(arr1));

        moveZerosToEndOptimal(arr2);
        System.out.println("After (swap):      " + Arrays.toString(arr2));
    }
}