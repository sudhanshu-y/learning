import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    // We keep it transient so default serialization ignores it
    private transient String password; 

    public Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
       return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student{" +
               "name='" + name + '\'' + ", age=" + age + ", password='" + password + '\'' +
               '}';
    }

    // ==========================================
    // CUSTOM SERIALIZATION METHODS
    // ==========================================

    // This method is called automatically during serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // 1. Perform the default serialization for non-transient fields (name, age)
        oos.defaultWriteObject();

        // 2. Add custom logic: Encrypt the password
        System.out.println("-> [Custom Serialization] Encrypting password...");
        String encryptedPassword = Base64.getEncoder().encodeToString(this.password.getBytes());
        
        // 3. Write the encrypted password manually to the stream
        oos.writeObject(encryptedPassword);
    }

    // This method is called automatically during deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // 1. Perform the default deserialization for non-transient fields
        ois.defaultReadObject();

        // 2. Read the custom data we added (the encrypted password)
        String encryptedPassword = (String) ois.readObject();
        System.out.println("-> [Custom Deserialization] Decrypting password...");

        // 3. Add custom logic: Decrypt the password and assign it
        this.password = new String(Base64.getDecoder().decode(encryptedPassword));
    }
}