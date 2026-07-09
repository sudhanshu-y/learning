import java.util.Arrays;

public class LongestSubarrayWithSumK {

    public static int longestSubarrayWithSumK(int[] arr, int targetSum){
        int n = arr.length;
        int max = 0, sum = 0;
        for(int i=0;i<n;i++){
            sum=0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum==targetSum && max<=sum){
                    max = j - i + 1;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        System.out.println(Arrays.toString(arr));

        System.out.println(longestSubarrayWithSumK(arr, 15)); // 4

    }
}
