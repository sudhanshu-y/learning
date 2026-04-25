import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        // pivot index is placed at correct sorted place
        int pivotIndex = partition(arr, left, right);

        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }

        // Place pivot at correct position
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 7, 0, 10, 2};

        System.out.println("Before sorting: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("After sorting:  " + Arrays.toString(arr));
    }
}
