abstract class Vehicle {

    int speed;

    // Constructor (abstract class can have constructor)
    Vehicle(int speed) {
        this.speed = speed;
        System.out.println("Vehicle constructor called");
    }

    // Abstract method (no body)
    abstract void start();

    // Non-abstract (concrete) method
    void fuel() {
        System.out.println("Vehicle needs fuel");
    }

    // Concrete method using variable
    void showSpeed() {
        System.out.println("Speed: " + speed);
    }
}
