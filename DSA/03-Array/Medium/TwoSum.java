// Two Sum: Check if a pair with given sum exists in Array
// Return indices of the two numbers such that their sum is equal to the target. Otherwise, we will return {-1, -1}.
//
// Example:
// Input : arr = [2, 6, 5, 8, 11], target = 14
// Output: [1, 3]
//
// Explanation:
// arr[1] + arr[3] = 6 + 8 = 14.
//
// ------------------------------------------------------------
// Approach 1: Brute Force
// ------------------------------------------------------------
// Check every possible pair.
//
// Time Complexity : O(n²)
// Space Complexity: O(1)
//
// ------------------------------------------------------------
// Approach 2: HashMap (Optimal)
// ------------------------------------------------------------
// Store each element and its index in a HashMap.
// For every element, check whether (target - currentElement)
// already exists in the map.
//
// Time Complexity : O(n)
// Space Complexity: O(n)

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // Brute Force
    public static int[] twoSumBruteForce(int[] arr, int target) {

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    // HashMap 
    public static int[] twoSum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int required = target - arr[i];

            if (map.containsKey(required)) {
                return new int[]{map.get(required), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Array  : " + Arrays.toString(arr));
        System.out.println("Target : " + target);

        System.out.println("Brute Force : " + Arrays.toString(twoSumBruteForce(arr, target)));
        System.out.println("HashMap : " + Arrays.toString(twoSum(arr, target)));
    }
}
