// Problem Statement: Given an integer N, return true if it is an Armstrong number, otherwise return false.
// An Armstrong number is a number that is equal to the sum of its own digits each raised to the power of the number of digits.
//
// Example:
// Input: N = 371
// Output: True
// Explanation: 3^3 + 7^3 + 1^3 = 27 + 343 + 1 = 371

public class ArmstrongNumber {

    public static boolean isArmstrong(int n) {
        int digits = (int) Math.log10(n) + 1;
        // int digits = String.valueOf(n).length(); -- as n could be 0. 
        int expected = n;
        int result = 0;

        while (n > 0) {
            int lastDigit = n % 10;
            result += Math.pow(lastDigit, digits);
            n /= 10;
        }

        return expected == result;
    }

    public static void main(String[] args) {
        int n = 371;
        boolean result = isArmstrong(n);

        System.out.println("n = " + n + " is Armstrong? : " + result);
    }
}