import java.util.ArrayList;
import java.util.List;

public class RE06Subsequences {

    // Time Complexity: O(n · 2ⁿ)
    // Space Complexity: O(n)
    public static void printSubsequences(String str, int index, String subStr) {
        if (index == str.length()) {
            System.out.println(subStr);
            return;
        }
        // Take current character
        printSubsequences(str, index + 1, subStr + str.charAt(index));
        // Not Take current character
        printSubsequences(str, index + 1, subStr);
    }

    // Time Complexity: O(n · 2ⁿ)
    // Space Complexity: O(n) + O(n × 2ⁿ) for result storage
    public static void getSubsequences(String str, int index, String subStr, List<String> subStrs) {
        if (index == str.length()) {
            subStrs.add(subStr);
            return;
        }
        // Take current character
        getSubsequences(str, index + 1, subStr + str.charAt(index), subStrs);
        // Not Take current character
        getSubsequences(str, index + 1, subStr, subStrs);
    }

    public static void main(String[] args) {
        String str = "abc";

        System.out.println("Printing subsequences:");
        printSubsequences(str, 0, "");

        List<String> list = new ArrayList<>();
        getSubsequences(str, 0, "", list);

        System.out.println("Subsequences in List:");
        System.out.println(list);
    }
}