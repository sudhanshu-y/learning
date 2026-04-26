// Check if an Array is Sorted

public class IsSortedArray {

    public static boolean isSortedArray(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }

        boolean asc = true;
        boolean desc = true;

        for (int i = 1; i < arr.length && (asc || desc); i++) {
            asc = asc && (arr[i] >= arr[i - 1]);
            desc = desc && (arr[i] <= arr[i - 1]);
        }

        return asc || desc;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {5, 4, 6, 7, 8};

        System.out.println(isSortedArray(arr1)); // true
        System.out.println(isSortedArray(arr2)); // false
    }
}