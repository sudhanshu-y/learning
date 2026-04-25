// Count frequency of each element in the array

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountFrequency {

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        return map;
    }

    public static void main(String[] args) {
        int arr[] = {10, 5, 10, 15, 10, 5};
        System.out.println("Array: " + Arrays.toString(arr));

        Map<Integer, Integer> map = countFrequency(arr);
        System.out.println("Map: " + map);

        System.out.println("Frequency of 5: " + map.get(5));
        System.out.println("Frequency of 15: " + map.get(15));
    }
}