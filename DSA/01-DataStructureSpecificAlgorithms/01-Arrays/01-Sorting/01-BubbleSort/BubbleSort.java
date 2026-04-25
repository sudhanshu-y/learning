import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] arr){
        int n = arr.length;

        // repeat process n-1 times (After n-1 passes, the array is guaranteed to be sorted.)
        for(int i = 0; i < n - 1; i++){
            // compare and swap
            // n-1 because j should not go out of bound 
            for(int j = 0; j < n - i - 1; j++){ 
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 8, 4, 9, 1};

        System.out.println("Before sorting: " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("After sorting:  " + Arrays.toString(arr));
    }
}
