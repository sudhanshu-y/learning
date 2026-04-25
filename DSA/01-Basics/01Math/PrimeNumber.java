// Problem Statement: Given an integer N, check whether it is prime or not.
// A prime number is a number that is only divisible by 1 and itself. So, the total number of divisors is 2.

public class PrimeNumber {

    // Brute Force Approach
    // T: O(N)
    // S: O(1)
    public static boolean isPrimeNumber(int n) {
        if (n <= 1) return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // If n has a factor greater than its square root, it must also have a factor smaller than its square root.
    // T: O(√N)
    // S: O(1)
    public static boolean isPrimeNumberOptimal(int n) {
        if (n <= 1) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 1483;

        boolean brute = isPrimeNumber(n);
        boolean optimal = isPrimeNumberOptimal(n);

        System.out.println("Is " + n + " prime? (Brute): " + brute);
        System.out.println("Is " + n + " prime? (Optimal): " + optimal);
    }
}