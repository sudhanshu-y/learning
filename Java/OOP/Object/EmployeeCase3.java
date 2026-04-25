/**
 * Case#3: hashCode() overridden, equals() NOT overridden.
 * Logical inconsistency.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmployeeCase3 {

    int id;

    EmployeeCase3(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {
        EmployeeCase3 e1 = new EmployeeCase3(1);
        EmployeeCase3 e2 = new EmployeeCase3(1);

        // Same hash code
        // equals() → false
        System.out.println("e1.equals(e2): " + e1.equals(e2)); // false
        System.out.println("e1.hashCode(): " + e1.hashCode()); // 32
        System.out.println("e2.hashCode(): " + e2.hashCode()); // 32

        Map<EmployeeCase3, String> map = new HashMap<>();
        map.put(e1, "Charlie");

        System.out.println("map.get(e2): " + map.get(e2)); // null
    }
}
