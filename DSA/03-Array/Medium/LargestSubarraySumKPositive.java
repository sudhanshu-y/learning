// Longest Subarray with Given Sum K (Positive Numbers)
//
// Example:
// Input : nums = [10, 5, 2, 7, 1, 9], k = 15
// Output: 4
//
// Explanation:
// The longest subarray with sum = 15 is [5, 2, 7, 1],
// whose length is 4.
//
// ------------------------------------------------------------
// Approach 1: Brute Force
// ------------------------------------------------------------
// Generate all possible subarrays and calculate their sum.
// If the sum equals k, update the maximum length.
//
// Time Complexity : O(n²)
// Space Complexity: O(1)
//
// ------------------------------------------------------------
// Approach 2: Sliding Window (Optimal)
// ------------------------------------------------------------
// Applicable only for positive numbers.
//
// Time Complexity : O(n)
// Space Complexity: O(1)

import java.util.Arrays;

public class LargestSubarraySumKPositive {

    // Brute Force Approach
    public static int longestSubarrayBruteForce(int[] arr, int targetSum) {

        int n = arr.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            int sum = 0;

            for (int j = i; j < n; j++) {

                sum += arr[j];

                if (sum == targetSum) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // Sliding Window Approach (Positive Numbers Only)
    public static int largestSubarraySumK(int[] arr, int k) {

        int maxLen = 0;
        int left = 0;
        int sum = 0;

        // Expand the window using the right pointer
        for (int right = 0; right < arr.length; right++) {

            // Add the current element to the window sum
            sum += arr[right];

            // Shrink the window while the sum exceeds k
            while (sum > k) {
                sum -= arr[left++];
            }

            // Update the maximum length if the sum equals k
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

        int[] arr = {10, 5, 2, 7, 1, 9};
        int k = 15;

        System.out.println("Array : " + Arrays.toString(arr));
        System.out.println("K     : " + k);

        System.out.println("Brute Force Result    : " + longestSubarrayBruteForce(arr, k));
        System.out.println("Sliding Window Result : " + largestSubarraySumK(arr, k));
    }
}