// Sort an Array of 0s, 1s and 2s
// Given an array containing only 0s, 1s and 2s, sort the array in ascending order.
//
// Example:
// Input : arr = [2, 0, 2, 1, 1, 0]
// Output: [0, 0, 1, 1, 2, 2]
//
// ------------------------------------------------------------
// Approach 1: Counting (Brute Force)
// ------------------------------------------------------------
// Count the number of 0s, 1s, and 2s.
// Then overwrite the array:
// - Fill count0 times with 0
// - Fill count1 times with 1
// - Fill count2 times with 2
//
// Time Complexity : O(n)
// Space Complexity: O(1)
//
// ------------------------------------------------------------
// Approach 2: Dutch National Flag Algorithm 
// ------------------------------------------------------------
// Maintain three pointers:
// low  -> boundary for 0s
// mid  -> current element
// high -> boundary for 2s
//
// Rules:
// - If arr[mid] == 0, swap with low and move both forward.
// - If arr[mid] == 1, move mid.
// - If arr[mid] == 2, swap with high and move high backward.
//
// Time Complexity : O(n)
// Space Complexity: O(1)

import java.util.Arrays;

public class Sort012 {

    // Counting Approach
    public static void sortCounting(int[] arr) {

        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : arr) {
            if (num == 0)
                count0++;
            else if (num == 1)
                count1++;
            else
                count2++;
        }

        int index = 0;

        while (count0-- > 0)
            arr[index++] = 0;

        while (count1-- > 0)
            arr[index++] = 1;

        while (count2-- > 0)
            arr[index++] = 2;
    }

    // Dutch National Flag Algorithm
    public static void sortOptimal(int[] arr) {

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {

            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high);
                high--;
            }
        }
    }

    // Swap Utility
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr1 = {2, 0, 2, 1, 1, 0};
        int[] arr2 = arr1.clone();

        System.out.println("Original Array : " + Arrays.toString(arr1));

        sortCounting(arr1);
        System.out.println("Counting       : " + Arrays.toString(arr1));

        sortOptimal(arr2);
        System.out.println("Optimal        : " + Arrays.toString(arr2));
    }
}