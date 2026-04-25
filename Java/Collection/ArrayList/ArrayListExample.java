import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListExample {
    public static void main(String[] args) {
        // Creating ArrayList
        ArrayList<Integer> al = new ArrayList<>();

        // Adding Elements
        al.add(66);       // add at end
        al.add(77);
        al.add(99);
        al.add(33);
        al.add(44);
        al.add(2, 0);     // insert at index
        System.out.println("Initial List: " + al); 
        // [66, 77, 0, 99, 33, 44]

        // Access & Modify
        System.out.println("Element at index 3: " + al.get(3)); // 99
        al.set(3, 22);  // replace element
        System.out.println("After set(): " + al); 
        // [66, 77, 0, 22, 33, 44]

        // Searching
        System.out.println("Contains 22 ? " + al.contains(22)); // true
        System.out.println("Index of 22: " + al.indexOf(22));  // 3

        // Sorting with Collections
        Collections.sort(al);
        System.out.println("Sorted ASC (Collections): " + al); 
        // [0, 22, 33, 44, 66, 77]

        Collections.sort(al, Collections.reverseOrder());
        System.out.println("Sorted DESC (Collections): " + al); 
        // [77, 66, 44, 33, 22, 0]

        // Sorting with Lambda (Java 8+)
        al.sort((x, y) -> x - y);
        System.out.println("Sorted ASC (Lambda): " + al); 
        // [0, 22, 33, 44, 66, 77]

        al.sort((x, y) -> y - x);
        System.out.println("Sorted DESC (Lambda): " + al); 
        // [77, 66, 44, 33, 22, 0]

        // Removing
        al.remove(2); // remove by index
        System.out.println("After remove(2): " + al);
        // [77, 66, 33, 22, 0]

        al.remove(Integer.valueOf(22)); // remove by object
        System.out.println("After remove(Integer.valueOf(22)): " + al);
        // [77, 66, 33, 0]
        // For string/other types - directly pass value

        // Traversals
        System.out.print("For Loop: ");
        for (int i = 0; i < al.size(); i++) {
            System.out.print(al.get(i) + " ");
        }
        System.out.println();
        // For Loop: 77 66 33 0

        System.out.print("For-Each Loop: ");
        for (int x : al) {
            System.out.print(x + " ");
        }
        System.out.println();
        // For-Each Loop: 77 66 33 0

        System.out.print("Iterator: ");
        Iterator<Integer> itr = al.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        // Iterator: 77 66 33 0

        System.out.print("forEach (Lambda): ");
        al.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // forEach (Lambda): 77 66 33 0

        // Utility Methods
        System.out.println("Size: " + al.size());       // Size: 4
        System.out.println("IsEmpty? " + al.isEmpty()); // IsEmpty? false

        al.clear();
        System.out.println("After clear(): " + al); // []
    }
}
