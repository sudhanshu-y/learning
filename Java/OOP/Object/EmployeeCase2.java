/**
 * Case#2: equals() overridden, hashCode() NOT overridden
 * Contract violation
 */

import java.util.HashMap;
import java.util.Map;

public class EmployeeCase2 {

    int id;

    EmployeeCase2(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeCase2)) return false;
        EmployeeCase2 e = (EmployeeCase2) o;
        return id == e.id;
    }

    public static void main(String[] args) {
        EmployeeCase2 e1 = new EmployeeCase2(1);
        EmployeeCase2 e2 = new EmployeeCase2(1);

        // If two objects are equal according to equals(), then they must have the same hashCode().
        // hashCodes are different here for two objects are equal according to equals().
        System.out.println("e1.equals(e2): " + e1.equals(e2)); // true
        System.out.println("e1.hashCode(): " + e1.hashCode()); // 622488023
        System.out.println("e2.hashCode(): " + e2.hashCode()); // 1933863327

        Map<EmployeeCase2, String> map = new HashMap<>();
        map.put(e1, "Bob");

        System.out.println("map.get(e2): " + map.get(e2)); // null
    }
}
