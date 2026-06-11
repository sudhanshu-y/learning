import java.util.ArrayList;
import java.util.List;

public class RE09CountSubsequencesWithSumK {

    public static int printAnySubsequencesWithSumK(int[] arr, int targetSum, 
        int curIndex, int curSum, List<Integer> result){

        if(curIndex==arr.length){
            if(curSum==targetSum){
                System.out.println(result);
                return 1;
            }
            return 0;
        }

        // Take
        result.add(arr[curIndex]);
        int left = printAnySubsequencesWithSumK(arr, targetSum, curIndex+1, curSum+arr[curIndex], result);

        // Not take
        result.remove(result.size()-1);
        int right = printAnySubsequencesWithSumK(arr, targetSum, curIndex+1, curSum, result);

        return left + right;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int targetSum = 2;

        int count = printAnySubsequencesWithSumK(arr, targetSum, 0, 0, new ArrayList<>());
        System.out.println("Count = " + count);
    }
}