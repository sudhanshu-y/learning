public class RE1BasicProblems {

    // Print a string n times using recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void printString(String str, int n) {
        if (n <= 0) return;

        System.out.println(str);
        printString(str, n - 1);
    }

    // Print numbers from 1 to N
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void print1ToN(int n, int currentVal) {
        if (currentVal > n) return;

        System.out.println(currentVal);
        print1ToN(n, currentVal + 1);
    }

    // Print numbers from N to 1
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void printNTo1(int n) {
        if (n <= 0) return;

        System.out.println(n);
        printNTo1(n - 1);
    }

    // Print 1 to N using backtracking
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void print1ToNBacktracking(int n) {
        if (n <= 0) return;

        print1ToNBacktracking(n - 1);
        System.out.println(n);
    }

    // Print N to 1 using backtracking
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void printNTo1Backtracking(int n, int currentVal) {
        if (currentVal > n) return;

        printNTo1Backtracking(n, currentVal + 1);
        System.out.println(currentVal);
    }

    public static void main(String[] args) {

        int n = 5;
        String str = "anshu";

        System.out.println("Printing string n times:");
        printString(str, n);

        System.out.println("Printing 1 to N:");
        print1ToN(n, 1);

        System.out.println("Printing N to 1:");
        printNTo1(n);

        System.out.println("Printing 1 to N using backtracking:");
        print1ToNBacktracking(n);

        System.out.println("Printing N to 1 using backtracking:");
        printNTo1Backtracking(n, 1);
    }
}