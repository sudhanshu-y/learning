import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeTwoSortedArrays {

    /*
     * ============================================================
     * 1. Merge Two Sorted Arrays
     * ============================================================
     *
     * Given two sorted arrays, merge them into a new sorted array.
     *
     * Example:
     * arr1 = [1,3,5]
     * arr2 = [2,4,6]
     *
     * Result:
     * [1,2,3,4,5,6]
     *
     * Time  : O(n1 + n2)
     * Space : O(n1 + n2)
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < n1) result[k++] = arr1[i++];
        while (j < n2) result[k++] = arr2[j++];
        
        return result;
    }

    /*
     * ============================================================
     * 2. Merge Two Sorted Arrays Into First Array (Using Extra Space)
     * ============================================================
     *
     * Problem: nums1 has enough capacity to hold elements from both arrays.
     * Merge nums2 into nums1.
     *
     * Time  : O(m+n)
     * Space : O(m+n)
     */
    public static void mergeUsingExtraList(int[] nums1, int m, int[] nums2, int n) {

        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result.add(nums1[i++]);
            } else {
                result.add(nums2[j++]);
            }
        }

        while (i < m) result.add(nums1[i++]);
        while (j < n) result.add(nums2[j++]);
        
        for (int k = 0; k < result.size(); k++) {
            nums1[k] = result.get(k);
        }
    }

    /*
     * ============================================================
     * 3. Merge Two Sorted Arrays Into First Array (In-Place Optimal)
     * ============================================================
     *
     * Problem: nums1 contains m valid elements followed by n empty slots.
     * nums2 contains n sorted elements.
     * Merge nums2 into nums1 without using extra space.
     *
     * IMPORTANT: With 2 pointers moving in same direction technique:
     * If we start from the beginning,
     * we may overwrite values not processed yet.
     * So, better to track from end with highest value comparision. 
     * 
     * Time  : O(m+n)
     * Space : O(1)
     */
    public static void mergeInPlaceOptimal(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;         // last valid element in nums1
        int j = n - 1;         // last element in nums2
        int k = m + n - 1;     // last position in nums1

        while (i >= 0 && j >= 0) {
            // Place larger element at the end
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // This loop is technically redundant, As array values are already in place. 
        // But kept here for learning/reference.
        while (i >= 0) nums1[k--] = nums1[i--];
        
        // Remaining nums2 elements must be copied.
        while (j >= 0) nums1[k--] = nums2[j--];
    }

    /*
     * ============================================================
     * 4. Union of Two Sorted Arrays
     * ============================================================
     *
     * Return only distinct elements.
     *
     * Example:
     *
     * arr1 = [1,2,3,4]
     * arr2 = [2,3,4,5]
     *
     * Result:
     * [1,2,3,4,5]
     *
     * Time  : O(n1+n2)
     * Space : O(n1+n2)
     */
    public static int[] unionDistinct(int[] arr1, int[] arr2) {

        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            int val;
            if (arr1[i] < arr2[j]) {
                val = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                val = arr2[j++];
            } else {
                val = arr1[i];
                i++;
                j++;
            }

            if (k == 0 || result[k - 1] != val) {
                result[k++] = val;
            }
        }

        while (i < n1) {
            if (k == 0 || result[k - 1] != arr1[i]) {
                result[k++] = arr1[i];
            }
            i++; // if same then increment. 
            // Must be incremented at the end to avoid infinite loop. 
        }

        while (j < n2) {
            if (k == 0 || result[k - 1] != arr2[j]) {
                result[k++] = arr2[j];
            }
            j++; // if same then increment. 
            // Must be incremented at the end to avoid infinite loop. 
        }

        return Arrays.copyOf(result, k);
    }

    /*
     * ============================================================
     * 5. Union of Two Sorted Arrays (Using ArrayList)
     * ============================================================
     *
     * Return List.
     * 
     * Time  : O(n1+n2)
     * Space : O(n1+n2)
     */
    public static List<Integer> unionDistinctList(int[] arr1, int[] arr2) {

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {

            int val;

            if (arr1[i] < arr2[j]) {
                val = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                val = arr2[j++];
            } else {
                val = arr1[i];
                i++;
                j++;
            }

            if (result.isEmpty() || result.get(result.size() - 1) != val) {
                result.add(val);
            }
        }

        while (i < arr1.length) {
            if (result.isEmpty() || result.get(result.size() - 1) != arr1[i]) {
                result.add(arr1[i]);
            }
            i++;
        }

        while (j < arr2.length) {
            if (result.isEmpty() || result.get(result.size() - 1) != arr2[j]) {
                result.add(arr2[j]);
            }
            j++;
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("1. MERGE TWO SORTED ARRAYS");
        System.out.println("==================================================");

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 3, 4, 6, 7};

        System.out.println("Array 1 : " + Arrays.toString(arr1));
        System.out.println("Array 2 : " + Arrays.toString(arr2));

        int[] merged = mergeSortedArrays(arr1, arr2);

        System.out.println("Merged   : " + Arrays.toString(merged));

        System.out.println("\n==================================================");
        System.out.println("2. MERGE TWO SORTED ARRAYS INTO FIRST ARRAY (USING EXTRA SPACE)");
        System.out.println("==================================================");

        int[] nums1A = {1, 2, 3, 0, 0, 0};
        int[] nums2A = {2, 5, 6};

        System.out.println("Before : " + Arrays.toString(nums1A));
        mergeUsingExtraList(nums1A, 3, nums2A, 3);

        System.out.println("After  : " + Arrays.toString(nums1A));

        System.out.println("\n==================================================");
        System.out.println("3. MERGE TWO SORTED ARRAYS INTO FIRST ARRAY (IN-PLACE OPTIMAL)");
        System.out.println("==================================================");

        int[] nums1B = {1, 2, 3, 0, 0, 0};
        int[] nums2B = {2, 5, 6};

        System.out.println("Before : " + Arrays.toString(nums1B));
        mergeInPlaceOptimal(nums1B, 3, nums2B, 3);

        System.out.println("After  : " + Arrays.toString(nums1B));

        System.out.println("\n==================================================");
        System.out.println("4. UNION OF TWO SORTED ARRAYS");
        System.out.println("==================================================");

        int[] union = unionDistinct(arr1, arr2);

        System.out.println(Arrays.toString(union));

        System.out.println("\n==================================================");
        System.out.println("5. UNION OF TWO SORTED ARRAYS (ARRAYLIST)");
        System.out.println("==================================================");

        System.out.println(unionDistinctList(arr1, arr2));
    }
}