public class JavaMethodsStrictlyPassByValue {
    public static void main(String[] args) {

        // Java is strictly pass-by-value.

        // 1. Primitive type
        int a = 10;
        System.out.println("Before changePrimitive, a = " + a);
        changePrimitive(a);
        System.out.println("After changePrimitive, a = " + a);

        System.out.println("------------------------------------------------");

        // 2. Object reference
        Person person = new Person("Alice");
        System.out.println("Before changeObjectState, person.name = " + person.name);
        changeObjectState(person);
        System.out.println("After changeObjectState, person.name = " + person.name);

        System.out.println("------------------------------------------------");

        // 3. Reassigning the object reference
        System.out.println("Before reassignObject, person.name = " + person.name);
        reassignObject(person);
        System.out.println("After reassignObject, person.name = " + person.name);
    }

      // A simple class to use as an object example
    static class Person {
        String name;

        Person(String name) {
            this.name = name;
        }
    }

    // Primitive example
    static void changePrimitive(int x) {
        x = 100;
        System.out.println("Inside changePrimitive, x = " + x);
    }

    // Object example: modifying the object's state
    static void changeObjectState(Person p) {
        p.name = "Bob";
        System.out.println("Inside changeObjectState, p.name = " + p.name);
    }

    // Object example: reassigning the reference
    static void reassignObject(Person p) {
        p = new Person("Charlie");
        System.out.println("Inside reassignObject, p.name = " + p.name);
    }
}