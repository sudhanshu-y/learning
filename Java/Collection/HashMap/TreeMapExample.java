import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        // Creating TreeMap (Natural Ordering)
        TreeMap<Integer, String> tm = new TreeMap<>();

        // Adding elements
        tm.put(50, "Apple");
        tm.put(10, "Banana");
        tm.put(30, "Cherry");
        tm.put(20, "Mango");
        tm.put(40, "Orange");

        System.out.println("TreeMap (Sorted by Key): " + tm);
        // {10=Banana, 20=Mango, 30=Cherry, 40=Orange, 50=Apple}

        // putIfAbsent
        tm.putIfAbsent(30, "Grapes"); // won't overwrite
        tm.putIfAbsent(60, "Pineapple");
        System.out.println("After putIfAbsent: " + tm);
        // {10=Banana, 20=Mango, 30=Cherry, 40=Orange, 50=Apple, 60=Pineapple}

        // Access
        System.out.println("Get key 30: " + tm.get(30)); // Cherry
        System.out.println("Get key 100 (default): " + tm.getOrDefault(100, "Not Found"));
        // Not Found

        // Search
        System.out.println("Contains Key 20? " + tm.containsKey(20)); // true
        System.out.println("Contains Value 'Mango'? " + tm.containsValue("Mango")); // true

        // Remove
        tm.remove(40);
        System.out.println("After remove(40): " + tm);
        // {10=Banana, 20=Mango, 30=Cherry, 50=Apple, 60=Pineapple}

        // Navigation
        System.out.println("First Key: " + tm.firstKey()); // 10
        System.out.println("Last Key: " + tm.lastKey()); // 60
        System.out.println("Higher Key(30): " + tm.higherKey(30)); // 50
        System.out.println("Lower Key(30): " + tm.lowerKey(30)); // 20
        System.out.println("Ceiling Key(25): " + tm.ceilingKey(25)); // 30
        System.out.println("Floor Key(25): " + tm.floorKey(25)); // 20

        // Sub Maps
        System.out.println("HeadMap(30): " + tm.headMap(30));   // < 30
        // {10=Banana, 20=Mango}
        System.out.println("TailMap(30): " + tm.tailMap(30));   // ≥ 30
        // {30=Cherry, 50=Apple, 60=Pineapple}
        System.out.println("SubMap(15, 45): " + tm.subMap(15, 45)); // [15,45)
        // {20=Mango, 30=Cherry}

        // Traversals
        System.out.print("Keys: ");
        for (Integer k : tm.keySet()) {
            System.out.print(k + " ");
        }
        System.out.println();
        // Keys: 10 20 30 50 60

        System.out.print("Values: ");
        for (String v : tm.values()) {
            System.out.print(v + " ");
        }
        System.out.println();
        // Values: Banana Mango Cherry Apple Pineapple

        System.out.println("Entries (for-each): ");
        for (Map.Entry<Integer, String> entry : tm.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.print("forEach (Lambda): ");
        tm.forEach((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.println();
        // forEach (Lambda): 10=Banana 20=Mango 30=Cherry 50=Apple 60=Pineapple

        // Custom Comparator (Descending Order)
        TreeMap<Integer, String> tmDesc = new TreeMap<>((a, b) -> b - a);
        tmDesc.putAll(tm);
        System.out.println("TreeMap (Descending Order): " + tmDesc);
        // {60=Pineapple, 50=Apple, 30=Cherry, 20=Mango, 10=Banana}

        // Utility
        System.out.println("Size: " + tm.size()); // 5
        System.out.println("IsEmpty? " + tm.isEmpty()); // false

        tm.clear();
        System.out.println("After clear(): " + tm); // {}
    }
}
