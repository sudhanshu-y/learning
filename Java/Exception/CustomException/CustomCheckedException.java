// Step 1: Create custom exception
class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}

// Step 2: Use custom exception
public class CustomCheckedException {

    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Access granted");
    }

    public static void main(String[] args) {

        try {
            checkAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
