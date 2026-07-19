// Find the Majority Element (> N/2 times)
// Given an integer array of size N, find the element that appears more than N/2 times. 
// If no such element exists, return -1.
//
// Example:
// Input : arr = [2, 2, 1, 1, 2, 2, 2]
// Output: 2
//
// Explanation:
// Element 2 appears 5 times, which is more than N/2 (=3).
//
// ------------------------------------------------------------
// Approach 1: HashMap (Frequency Count)
// ------------------------------------------------------------
// Store the frequency of each element in a HashMap.
// As soon as any element's frequency becomes greater than N/2, return that element.
//
// Time Complexity : O(n)
// Space Complexity: O(n)
//
// ------------------------------------------------------------
// Approach 2: Moore's Voting Algorithm 
// ------------------------------------------------------------
// Step 1: Find a candidate using Moore's Voting Algorithm.
// Step 2: Verify whether the candidate appears more than N/2 times.
//
// Moore's Voting Algorithm repeatedly cancels out pairs of different elements. 
// Since the majority element appears more than all other elements combined, 
// it cannot be completely cancelled (count = 0) and will always remain as the final candidate.
// 
// Time Complexity : O(n)
// Space Complexity: O(1)

import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement {

    // HashMap Approach
    public static int majorityElementHashMap(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length / 2;

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) > n) {
                return num;
            }
        }

        return -1;
    }

    // Moore's Voting Algorithm
    public static int majorityElement(int[] arr) {

        int candidate = -1;
        int count = 0;

        // Step 1: Find Candidate
        for (int num : arr) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Step 2: Verify Candidate
        count = 0;

        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }

        return (count > arr.length / 2) ? candidate : -1;
    }

    public static void main(String[] args) {

        int[] arr = {2, 2, 1, 1, 2, 2, 2};

        System.out.println("Array : " + Arrays.toString(arr));

        System.out.println("HashMap : " + majorityElementHashMap(arr));
        System.out.println("Optimal : " + majorityElement(arr));
    }
}