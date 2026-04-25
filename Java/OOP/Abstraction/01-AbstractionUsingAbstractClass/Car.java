class Car extends Vehicle {

    // Must call abstract class constructor
    Car(int speed) {
        super(speed);
        System.out.println("Car constructor called");
    }

    // Implement abstract method
    @Override
    void start() {
        System.out.println("Car starts with key or button");
    }
}
