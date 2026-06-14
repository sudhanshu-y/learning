/**
 * Array contains numbers from 0 to n.
 * Exactly one number is missing.
 *
 * Example: [9,6,4,2,3,5,7,0,1]
 * Missing Number: 8
 */
import java.util.Arrays;

public class MissingNumber {

    // Missing Number = Expected Sum - Actual Sum
    // Time  : O(n)
    // Space : O(1)
    public static int missingNumber(int[] arr) {

        int n = arr.length;

        // Use long to avoid overflow
        long expectedSum = (long) n * (n + 1) / 2;

        long actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return (int) (expectedSum - actualSum);
    }

    // Missing Number Using XOR
    // a ^ a = 0
    // a ^ 0 = a
    // XOR all numbers from 0 to n.
    // XOR all array elements.
    // (0^1^2^3^4^5^6^7^8^9) ^ (9^6^4^2^3^5^7^0^1) = 8
    // Time  : O(n)
    // Space : O(1)
    public static int missingNumber2(int[] arr) {

        int n = arr.length;

        int xorArray = 0;
        int xorRange = 0;

        for (int num : arr) {
            xorArray ^= num;
        }

        for (int i = 0; i <= n; i++) {
            xorRange ^= i;
        }

        return xorArray ^ xorRange;
    }

    // Missing Number Using XOR (Single Loop)
    //
    // Example:
    // xor = n
    // xor ^= i
    // xor ^= arr[i]
    //
    // All matching values cancel out.
    //
    // Time  : O(n)
    // Space : O(1)
    public static int missingNumber3(int[] arr) {

        int n = arr.length;

        int xor = n;

        for (int i = 0; i < n; i++) {
            xor ^= (i^arr[i]);
        }

        return xor;
    }

    public static void main(String[] args) {

        int[] arr = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        System.out.println("==================================================");
        System.out.println("MISSING NUMBER");
        System.out.println("==================================================");

        System.out.println("Array : " + Arrays.toString(arr));

        System.out.println("\nUsing Sum Formula:");
        System.out.println("Missing Number : " + missingNumber(arr));

        System.out.println("\nUsing XOR:");
        System.out.println("Missing Number : " + missingNumber2(arr));

        System.out.println("\nUsing XOR (Single Loop):");
        System.out.println("Missing Number : " + missingNumber3(arr));
    }
}