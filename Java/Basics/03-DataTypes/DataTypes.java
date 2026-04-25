public class DataTypes {

    public static void main(String[] args) {

        // ===============================
        // 1. Primitive Data Types
        // ===============================

        byte myByte = 100;
        short myShort = 32000;
        int myInt = 100000;
        long myLong = 10000000000L;

        float myFloat = 5.75f;
        double myDouble = 19.99;

        char myChar = 'A';
        boolean myBoolean = true;

        System.out.println("Primitive Data Types:");
        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);
        System.out.println("char: " + myChar);
        System.out.println("boolean: " + myBoolean);

        System.out.println();

        // ===============================
        // 2. Non-Primitive Data Types
        // ===============================

        String myString = "Hello, Java";
        int[] myArray = {1, 2, 3, 4, 5};
        Person person = new Person("Alice", 25);

        System.out.println("Non-Primitive Data Types:");
        System.out.println("String: " + myString);

        System.out.print("Array: ");
        for (int num : myArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Object: " + person.getDetails());

        System.out.println();

        // ===============================
        // 3. Unicode Characters
        // ===============================

        // Basic character
        char letter = 'A';

        // Unicode representation of characters
        char unicodeA = '\u0041';     // A
        char rupee = '\u20B9';        // ₹
        char smiley = '\u263A';       // ☺
        char alpha = '\u03B1';        // α

        // Printing characters
        System.out.println("Unicode Examples:");
        System.out.println("Letter: " + letter);
        System.out.println("Unicode A: " + unicodeA);
        System.out.println("Rupee Symbol: " + rupee);
        System.out.println("Smiley: " + smiley);
        System.out.println("Greek Alpha: " + alpha);

        // Unicode escape inside code
        System.out.println("\u004A\u0061\u0076\u0061"); // Java
    }
}

// ===============================
// Custom Class
// ===============================
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getDetails() {
        return "Name: " + name + ", Age: " + age;
    }
}
