// Step 1: Create custom unchecked exception
class InsufficientBalanceException extends RuntimeException {

    InsufficientBalanceException(String message) {
        super(message);
    }
}

// Step 2: Use custom exception
public class CustomUncheckedException {

    static void withdraw(int balance, int amount) {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        System.out.println("Withdrawal successful");
    }

    public static void main(String[] args) {
        withdraw(5000, 7000);
    }
}
