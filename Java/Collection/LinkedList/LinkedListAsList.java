import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;

public class LinkedListAsList {
    public static void main(String[] args) {
        // Creating LinkedList
        LinkedList<Integer> ll = new LinkedList<>();

        // Adding Elements
        ll.add(66);       // add at end
        ll.add(77);
        ll.add(99);
        ll.add(33);
        ll.add(44);
        ll.add(2, 0);     // insert at index
        System.out.println("Initial List: " + ll); 
        // [66, 77, 0, 99, 33, 44]

        // Access & Modify
        System.out.println("Element at index 3: " + ll.get(3)); // 99
        ll.set(3, 22);  // replace element
        System.out.println("After set(): " + ll); 
        // [66, 77, 0, 22, 33, 44]

        // Searching
        System.out.println("Contains 22? " + ll.contains(22)); // true
        System.out.println("Index of 22: " + ll.indexOf(22));  // 3

        // Sorting with Collections
        Collections.sort(ll);
        System.out.println("Sorted ASC (Collections): " + ll); 
        // Sorted ASC (Collections): [0, 22, 33, 44, 66, 77]

        Collections.sort(ll, Collections.reverseOrder());
        System.out.println("Sorted DESC (Collections): " + ll); 
        // Sorted DESC (Collections): [77, 66, 44, 33, 22, 0]

        // Sorting with Lambda
        ll.sort((x, y) -> x - y);
        System.out.println("Sorted ASC (Lambda): " + ll);
        // Sorted ASC (Lambda): [0, 22, 33, 44, 66, 77]

        ll.sort((x, y) -> y - x);
        System.out.println("Sorted DESC (Lambda): " + ll);
        // Sorted DESC (Lambda): [77, 66, 44, 33, 22, 0]

        // Removing
        ll.remove(2); // remove by index
        System.out.println("After remove(2): " + ll);
        // After remove(2): [77, 66, 33, 22, 0]

        ll.remove(Integer.valueOf(44)); // remove by object
        System.out.println("After remove(Integer.valueOf(44)): " + ll);
        // After remove(Integer.valueOf(44)): [77, 66, 33, 22, 0]

        // Traversals
        System.out.print("For Loop: ");
        for (int i = 0; i < ll.size(); i++) {
            System.out.print(ll.get(i) + " ");
        }
        System.out.println();
        // For Loop: 77 66 33 22 0

        System.out.print("For-Each Loop: ");
        for (int x : ll) {
            System.out.print(x + " ");
        }
        System.out.println();
        // For-Each Loop: 77 66 33 22 0

        System.out.print("Iterator: ");
        Iterator<Integer> itr = ll.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        // Iterator: 77 66 33 22 0

        System.out.print("forEach (Lambda): ");
        ll.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // forEach (Lambda): 77 66 33 22 0 

        // Utility Methods
        System.out.println("Size: " + ll.size());       // 5
        System.out.println("IsEmpty? " + ll.isEmpty()); // false

        ll.clear();
        System.out.println("After clear(): " + ll); // []
    }
}
