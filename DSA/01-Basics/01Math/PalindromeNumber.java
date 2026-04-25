// Problem Statement: Given an integer N, return true if it is a palindrome else return false.
// A palindrome is a number that reads the same backward as forward.
// Example: 121, 1331, and 4554 are palindromes because they remain the same when reversed.

public class PalindromeNumber {

    // Time Complexity: O(log10(N))
    // Space Complexity: O(1)
    public static boolean isPalindrome(int n) {
        int finalResult = n;
        int result = 0;

        while (n > 0) {
            int lastDigit = n % 10;
            result = result * 10 + lastDigit;
            n /= 10;
        }

        return finalResult == result;
    }

    public static void main(String[] args) {
        int n = 1331;
        boolean result = isPalindrome(n);

        System.out.println("n = " + n + " is palindrome? : " + result);
    }
}