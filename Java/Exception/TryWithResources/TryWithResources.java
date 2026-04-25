import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TryWithResources {
    public static void main(String[] args) {

        // WITHOUT try-with-resources (OLD WAY)
        FileReader fr = null;
        try {
            fr = new FileReader("data.txt");
            System.out.println("Old way: File opened");
        } catch (IOException e) {
            System.out.println("Old way: File error");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("Old way: File closed");
                }
            } catch (IOException e) {
                System.out.println("Old way: Error closing file");
            }
        }

        System.out.println("--------------------------------");

        // WITH try-with-resources (RECOMMENDED)
        try (FileReader fr2 = new FileReader("data.txt")) {
            System.out.println("New way: File opened");
        } catch (IOException e) {
            System.out.println("New way: File error");
        }

        System.out.println("--------------------------------");

        // MULTIPLE RESOURCES EXAMPLE
        try (
            FileReader fr3 = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr3)
        ) {
            System.out.println("Multiple resources: " + br.readLine());
        } catch (IOException e) {
            System.out.println("Multiple resources: File error");
        }
    }
}

