import java.util.Arrays;

public class SecondLargestElement {

    public static int getSecondLargest(int[] arr) {
        int max = Integer.MIN_VALUE;

        // find max
        for (int x : arr) {
            if (x > max) {
                max = x;
            }
        }

        int secondMax = Integer.MIN_VALUE;

        // find second max
        for (int x : arr) {
            if (x > secondMax && x != max) {
                secondMax = x;
            }
        }

        return secondMax;
    }

    public static int getSecondLargestOptimal(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int x : arr) {
            if (x > max) {
                secondMax = max;
                max = x;
            } else if (x > secondMax && x != max) {
                secondMax = x;
            }
        }

        return secondMax;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 7, 5};

        System.out.println("Array: " + Arrays.toString(arr));

        int result1 = getSecondLargest(arr);
        int result2 = getSecondLargestOptimal(arr);

        System.out.println("Second Largest (Two Pass): " + result1);
        System.out.println("Second Largest (Optimal): " + result2);
    }
}