import java.util.Arrays;

public class TwoSumII {

    private static int[] getTwoSumIndexes(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                // Return 1-based indices
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15}; // array must be sorted
        int target = 9;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int[] result = getTwoSumIndexes(arr, target);
        System.out.println("Result indices: " + Arrays.toString(result));
    }
}
