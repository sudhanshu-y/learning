// Problem Statement: Given an integer N return the reverse of the given number.

public class ReverseDigits {

    // T: O(log10(N))
    // S: O(1)
    public static int reverseDigit(int n) {
        int result = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            result = result * 10 + lastDigit;
            n /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 7789;
        int result = reverseDigit(n);

        System.out.println(result); // Output: 9877
    }
}