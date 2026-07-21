// Kadane's Algorithm : Maximum Subarray Sum in an Array
// Given an integer array nums, find the subarray with the largest sum and return the sum of the elements present in that subarray.
// A negative running sum can never help increase the sum of any future subarray.
class KadanesAlgorithmMaximumSubarraySum {

    // ------------------------------------------------------------
    // Approach 1: Brute Force
    // ------------------------------------------------------------
    // Generate all possible subarrays.
    // Calculate the sum of each subarray.
    // Keep track of the maximum sum.
    //
    // Time Complexity: O(N²)
    // Space Complexity: O(1)
    public static int maxSubArrayBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;

            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    // ------------------------------------------------------------
    // Approach 2: Kadane's Algorithm: A negative running sum can never help increase the sum of any future subarray.
    // ------------------------------------------------------------
    // Maintain:
    // - currentSum = Sum of current subarray
    // - maxSum = Maximum sum found so far
    //
    // For each element:
    // - Add current element to currentSum.
    // - Update maxSum.
    // - If currentSum becomes negative, reset it to 0.
    //
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static int maxSubArrayKadane(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int num : nums) {
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);

            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        return maxSum;
    }


    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Brute Force: " + maxSubArrayBruteForce(nums));
        System.out.println("Kadane's Algorithm: " + maxSubArrayKadane(nums));
    }
}