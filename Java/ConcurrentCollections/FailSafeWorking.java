import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class FailSafeWorking {

    public static void main(String[] args) {

        // COPY-ON-WRITE EXAMPLE (Fail-Safe Iterator)
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("CopyOnWriteArrayList : "+ list);

        for (Integer num : list) {

            System.out.println("Reading: " + num);

            // Condition based modification during iteration
            if (num == 2) {
                list.add(4);
                System.out.println("Added 4 during iteration");
            }
        }

        System.out.println("Final List: " + list);
        // [1, 2, 3, 4]

        
        // WEAKLY CONSISTENT ITERATOR EXAMPLE
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        System.out.println("\nConcurrentHashMap : "+ map);

        for (Integer key : map.keySet()) {

            System.out.println("Reading: " + key + " -> " + map.get(key));

            // Condition based modification during iteration
            if (key == 2) {
                map.put(4, "D");
                System.out.println("Added (4,D) during iteration");
            }
        }

        System.out.println("Final Map: " + map);
        // New elements MAY appear in iteration.
    }
}