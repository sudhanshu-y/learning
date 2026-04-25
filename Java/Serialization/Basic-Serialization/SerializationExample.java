import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {

    public static void main(String[] args) {
    
        // Create an object
        Student originalStudent = new Student("Alice", 20, "mySecurePassword");
        String filename = "student.ser";

        // --- Serialization: Writing the object to a file ---
        try (
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        ) {
           // Write the object to the file
           objectOut.writeObject(originalStudent);
           System.out.println("Object serialized and saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the original object to see its state
        System.out.println("Original Object: " + originalStudent);

        // --- Deserialization: Reading the object from a file ---
        Student deserializedStudent = null;

        try (
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            // Read the object from the file
            deserializedStudent = (Student) objectIn.readObject();
            System.out.println("Object deserialized from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print the deserialized object to show the result
        System.out.println("Deserialized Object: " + deserializedStudent);
   }
}

