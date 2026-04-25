import java.util.Arrays;

public class LinearSearch {

    public static int linearSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {45, 12, 78, 34, 23};
        System.out.println("Original array: " + Arrays.toString(arr));

        int index = linearSearch(arr, 34);

        if (index != -1)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found");
    }
}
