import java.io.FileReader;
import java.io.IOException;

public class CheckedThrows {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("data.txt");
        System.out.println("File opened successfully");
        fr.close();
    }
}

