// Factorial of a Number
// n! = n * (n - 1) * (n - 2) * ... * 1  for n > 0

public class Factorial {

    // Iterative Approach
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static int factorialIterative(int n) {
        int ans = 1;

        for (int i = 1; i <= n; i++) {
            ans *= i;
        }

        return ans;
    }

    // Recursive Approach
    // Time Complexity: O(N)
    // Space Complexity: O(N) (recursion stack)
    public static int factorialRecursive(int n) {
        if (n <= 1) return 1;

        return n * factorialRecursive(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;

        int iterativeResult = factorialIterative(n);
        int recursiveResult = factorialRecursive(n);

        System.out.println("Factorial (Iterative): " + iterativeResult);
        System.out.println("Factorial (Recursive): " + recursiveResult);
    }
}