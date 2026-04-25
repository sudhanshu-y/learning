// Print Name N times using Recursion
// Given an integer N, write a program to print your name N times.

public class PrintName {

    public static void printNameRecursive(int n, String name) {
        if (n <= 0) return;

        System.out.println(name);
        printNameRecursive(n - 1, name);
    }

    public static void main(String[] args) {
        int n = 3;
        String name = "Ramsy";

        printNameRecursive(n, name);
    }
}