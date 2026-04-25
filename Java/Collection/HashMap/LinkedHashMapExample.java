import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // Creating LinkedHashMap
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // Adding elements
        lhm.put(1, "Apple");
        lhm.put(2, "Banana");
        lhm.put(3, "Cherry");
        lhm.put(4, "Mango");
        lhm.put(2, "Grapes"); // duplicate key → value overwritten

        System.out.println("Initial LinkedHashMap: " + lhm);
        // Maintains insertion order: {1=Apple, 2=Grapes, 3=Cherry, 4=Mango}

        // putIfAbsent
        lhm.putIfAbsent(2, "Banana"); // won't overwrite existing
        lhm.putIfAbsent(5, "Orange");
        System.out.println("After putIfAbsent: " + lhm);
        // {1=Apple, 2=Grapes, 3=Cherry, 4=Mango, 5=Orange}

        // Access
        System.out.println("Get key 3: " + lhm.get(3)); // Cherry
        System.out.println("Get key 10 (default): " + lhm.getOrDefault(10, "Not Found"));
        // Not Found

        // Search
        System.out.println("Contains Key 1? " + lhm.containsKey(1)); // true
        System.out.println("Contains Value 'Mango'? " + lhm.containsValue("Mango")); // true

        // Remove
        lhm.remove(4);
        System.out.println("After remove(4): " + lhm);
        // {1=Apple, 2=Grapes, 3=Cherry, 5=Orange}

        lhm.remove(2, "Grapes");
        System.out.println("After remove(2, Grapes): " + lhm);
        // {1=Apple, 3=Cherry, 5=Orange}

        // Replace
        lhm.replace(3, "Cherry", "Blueberry");
        System.out.println("After replace: " + lhm);
        // {1=Apple, 3=Blueberry, 5=Orange}

        // Traversals
        System.out.print("Keys: ");
        for (Integer k : lhm.keySet()) {
            System.out.print(k + " ");
        }
        System.out.println();
        // Keys: 1 3 5 

        System.out.print("Values: ");
        for (String v : lhm.values()) {
            System.out.print(v + " ");
        }
        System.out.println();
        // Values: Apple Blueberry Orange

        System.out.println("Entries (for-each): ");
        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.print("forEach (Lambda): ");
        lhm.forEach((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.println();
        // forEach (Lambda): 1=Apple 3=Blueberry 5=Orange

        // Utility
        System.out.println("Size: " + lhm.size()); // 3
        System.out.println("IsEmpty? " + lhm.isEmpty()); // false

        lhm.clear();
        System.out.println("After clear(): " + lhm); // {}
    }
}
