public class ExceptionExample {

    // Method that uses 'throws'
    static void checkAge(int age) throws Exception {
        if (age < 18) {
            // 'throw' keyword
            throw new Exception("Age must be 18 or above");
        }
        System.out.println("Access granted");
    }

    public static void main(String[] args) {

        try {
            System.out.println("Program started");

            int result = 10 / 2;   // try block
            System.out.println("Result: " + result);

            checkAge(15);          // will throw exception

        } catch (ArithmeticException e) {
            // catch block
            System.out.println("Arithmetic Error: " + e.getMessage());
        } catch (Exception e) {
            // catches exception thrown by checkAge()
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            // finally block
            System.out.println("Finally block executed");
        }

        System.out.println("Program ended");
    }
}
