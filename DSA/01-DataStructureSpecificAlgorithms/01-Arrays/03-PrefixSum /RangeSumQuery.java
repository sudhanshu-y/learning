import java.util.Arrays;

public class RangeSumQuery {

    private static int rangeSumQuery(int[] arr, int left, int right) {
        int[] preFixSum = getPreFixSumArray(arr);

        if (left == 0) {
            return preFixSum[right];
        }

        return preFixSum[right] - preFixSum[left - 1];
    }

    private static int[] getPreFixSumArray(int[] arr) {
        int n = arr.length, sum = 0;
        int[] preFixSum = new int[n];
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            preFixSum[i] = sum;
        }
        return preFixSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        System.out.println("Original Array:     " + Arrays.toString(nums));

        int[] preFixSum = getPreFixSumArray(nums);
        System.out.println("Prefix Sum Array:   " + Arrays.toString(preFixSum));

        int q02 = rangeSumQuery(nums, 0, 2);
        int q25 = rangeSumQuery(nums, 2, 5);
        int q05 = rangeSumQuery(nums, 0, 5);

        System.out.println("Range Sum (0, 2): " + q02);
        System.out.println("Range Sum (2, 5): " + q25);
        System.out.println("Range Sum (0, 5): " + q05);
    }
}
