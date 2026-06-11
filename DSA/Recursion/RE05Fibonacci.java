// Print Fibonacci numbers using recursion
public class RE05Fibonacci {

    // Find nth Fibonacci number
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Print Fibonacci series till n terms
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n)
    public static void printFibonacci(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int n = 5;

        int fifthFibonacci = fibonacci(n);
        System.out.println("5th Fibonacci Number: " + fifthFibonacci);

        System.out.println("Fibonacci Series till " + n + " terms:");
        printFibonacci(n);
    }
}