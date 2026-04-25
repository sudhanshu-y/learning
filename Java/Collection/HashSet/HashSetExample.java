import java.util.HashSet;
import java.util.Iterator;

public class HashSetExample {
    public static void main(String[] args) {
        // Creating HashSet
        HashSet<Integer> hs = new HashSet<>();

        // Adding elements
        hs.add(10);
        hs.add(20);
        hs.add(30);
        hs.add(20); // duplicate, will be ignored
        hs.add(null); // allows one null
        System.out.println("Initial Set: " + hs);
        // [null, 20, 10, 30]

        // Search / Check
        System.out.println("Contains 20? " + hs.contains(20)); // true
        System.out.println("Size: " + hs.size()); // 4
        System.out.println("Is Empty? " + hs.isEmpty()); // false

        // Removing elements
        hs.remove(30);
        System.out.println("After remove(30): " + hs);
        // [null, 20, 10]

        // Traversals
        System.out.print("For-Each Loop: ");
        for (Integer x : hs) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.print("Iterator: ");
        Iterator<Integer> itr = hs.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();

        System.out.print("forEach (Lambda): ");
        hs.forEach(x -> System.out.print(x + " "));
        System.out.println();

        // Set Operations
        HashSet<Integer> hs2 = new HashSet<>();
        hs2.add(10);
        hs2.add(40);
        hs2.add(50);

        // Union: A U B
        HashSet<Integer> union = new HashSet<>(hs);
        union.addAll(hs2);
        System.out.println("Union: " + union);
        // [null, 50, 20, 40, 10]

        // Intersection
        HashSet<Integer> intersection = new HashSet<>(hs);
        intersection.retainAll(hs2);
        System.out.println("Intersection: " + intersection);
        // [10]

        // Difference: A-B
        HashSet<Integer> difference = new HashSet<>(hs);
        difference.removeAll(hs2);
        System.out.println("Difference: " + difference);
        // [null, 20]

        hs.clear();
        System.out.println("After clear(): " + hs); // []
    }
}
