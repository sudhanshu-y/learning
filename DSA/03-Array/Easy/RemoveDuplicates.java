// Remove Duplicates in-place from Sorted Array
// Given an integer array sorted in non-decreasing order, remove the duplicates in place such that each unique element appears only once. 
// The relative order of the elements should be kept the same.

// Example
// Input: arr[]=[1,1,1,2,2,3,3,3,3,4,4]
// Output: [1,2,3,4,_,_,_,_,_,_,_]

import java.util.Arrays;

public class RemoveDuplicates {

    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int n = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[n] != arr[i]) {
                arr[++n] = arr[i];
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4};
        System.out.println("Array before removing duplicates: " + Arrays.toString(arr));

        int newLength = removeDuplicates(arr);
        System.out.println("Array after removing duplicates: " + Arrays.toString(Arrays.copyOf(arr, newLength)));
        System.out.println("New length: " + newLength);
    }
}