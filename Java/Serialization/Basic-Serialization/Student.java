import java.io.Serializable;

public class Student implements Serializable {

    // Unique ID for versioning. Highly recommended for production code.
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    // The 'transient' keyword prevents this field from being serialized.
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
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", password='" + password + '\'' +'}';
   }
}
