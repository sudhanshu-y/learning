import java.util.ArrayList;
import java.util.List;

public class RE08AnySubsequencesWithSumK {

    public static boolean printAnySubsequencesWithSumK(int[] arr, int targetSum, 
        int curIndex, int curSum, List<Integer> result){

        if(curIndex==arr.length){
            if(curSum==targetSum){
                System.out.println(result);
                return true;
            }
            return false;
        }

        // Take
        result.add(arr[curIndex]);
        if(printAnySubsequencesWithSumK(arr, targetSum, curIndex+1, curSum+arr[curIndex], result)){
            return true;
        }

        // Not take
        result.remove(result.size()-1);
        if(printAnySubsequencesWithSumK(arr, targetSum, curIndex+1, curSum, result)){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int targetSum = 2;

        printAnySubsequencesWithSumK(arr, targetSum, 0, 0, new ArrayList<>());
    }
}