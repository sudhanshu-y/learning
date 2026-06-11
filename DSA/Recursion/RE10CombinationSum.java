import java.util.ArrayList;
import java.util.List;

public class RE10CombinationSum {

    public static void findCombinations(int[] arr, int target, int index,
        List<Integer> ds, List<List<Integer>> result) {

        if (target < 0) return;

        if (index == arr.length) {
            if (target == 0) {
                result.add(new ArrayList<>(ds)); // copy the list. Do not just put references. 
            }
            return;
        }

        // Pick
        if (arr[index] <= target) {
            ds.add(arr[index]);
            findCombinations(arr, target - arr[index], index, ds, result);
            ds.remove(ds.size() - 1);
        }

        // Not Pick
        findCombinations(arr, target, index + 1, ds, result);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = new ArrayList<>();

        findCombinations(arr, target, 0, new ArrayList<>(), result);

        System.out.println(result);
    }
}