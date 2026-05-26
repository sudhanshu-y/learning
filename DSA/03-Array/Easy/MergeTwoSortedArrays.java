/*
Union of Two Sorted Arrays

Problem Statement: Given two sorted arrays, arr1, and arr2 of size n and m. Find the union of two sorted arrays.
The union of two arrays can be defined as the common and distinct elements in the two arrays.
NOTE: Elements in the union should be in ascending order.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeTwoSortedArrays {

    public static List<Integer> merge(int[] arr1, int[] arr2) {

        List<Integer> result = new ArrayList<>();

        int n1 = arr1.length;
        int n2 = arr2.length;

        int i = 0, j = 0;

        while (i < n1 && j < n2) {

            if (arr1[i] <= arr2[j]) {

                // Avoid duplicates
                if (result.isEmpty() || result.get(result.size() - 1) != arr1[i]) {
                    result.add(arr1[i]);
                }
                i++;

            } else {

                // Avoid duplicates
                if (result.isEmpty() || result.get(result.size() - 1) != arr2[j]) {
                    result.add(arr2[j]);
                }
                j++;
            }
        }

        // Remaining elements from arr1
        while (i < n1) {
            if (result.isEmpty() || result.get(result.size() - 1) != arr1[i]) {
                result.add(arr1[i]);
            }
            i++;
        }

        // Remaining elements from arr2
        while (j < n2) {
            if (result.isEmpty() || result.get(result.size() - 1) != arr2[j]) {
                result.add(arr2[j]);
            }
            j++;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr1 = {1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = {2,3,4,4,5,11,12};

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        List<Integer> list = merge(arr1, arr2);
        System.out.println(list);
    }
}