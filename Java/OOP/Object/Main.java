import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // 1. Object class reference
        Object obj1 = new Person("Alice", 25);
        Object obj2 = new Person("Alice", 25);

        // calls overridden toString()
        System.out.println("obj1.toString(): " + obj1.toString()); 
        System.out.println("obj2.toString(): " + obj2.toString());

        // 2. == vs equals()
        System.out.println("\n== comparison:");
        System.out.println("obj1 == obj2: " + (obj1 == obj2)); 
        // false, different references

        System.out.println("\nequals() comparison:");
        System.out.println("obj1.equals(obj2): " + obj1.equals(obj2)); 
        // true, content equal

        // 3. hashCode
        System.out.println("\nHash codes:");
        System.out.println("obj1.hashCode(): " + obj1.hashCode()); // 63350393
        System.out.println("obj2.hashCode(): " + obj2.hashCode()); // 63350393

        // 4. getClass()
        System.out.println("\nClass info:");
        System.out.println("obj1 class: " + obj1.getClass().getName()); // Person
        System.out.println("obj2 class: " + obj2.getClass().getName()); // Person

        // 5. Using Object reference for polymorphism
        Object obj3 = "Hello World"; // String is also an Object
        System.out.println("\nPolymorphism example:");
        System.out.println("obj3: " + obj3.toString());     // Hello World
        System.out.println("obj3 class: " + obj3.getClass().getName());     
        // java.lang.String

        // 6. Adding to a HashSet
        System.out.println("\nHashSet example:");
        HashSet<Person> set = new HashSet<>();

        set.add((Person) obj1);
        set.add((Person) obj2); 
        // won't add duplicate because equals() and hashCode() match

        System.out.println("HashSet size: " + set.size()); // 1
        System.out.println("HashSet content: " + set);
        // HashSet content: [Person{name='Alice', age=25}]
    }
}
