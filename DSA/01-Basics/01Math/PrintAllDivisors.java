// Problem Statement: Given an integer N, return all divisors of N.
// A divisor of an integer N is a positive integer that divides N without leaving a remainder.
//
// Example:
// Input: N = 12
// Output: [1, 2, 3, 4, 6, 12]

import java.util.ArrayList;
import java.util.List;

public class PrintAllDivisors {

    // T: O(N)
    // S: O(N)
    public static List<Integer> getAllDivisors(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }

        return result;
    }

    // For any non-negative integer n, if d is a divisor of n then n/d is also a divisor of n. 
    // This property is symmetric about the square root of N.
    // T: O(√N)
    // S: O(N)
    public static List<Integer> getAllDivisorsOptimal(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                result.add(i);

                if (i != n / i) {
                    result.add(n / i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 36;

        List<Integer> brute = getAllDivisors(n);
        List<Integer> optimal = getAllDivisorsOptimal(n);

        System.out.println("Brute Force Divisors: " + brute);
        System.out.println("Optimal Divisors: " + optimal);
    }
}