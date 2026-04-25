public class Main {
    public static void main(String[] args) {

        // Interfaces cannot be instantiated
        // Vehicle v = new Vehicle();

        // Runtime polymorphism
        Vehicle v = new Car();

        v.start();   // Car implementation
        v.fuel();    // Car implementation

        // System.out.println(v.MAX_SPEED); // allowed
        System.out.println("Max speed: " + Vehicle.MAX_SPEED);

        // v.honk(); // Not accessible (reference type)
    }
}
