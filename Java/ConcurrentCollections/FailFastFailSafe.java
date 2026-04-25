import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastFailSafe {
    public static void main(String[] args) {

        // WRONG – Fail-Fast (Throws Exception)
        try {
            List<String> names = new ArrayList<>(Arrays.asList("A", "B", "C"));

            for (String name : names) {
                if (name.equals("A")) {
                    names.remove(name); // ❌ ConcurrentModificationException
                }
            }
        } catch (Exception e) {
            System.out.println("Fail-Fast Exception Caught: " + e);
        }

        // RIGHT – Fail-Fast but Safe (Using Iterator)
        List<String> names2 = new ArrayList<>(Arrays.asList("A", "B", "C"));

        Iterator<String> iterator = names2.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("A")) {
                iterator.remove(); // ✅ Safe removal
            }
        }

        System.out.println("After safe removal (Fail-Fast safe): " + names2);

        // Fail-Safe Example (CopyOnWriteArrayList)
        CopyOnWriteArrayList<String> names3 =
                new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));

        for (String name : names3) {
            if (name.equals("A")) {
                names3.remove(name); // ✅ No Exception
            }
        }

        System.out.println("After modification (Fail-Safe): " + names3);
    }
}




