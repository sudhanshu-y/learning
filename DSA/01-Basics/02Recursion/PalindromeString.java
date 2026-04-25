// Check if the given String is Palindrome or not

public class PalindromeString {

    // Iterative Approach
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static boolean isPalindromeIterative(String str) {
        if (str == null || str.length() <= 1) return true;

        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Recursive Approach
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static boolean isPalindromeRecursive(String str, int left, int right) {
        if (left >= right) return true;

        if (str.charAt(left) != str.charAt(right)) return false;

        return isPalindromeRecursive(str, left + 1, right - 1);
    }

    public static void main(String[] args) {

        String str = "ABCDCBA";

        // Using StringBuilder (fixed comparison)
        boolean result1 = new StringBuilder(str).reverse().toString().equals(str);

        // Using iterative method
        boolean result2 = isPalindromeIterative(str);

        // Using recursive method
        boolean result3 = isPalindromeRecursive(str, 0, str.length() - 1);

        System.out.println("Using StringBuilder: " + result1);
        System.out.println("Iterative: " + result2);
        System.out.println("Recursive: " + result3);
    }
}