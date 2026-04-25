import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Find the maximum length of a contiguous subarray with an equal number of 0s and 1s.
public class ContiguousArray {

    private static int maxContiguousSubarray(int[] arr) {
        if (arr.length <= 1)
            return 0;
        
        // Replace 0 with -1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        System.out.println("Converted Array (0 -> -1): " + Arrays.toString(arr));

        // Build prefix sum array
        int[] preFixSum = new int[arr.length];
        preFixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preFixSum[i] = preFixSum[i - 1] + arr[i];
        }
        System.out.println("Prefix Sum Array:          " + Arrays.toString(preFixSum));

        // HashMap to store prefix sum -> first index
        Map<Integer, Integer> sumToIndexMap = new HashMap<>();
        // Prefix sum 0 at index -1 helps handle subarrays starting at index 0
        sumToIndexMap.put(0, -1);

        int maxLen = 0;

        for (int i = 0; i < preFixSum.length; i++) {
            // If prefix sum already exists, calculate subarray length
            if (sumToIndexMap.containsKey(preFixSum[i])) {
                maxLen = Math.max(maxLen, i - sumToIndexMap.get(preFixSum[i]));
            } 
            else {
                // Store only the first occurrence to maximize length
                sumToIndexMap.put(preFixSum[i], i);
            }
        }

        return maxLen;
    }

    private static int maxContiguousSubarrayLength(int[] arr) {
        if (arr.length <= 1) return 0;

        Map<Integer, Integer> sumToIndexMap = new HashMap<>();
        sumToIndexMap.put(0, -1);

        int sum = 0, maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += (arr[i] == 1) ? 1 : -1;

            if (sumToIndexMap.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - sumToIndexMap.get(sum));
            } else {
                sumToIndexMap.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 1, 1, 0, 0, 0};
        System.out.println("Original Array:            " + Arrays.toString(arr));

        int maxLength = maxContiguousSubarray(arr.clone());
        System.out.println("Maximum Contiguous Length: " + maxLength);

        int maxLength2 = maxContiguousSubarrayLength(arr.clone());
        System.out.println("Max Length (Optimized):    " + maxLength2);
    }
}
