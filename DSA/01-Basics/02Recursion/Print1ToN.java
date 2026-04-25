// Print 1 to N using Recursion
// Given an integer N, write a program to print numbers from 1 to N.

// Example
// Input: N = 4
// Output: 1, 2, 3, 4
// Explanation: All the numbers from 1 to 4 are printed.

public class Print1ToN {

    // Normal Recursion
    // T: O(N)
    // S: O(N)
    public static void printRecursive(int n, int count) {
        if (count > n) return;

        System.out.println(count);
        printRecursive(n, count + 1);
    }

    // Backtracking
    // The function recursively calls itself with the next number until it passes n. 
    // After reaching the base case, it prints the numbers while returning from the recursion.
    // T: O(N)
    // S: O(N)
    public static void printBacktracking(int n, int count) {
        if (count > n) return;

        printBacktracking(n, count + 1);
        System.out.println(count);
    }

    public static void main(String[] args) {
        int n = 3;

        System.out.println("Normal Recursion:");
        printRecursive(n, 1);

        System.out.println("Backtracking:");
        printBacktracking(n, 1);
    }
}