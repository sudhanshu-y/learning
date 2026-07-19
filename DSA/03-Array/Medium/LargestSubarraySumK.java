// Longest Subarray with Given Sum K (Positive, Negative & Zero)
//
// Example:
// Input : nums = [10, 5, 2, 7, 1, 9], k = 15
// Output: 4
//
// Example:
// Input : nums = [-1, 1, 1], k = 1
// Output: 3
//
// Explanation:
// The longest subarray with sum = k is returned.
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
// Approach 2: Prefix Sum + HashMap 
// ------------------------------------------------------------
// Works for positive, negative and zero values.
//
// 1. Maintain a running prefix sum.
// 2. If prefixSum == k, update the answer.
// 3. If (prefixSum - k) has been seen before, then the subarray between those indices has sum = k.
// 4. Store only the first occurrence of each prefix sum.
//
// Time Complexity : O(n)
// Space Complexity: O(n)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubarraySumK {

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

    // Prefix Sum + HashMap
    public static int largestSubarraySumK(int[] arr, int k) {

        // <K, V> = <sum, index>
        Map<Integer, Integer> map = new HashMap<>();

        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {

            // Calculate prefix sum
            prefixSum += arr[i];

            // If subarray starts from index 0
            if (prefixSum == k) {
                maxLen = i + 1;
            }

            // Check if (prefixSum - k) exists
            if (map.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - map.get(prefixSum - k));
            }

            // Store first occurrence only
            map.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }

    public static void main(String[] args) {

        int[] arr = {10, 5, 2, 7, 1, 9};
        int k = 15;

        System.out.println("Array : " + Arrays.toString(arr));
        System.out.println("K     : " + k);

        System.out.println("Brute Force Result : " + longestSubarrayBruteForce(arr, k));
        System.out.println("Optimal Result : " + largestSubarraySumK(arr, k));
    }
}