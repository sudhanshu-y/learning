import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if(left>=right) return;
        
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);        // left half
        mergeSort(arr, mid + 1, right);   // right half

        // left and right both halfs are sorted now.
        // copy left and right into arr such that arr is sorted.
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1; // length of left sub array 
        int n2 = right - mid;    // length of the righ sub array 

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy left half
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];

        // Copy right half
        // mid element is already included in left array
        for (int i = 0; i < n2; i++) R[i] = arr[mid + 1 + i]; 

        int i = 0, j = 0;   // Initial indexes of first and second sub-arrays
        int k = left;       // Initial index of merged sub-array

        // Merge
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
             else arr[k++] = R[j++];
        }

        // Remaining elements
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 7, 0, 10, 2};

        System.out.println("Before sorting: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("After sorting: " + Arrays.toString(arr));
    }
}
