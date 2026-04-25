/**
 * Case#1: Neither equals() nor hashCode() overridden
 * Default Object behavior
 */

import java.util.HashMap;
import java.util.Map;

public class EmployeeCase1 {

    int id;

    EmployeeCase1(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        EmployeeCase1 e1 = new EmployeeCase1(1);
        EmployeeCase1 e2 = new EmployeeCase1(1);

        System.out.println("e1.equals(e2): " + e1.equals(e2)); // false
        System.out.println("e1.hashCode(): " + e1.hashCode()); // 622488023
        System.out.println("e2.hashCode(): " + e2.hashCode()); // 1933863327

        Map<EmployeeCase1, String> map = new HashMap<>();
        map.put(e1, "Alice");

        // If two objects are equal according to equals(), then they must have the same hashCode().
        // Here objects are not same according to default behaviour of e1.equals(e2) method
        System.out.println("map.get(e2): " + map.get(e2)); // null
    }
}
