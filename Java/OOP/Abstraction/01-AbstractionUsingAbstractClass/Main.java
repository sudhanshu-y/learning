public class Main {
    public static void main(String[] args) {

        // Abstract classes cannot be instantiated
        // Vehicle v = new Vehicle(100);

        // Allowed (runtime polymorphism)
        Vehicle v = new Car(120);

        v.start();       // Car implementation
        v.fuel();        // Vehicle implementation
        v.showSpeed();   // Vehicle implementation
    }
}
