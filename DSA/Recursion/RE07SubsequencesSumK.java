import java.util.ArrayList;
import java.util.List;

public class RE07SubsequencesSumK {

    public static void printSubsequencesWithSumK(int[] arr, int targetSum, 
        int curIndex, int curSum, List<Integer> result) {

        if (curIndex == arr.length) {
            if (curSum == targetSum) {
                System.out.println(result);
            }
            return;
        }

        // Take
        result.add(arr[curIndex]);
        printSubsequencesWithSumK(arr, targetSum, curIndex + 1, curSum + arr[curIndex], result);

        // Backtrack
        result.remove(result.size() - 1);

        // Not take
        printSubsequencesWithSumK(arr, targetSum, curIndex + 1, curSum, result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int targetSum = 2;

        printSubsequencesWithSumK(arr, targetSum, 0, 0, new ArrayList<>());
    }
}