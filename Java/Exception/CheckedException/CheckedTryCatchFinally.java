import java.io.FileReader;
import java.io.IOException;

public class CheckedTryCatchFinally {
    public static void main(String[] args) {

        FileReader fr = null;

        try {
            // Code that may cause a checked exception
            fr = new FileReader("data.txt");
            System.out.println("File opened successfully");
        } catch (IOException e) {
            // Handling the checked exception
            System.out.println("File not found or error reading file");
        } finally {
            // Cleanup code
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("File closed");
                }
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
