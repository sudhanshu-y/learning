import java.util.Optional;

public class Optional2MethodsExample {
    public static void main(String[] args) {

        Optional<String> opt1 = Optional.of("Learning!!"); 
        Optional<String> opt2 = Optional.empty();

        System.out.println("===== Using get() =====");
        // ===== Using get() =====
        if (opt1.isPresent()) {
            System.out.println("opt1.get(): " + opt1.get()); 
            // opt1.get(): Learning!!
        }

        try {
            System.out.println("opt2.get(): " + opt2.get()); 
        } catch (Exception e) {
            System.out.println("opt2.get() Exception: " + e.getMessage());
            // opt2.get() Exception: No value present
        }

        System.out.println("\n===== Using isPresent() and isEmpty() =====");
        // ===== Using isPresent() and isEmpty() =====
        System.out.println("opt1.isPresent(): " + opt1.isPresent()); 
        // opt1.isPresent(): true
        System.out.println("opt2.isPresent(): " + opt2.isPresent()); 
        // opt2.isPresent(): false
        System.out.println("opt1.isEmpty(): " + opt1.isEmpty());     
        // opt1.isEmpty(): false
        System.out.println("opt2.isEmpty(): " + opt2.isEmpty());     
        // opt2.isEmpty(): true

        System.out.println("\n===== Using ifPresent() =====");
        // ===== Using ifPresent() =====
        opt1.ifPresent(value -> System.out.println("opt1 via ifPresent: " + value));
        // opt1 via ifPresent: Learning!!
        opt2.ifPresent(value -> System.out.println("opt2 via ifPresent: " + value)); 
        // (no output)

        System.out.println("\n===== Using ifPresentOrElse() =====");
        // ===== Using ifPresentOrElse() =====
        opt1.ifPresentOrElse(
                value -> System.out.println("opt1 via ifPresentOrElse: " + value),
                () -> System.out.println("opt1 is empty")
        );
        // opt1 via ifPresentOrElse: Learning!!

        opt2.ifPresentOrElse(
                value -> System.out.println("opt2 via ifPresentOrElse: " + value),
                () -> System.out.println("opt2 is empty")
        );
        // opt2 is empty

        System.out.println("\n===== Using orElse() =====");
        // ===== Using orElse() =====
        String val1 = opt1.orElse("Default");
        String val2 = opt2.orElse("Default");
        System.out.println("orElse opt1: " + val1);
        // orElse opt1: Learning!!
        System.out.println("orElse opt2: " + val2);
        // orElse opt2: Default

        System.out.println("\n===== Using orElseGet() =====");
        // ===== Using orElseGet() =====
        String val3 = opt1.orElseGet(() -> "Lazy Default");
        String val4 = opt2.orElseGet(() -> "Lazy Default");
        System.out.println("orElseGet opt1: " + val3);
        // orElseGet opt1: Learning!!
        System.out.println("orElseGet opt2: " + val4);
        // orElseGet opt2: Lazy Default

        System.out.println("\n===== Using orElseThrow() =====");
        // ===== Using orElseThrow() =====
        try {
            String val5 = opt1.orElseThrow(); 
            System.out.println("orElseThrow opt1: " + val5);
            // orElseThrow opt1: Learning!!
        } catch (Exception e) {
            System.out.println("orElseThrow opt1 Exception: " + e.getMessage());
        }

        try {
            String val6 = opt2.orElseThrow(); 
            System.out.println("orElseThrow opt2: " + val6);
        } catch (Exception e) {
            System.out.println("orElseThrow opt2 Exception: " + e.getMessage());
            // orElseThrow opt2 Exception: No value present
        }

        System.out.println("\n===== Using orElseThrow(Supplier) =====");
        // ===== Using orElseThrow(Supplier) =====
        try {
            String val7 = opt1.orElseThrow(() -> new RuntimeException("Missing Value"));
            System.out.println("orElseThrow(Supplier) opt1: " + val7);
            // orElseThrow(Supplier) opt1: Learning!!
        } catch (Exception e) {
            System.out.println("opt1 Exception: " + e.getMessage());
        }

        try {
            String val8 = opt2.orElseThrow(() -> new RuntimeException("Missing Value"));
            System.out.println("orElseThrow(Supplier) opt2: " + val8);
        } catch (Exception e) {
            System.out.println("orElseThrow(Supplier) opt2 Exception: " + e.getMessage());
            // orElseThrow(Supplier) opt2 Exception: Missing Value
        }
    }
}
