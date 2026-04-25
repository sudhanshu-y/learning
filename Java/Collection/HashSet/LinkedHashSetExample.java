import java.util.LinkedHashSet;
import java.util.Iterator;

public class LinkedHashSetExample {

    public static void main(String[] args) {
        // Creating LinkedHashSet (stores unique elements, maintains insertion order)
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();

        // Adding elements
        lhs.add(10);
        lhs.add(20);
        lhs.add(30);
        lhs.add(20);   // duplicate, ignored
        lhs.add(null); // allows one null
        System.out.println("Initial Set: " + lhs); 
        // maintains order: [10, 20, 30, null]

        // Search / Check
        System.out.println("Contains 20? " + lhs.contains(20)); // true
        System.out.println("Size: " + lhs.size());              // 4
        System.out.println("Is Empty? " + lhs.isEmpty());       // false

        // Removing elements
        lhs.remove(30); // remove by value
        System.out.println("After remove(30): " + lhs);

        // Traversals
        System.out.print("For-Each Loop: ");
        for (Integer x : lhs) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.print("Iterator: ");
        Iterator<Integer> itr = lhs.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();

        System.out.print("forEach (Lambda): ");
        lhs.forEach(x -> System.out.print(x + " "));
        System.out.println();

        // Set Operations
        LinkedHashSet<Integer> lhs2 = new LinkedHashSet<>();
        lhs2.add(10);
        lhs2.add(40);
        lhs2.add(50);

        // Union (all unique elements from both sets)
        LinkedHashSet<Integer> union = new LinkedHashSet<>(lhs);
        union.addAll(lhs2);
        System.out.println("Union: " + union);
        // [10, 20, null, 40, 50]

        // Intersection (common elements between sets)
        LinkedHashSet<Integer> intersection = new LinkedHashSet<>(lhs);
        intersection.retainAll(lhs2);
        System.out.println("Intersection: " + intersection);
        // [10]

        // Difference (elements in lhs but not in lhs2)
        LinkedHashSet<Integer> difference = new LinkedHashSet<>(lhs);
        difference.removeAll(lhs2);
        System.out.println("Difference: " + difference);
        // [20, null]

        // Clear all elements
        lhs.clear();
        System.out.println("After clear(): " + lhs); // []
    }
}
