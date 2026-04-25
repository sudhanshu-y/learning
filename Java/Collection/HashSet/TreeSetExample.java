import java.util.TreeSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TreeSetExample {
    public static void main(String[] args) {
        // Creating TreeSet (Natural Ordering - Ascending)
        TreeSet<Integer> ts = new TreeSet<>();

        // Adding elements
        ts.add(50);
        ts.add(10);
        ts.add(30);
        ts.add(20);
        ts.add(40);

        System.out.println("TreeSet (Sorted): " + ts);
        // [10, 20, 30, 40, 50]

        // Search
        System.out.println("Contains 30? " + ts.contains(30)); // true

        // First and Last
        System.out.println("First: " + ts.first());  // 10
        System.out.println("Last: " + ts.last());    // 50

        // Higher, Lower, Ceiling, Floor
        System.out.println("Higher(30): " + ts.higher(30));   // 40
        System.out.println("Lower(30): " + ts.lower(30));     // 20
        System.out.println("Ceiling(25): " + ts.ceiling(25)); // 30
        System.out.println("Floor(25): " + ts.floor(25));     // 20

        // Subsets
        System.out.println("HeadSet(30): " + ts.headSet(30));   
        // [10, 20]
        System.out.println("TailSet(30): " + ts.tailSet(30));   
        // [30, 40, 50]
        System.out.println("SubSet(15, 45): " + ts.subSet(15, 45)); 
        // [20, 30, 40]

        // Remove
        ts.remove(20);
        System.out.println("After remove(20): " + ts);
        // [10, 30, 40, 50]

        // Traversals
        System.out.print("For-Each Loop: ");
        for (int x : ts) {
            System.out.print(x + " ");
        }
        System.out.println();
        // For-Each Loop: 10 30 40 50

        System.out.print("Iterator: ");
        Iterator<Integer> itr = ts.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        // Iterator: 10 30 40 50

        System.out.print("forEach (Lambda): ");
        ts.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // forEach (Lambda): 10 30 40 50

        // Utility
        System.out.println("Size: " + ts.size()); // 4
        System.out.println("IsEmpty? " + ts.isEmpty()); // false

        ts.clear();
        System.out.println("After clear(): " + ts); // []

        // Descending Order
        TreeSet<Integer> tsDesc = new TreeSet<>(Collections.reverseOrder());
        tsDesc.addAll(Arrays.asList(90, 50, 40, 70, 80));
        System.out.println(tsDesc); 
        // [90, 80, 70, 50, 40]

        TreeSet<Integer> tsDesc2 = new TreeSet<>((x, y)->(y-x));
        tsDesc2.addAll(Arrays.asList(90, 50, 40, 70, 80));
        System.out.println(tsDesc2); 
        // [90, 80, 70, 50, 40]
    }
}
