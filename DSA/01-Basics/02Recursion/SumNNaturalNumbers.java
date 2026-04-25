// Sum of first N Natural Numbers
// Problem Statement: Given a number ‘N’, find the sum of the first N natural numbers.

public class SumNNaturalNumbers {

    // Recursive Approach
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static int sumNNaturalNumbers(int n) {
        if (n <= 0) return 0;

        return n + sumNNaturalNumbers(n - 1);
    }

    public static void main(String[] args) {
        int n = 100;

        // Formula approach (O(1))
        System.out.println("Using formula: " + (n * (n + 1) / 2));

        // Recursive approach
        System.out.println("Using recursion: " + sumNNaturalNumbers(n));
    }
}