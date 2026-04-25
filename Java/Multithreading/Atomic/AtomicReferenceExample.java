import java.util.concurrent.atomic.AtomicReference;

class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    // Getters only (no setters)
    public String getName() {
        return name; 
    }

    @Override
    public String toString() {
        return "User{name="+name+"}";
    }
}

public class AtomicReferenceExample {

    public static void main(String[] args) {

        // Create AtomicReference holding User object
        AtomicReference<User> userRef = new AtomicReference<>(new User("Alice"));
        System.out.println("Initial User: " + userRef.get()); // User{name=Alice}

        // Directly replace reference
        userRef.set(new User("Bob"));
        System.out.println("After set(): " + userRef.get()); // User{name=Bob}

        // compareAndSet(): Update only if current value matches expected
        boolean updated = userRef.compareAndSet(
                new User("Bob"),    // expected
                new User("Charlie") // new value
        );

        System.out.println("compareAndSet success: " + updated); // false
        System.out.println("Current User: " + userRef.get()); // User{name=Bob}   

        // compareAndSet above will FAIL because new User("Bob") != actual reference (different object).
        // CAS checks reference equality, not object content.

        // Correct CAS Example
        User current = userRef.get();
        boolean correctUpdate = userRef.compareAndSet(current, new User("Charlie"));

        System.out.println("Correct CAS success: " + correctUpdate); // true
        System.out.println("User after CAS: " + userRef.get()); // User{name=Charlie}

        // updateAndGet(): Applies function and returns UPDATED value
        User updatedUser = userRef.updateAndGet(user -> new User(user.getName() + " Smith"));
        System.out.println("After updateAndGet(): " + updatedUser); // User{name=Charlie Smith}

        // getAndUpdate(): Returns OLD value then updates
        User oldUser = userRef.getAndUpdate(user -> new User(user.getName() + " Jr"));
        System.out.println("Value returned by getAndUpdate(): " + oldUser); // User{name=Charlie Smith}
        System.out.println("Current User after getAndUpdate(): " + userRef.get()); // User{name=Charlie Smith Jr}
    }
}