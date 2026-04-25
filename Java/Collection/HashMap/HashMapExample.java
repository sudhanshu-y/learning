import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Creating HashMap
        HashMap<Integer, String> map = new HashMap<>();

        // Adding elements
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        map.put(4, "Mango");
        map.put(2, "Grapes"); // Duplicate key → value overwritten

        System.out.println("Initial Map: " + map);
        // {1=Apple, 2=Grapes, 3=Cherry, 4=Mango}

        // putIfAbsent
        map.putIfAbsent(2, "Banana");
        map.putIfAbsent(5, "Orange");
        System.out.println("After putIfAbsent: " + map);
        // {1=Apple, 2=Grapes, 3=Cherry, 4=Mango, 5=Orange}

        // computeIfAbsent
        map.computeIfAbsent(6, k -> "Pineapple");   // added
        map.computeIfAbsent(2, k -> "Banana");      // not added (key exists)
        System.out.println("After computeIfAbsent: " + map);
        // {1=Apple, 2=Grapes, 3=Cherry, 4=Mango, 5=Orange, 6=Pineapple}

        // Access
        System.out.println("Get key 3: " + map.get(3)); // Cherry
        System.out.println("Get key 10 (default): " + map.getOrDefault(10, "Not Found"));
        // Not Found

        // Search
        System.out.println("Contains Key 1? " + map.containsKey(1)); // true
        System.out.println("Contains Value 'Mango'? " + map.containsValue("Mango")); // true

        // Remove
        map.remove(4);
        System.out.println("After remove(4): " + map);
        // {1=Apple, 2=Grapes, 3=Cherry, 5=Orange, 6=Pineapple}

        map.remove(2, "Grapes"); // remove only if exact match
        System.out.println("After remove(2, Grapes): " + map);
        // {1=Apple, 3=Cherry, 5=Orange, 6=Pineapple}

        // Replace
        map.replace(3, "Cherry", "Blueberry");
        System.out.println("After replace: " + map);
        // {1=Apple, 3=Blueberry, 5=Orange, 6=Pineapple}

        // Traversals
        System.out.print("Keys: ");
        for (Integer k : map.keySet()) {
            System.out.print(k + " ");
        }
        System.out.println();
        // Keys: 1 3 5 6

        System.out.print("Values: ");
        for (String v : map.values()) {
            System.out.print(v + " ");
        }
        System.out.println();
        // Values: Apple Blueberry Orange Pineapple

        System.out.println("Entries (for-each): ");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.print("forEach (Lambda): ");
        map.forEach((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.println();
        // forEach (Lambda): 1=Apple 3=Blueberry 5=Orange 6=Pineapple 

        // Utility
        System.out.println("Size: " + map.size()); // 4
        System.out.println("IsEmpty? " + map.isEmpty()); // false

        map.clear();
        System.out.println("After clear(): " + map); // {}
    }
}
