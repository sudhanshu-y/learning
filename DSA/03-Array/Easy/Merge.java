import java.util.Arrays;

public class Merge {

    public static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            int val;

            if (arr1[i] <= arr2[j]) {
                val = arr1[i++];
            } else {
                val = arr2[j++];
            }

            if (k == 0 || result[k - 1] != val) {
                result[k++] = val;
            }
        }

        while (i < n1) {
            if (k == 0 || result[k - 1] != arr1[i]) {
                result[k++] = arr1[i];
            }
            i++;
        }

        while (j < n2) {
            if (k == 0 || result[k - 1] != arr2[j]) {
                result[k++] = arr2[j];
            }
            j++;
        }

        return Arrays.copyOf(result, k); // trim extra space
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = {2,3,4,4,5,11,12};

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int[] result = merge(arr1, arr2);
        System.out.println(Arrays.toString(result));
    }
}