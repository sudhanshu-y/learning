// Problem Statement: Given two integers N1 and N2, find their greatest common divisor.
// The GCD of two numbers is the largest number that divides both of them without leaving a remainder. 

// Example: 
// Input: N1 = 20, N2 = 15
// Output: 5
// Explanation:
// Factors of 20: 1, 2, 4, 5, 10, 20
// Factors of 15: 1, 3, 5, 15
// Common Factors: 1, 5
// Greatest common factor: 5 (GCD)

public class GCD {

    // T: O(min(N1, N2))
    // S: O(1)
    public static int gcd(int n1, int n2) {
        int result = 1;
        int min = Math.min(n1, n2);

        for (int i = 1; i <= min; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                result = i;
            }
        }

        return result;
    }

    // Early exit
    // T: O(min(N1, N2))
    // S: O(1)
    public static int gcdOptimal(int n1, int n2) {
        int min = Math.min(n1, n2);

        for (int i = min; i > 0; i--) {
            if (n1 % i == 0 && n2 % i == 0) {
                return i;
            }
        }

        return 1;
    }

    // Euclidean Algorithm 
    // GCD of two numbers remains the same even if the smaller number is subtracted from the larger number.

    // Euclidean Algorithm Iterative
    // T: O(log(min(N1, N2)))
    // S: O(1)
    public static int gcdEuclidean(int n1, int n2) {
        while (n2 > 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1;
    }

    // Euclidean Algorithm Recursive
    // T: O(log(min(N1, N2)))
    // S: O(log(min(N1, N2))) due to recursion stack
    public static int gcdEuclideanRecursive(int n1, int n2) {
        if (n2 == 0) return n1;
        return gcdEuclideanRecursive(n2, n1 % n2);
    }

    public static void main(String[] args) {
        int n1 = 20, n2 = 15;

        System.out.println("GCD (Brute) = " + gcd(n1, n2));
        System.out.println("GCD (Better) = " + gcdOptimal(n1, n2));
        System.out.println("GCD (Euclidean Iterative) = " + gcdEuclidean(n1, n2));
        System.out.println("GCD (Euclidean Recursive) = " + gcdEuclideanRecursive(n1, n2));
    }
}