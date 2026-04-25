public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override equals() to compare content, not reference
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;               // same reference
        
        if (obj == null || getClass() != obj.getClass()) return false; // different type
        
        Person person = (Person) obj;
        return this.age == person.age && this.name.equals(person.name);
    }

    // Override hashCode() to match equals()
    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

    // Override toString() for readable output
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}