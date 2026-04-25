public class Main {

    public static void main(String[] args) {

        // Upcasting
        Vehicle v = new Car();

        System.out.println("\n--- Method Calls ---");
        v.start();   // Car's method (object type)
        v.fuel();    // Car + Vehicle method

        System.out.println("\n--- Variable Access ---");
        System.out.println(v.type); // Vehicle (reference type)

        System.out.println("\n--- Reference Type Restriction ---");
        // v.honk(); // Compile-time error

        System.out.println("\n--- Downcasting ---");
        Car c = (Car) v; // safe downcasting
        c.honk();        // now allowed
    }
}