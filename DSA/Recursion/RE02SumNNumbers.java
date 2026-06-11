// Sum of first N natural numbers using recursion
public class RE02SumNNumbers {

    // Parameterized recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void sumRecursiveParameterized(int n, int sum) {
        if (n == 0) {
            System.out.println("Sum using parameterized recursion: " + sum);
            return;
        }
        sumRecursiveParameterized(n - 1, sum + n);
    }

    // Functional recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int sumRecursiveFunctional(int n) {
        if (n == 0) return 0;
        return n + sumRecursiveFunctional(n - 1);
    }

    public static void main(String[] args) {

        int n = 100;

        // Parameterized recursion
        sumRecursiveParameterized(n, 0);

        // Functional recursion
        int result = sumRecursiveFunctional(n);
        System.out.println("Sum using functional recursion: " + result);
    }
}