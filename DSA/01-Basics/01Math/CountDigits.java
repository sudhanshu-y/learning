// Problem Statement: Given an integer N, return the number of digits in N.

public class CountDigits {

    // T: O(log10(N))
    // S: O(1)
    private static int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            count += 1;
            n /= 10;
        }
        return count;
    }

    // T: O(1)
    // S: O(1)
    private static int countDigitsOptimal(int n) {
        return (int) Math.log10(n) + 1;
    }

    public static void main(String[] args) {
        int n = 329823;

        int result = countDigits(n);
        System.out.println("n = " + n + " has " + result + " digits.");

        int optimalResult = countDigitsOptimal(n);
        System.out.println("[Optimal]: n = " + n + " has " + optimalResult + " digits.");
    }
}