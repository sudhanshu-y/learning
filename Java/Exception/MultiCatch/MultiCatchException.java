import java.io.FileReader;
import java.io.IOException;

public class MultiCatchException {
    public static void main(String[] args) {
        try {
            int a = 10 / 0;               // ArithmeticException
            FileReader fr = new FileReader("data.txt");
        } catch (ArithmeticException | IOException e) {
            System.out.println("Arithmetic or File error occurred");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
