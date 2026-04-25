import java.util.concurrent.atomic.AtomicStampedReference;

class User {
    private String name;
    
    public User(String name) { 
        this.name = name; 
    }

    public String getName() {
        return name; 
    }

    @Override
    public String toString() {
        return "User{name=" + name + "}"; 
    }
}

public class AtomicStampedReferenceExample {
    public static void main(String[] args) {
        User alice = new User("Alice");
        
        // Initializing with reference 'alice' and stamp '0'
        AtomicStampedReference<User> stampedRef = new AtomicStampedReference<>(alice, 0);

        // Getting both the reference and the stamp
        int[] stampHolder = new int[1]; // Array used to "return" the stamp value
        User current = stampedRef.get(stampHolder);
        int currentStamp = stampHolder[0];

        System.out.println("Initial User: " + current + " | Stamp: " + currentStamp);
        // Initial User: User{name=Alice} | Stamp: 0

        // Correct CAS Example
        // We provide the current object AND the current stamp
        boolean success = stampedRef.compareAndSet(
                current,                // Expected Ref
                new User("Bob"),   // New Ref
                currentStamp,           // Expected Stamp
                currentStamp + 1        // New Stamp
        );
        System.out.println("Update to Bob success: " + success); // true
        System.out.println("Current Stamp: " + stampedRef.getStamp()); // Current Stamp: 1

        // Simulating the ABA Problem Prevention
        User bob = stampedRef.getReference();
        int bobStamp = stampedRef.getStamp();

        // Thread A: Changes Bob -> Alice (Stamp goes 1 -> 2)
        stampedRef.compareAndSet(bob, alice, bobStamp, bobStamp + 1);
        
        // Thread B: Tries to change Alice -> Charlie using the OLD stamp (0)
        // This will FAIL even though the reference is "Alice" again!
        boolean abaCaught = stampedRef.compareAndSet(
                alice, 
                new User("Charlie"), 
                0,    // Old stamp from the very beginning
                1          // Target stamp
        );

        System.out.println("ABA Update (with old stamp) success: " + abaCaught); // false
        System.out.println("Final State: " + stampedRef.getReference()); // Final State: User{name=Alice}
        System.out.println("Final Stamp: " + stampedRef.getStamp()); // Final Stamp: 2
    }
}