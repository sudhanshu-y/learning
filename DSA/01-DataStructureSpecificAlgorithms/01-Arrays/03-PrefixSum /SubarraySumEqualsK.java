import java.util.Arrays;
import java.util.HashMap;

public class SubarraySumEqualsK {
    public static int subArraySum(int[] arr, int K){
        // <K, V> = <preFixSum, preFixSumCount>
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int preFixSum = 0, count=0;

        for(int i=0;i<arr.length;i++){
            preFixSum+=arr[i];
            if(map.containsKey(preFixSum-K)){
                int freq = map.get(preFixSum-K);
                count+=freq;
            }
            map.put(preFixSum, map.getOrDefault(preFixSum, 0)+1);
        }
        System.out.println("Prefix Sum Map: " + map);
        return count;
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, 7, 2, -3, 1, 4, 2};
        System.out.println("Input Array: " + Arrays.toString(arr));

        int K = 7;
        int result = subArraySum(arr, K);
        System.out.println("Total subarrays with sum " + K + " = " + result);
    }
}
