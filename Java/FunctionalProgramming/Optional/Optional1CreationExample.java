import java.util.Optional;

public class Optional1CreationExample {
    public static void main(String[] args) {
        
        // 1. Optional.of() → use when value is guaranteed not null
        System.out.println("===== Optional.of() =====");
        String name = "Alice";
        Optional<String> opt1 = Optional.of(name);
        System.out.println("Optional.of(): " + opt1); 
        System.out.println("Optional.of(): " + opt1.get()); 
        // Prints: Alice
        
        // If value were null here → throws NullPointerException
        // Optional<String> optInvalid = Optional.of(null); // ❌ will crash

        // 2. Optional.ofNullable() → use when value may be null
        System.out.println("\n===== Optional.ofNullable() =====");
        String nullableName = null;
        Optional<String> opt2 = Optional.ofNullable(nullableName);
        System.out.println("Optional.ofNullable(): " + opt2);
        System.out.println("Optional.ofNullable(): " + opt2.orElse("Default Name")); 
        // Prints: Default Name

        // If value was non-null → behaves same as Optional.of()
        Optional<String> opt2b = Optional.ofNullable("Bob");
        System.out.println("Optional.ofNullable (non-null): " + opt2b); 
        System.out.println("Optional.ofNullable (non-null): " + opt2b.get()); 
        // Prints: Bob

        // 3. Optional.empty() → explicitly represents "no value"
        System.out.println("\n===== Optional.empty() =====");
        Optional<String> opt3 = Optional.empty();
        System.out.println("Optional.empty(): " + opt3);
        System.out.println("Optional.empty(): " + opt3.orElse("No Value Present")); 
        // Prints: No Value Present
    }
}