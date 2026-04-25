// Print Fibonacci Series up to Nth term

// Example:
// Input: N = 5
// Output: 0 1 1 2 3 5
// Explanation: 0 1 1 2 3 5 is the fibonacci series up to 5th term.(0 based indexing)

public class FibonacciSeries {

    // Iterative approach
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void fibonacciSeriesIterative(int n) {
        int a = 0, b = 1;

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " -> " + a + " ");

            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    // Recursive Approach
    // Level 0 → 1 call
    // Level 1 → 2 calls
    // Level 2 → 4 calls
    // Level 3 → 8 calls
    // ...
    // Level k → 2^k calls
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    public static int fibonacciNumber(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;

        return fibonacciNumber(n - 1) + fibonacciNumber(n - 2);
    }

    // Prints Fibonacci series using recursion
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n)
    public static void fibonacciSeries(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " -> " + fibonacciNumber(i));
        }
    }

    public static void main(String[] args) {
        int n = 10;

        System.out.println("Recursive Series:");
        fibonacciSeries(n);

        System.out.println("\nIterative Series:");
        fibonacciSeriesIterative(n);
    }
}