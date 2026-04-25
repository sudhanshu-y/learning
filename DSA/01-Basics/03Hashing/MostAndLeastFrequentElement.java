// Find the highest/lowest frequency element

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MostAndLeastFrequentElement {

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        return map;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void main(String[] args) {
        int arr[] = {10, 5, 10, 15, 10, 5};
        System.out.println("Array: " + Arrays.toString(arr));

        Map<Integer, Integer> map = countFrequency(arr);
        System.out.println("Map: " + map);

        int maxFreq = Integer.MIN_VALUE, minFreq = Integer.MAX_VALUE;
        int maxElement = Integer.MIN_VALUE, minElement = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();

            if (v > maxFreq) {
                maxFreq = v;
                maxElement = k;
            }

            if (v < minFreq) {
                minFreq = v;
                minElement = k;
            }
        }

        System.out.println("Max Frequency Element: " + maxElement + " -> " + maxFreq);
        System.out.println("Min Frequency Element: " + minElement + " -> " + minFreq);
    }
}