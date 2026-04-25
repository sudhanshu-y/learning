// Print N to 1 using Recursion
// Given an integer N, write a program to print numbers from N to 1.

public class PrintNTo1 {

    // Normal Recursion (N → 1)
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static void printRecursive(int n) {
        if (n <= 0) return;

        System.out.println(n);
        printRecursive(n - 1);
    }

    // Backtracking (actually prints 1 → N)
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static void printBacktracking(int n) {
        if (n <= 0) return;

        printBacktracking(n - 1);
        System.out.println(n);
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println("N to 1:");
        printRecursive(n);

        System.out.println("1 to N (Backtracking):");
        printBacktracking(n);
    }
}