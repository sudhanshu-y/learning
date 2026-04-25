class Car extends Vehicle {
    String type = "Car";

    Car() {
        super(); // parent constructor
        System.out.println("Car constructor");
    }

    @Override
    void start() {
        System.out.println("Car starts with key or button");
    }

    @Override
    void fuel() {
        super.fuel(); // call parent method
        System.out.println("Car uses petrol or diesel");
    }

    void honk() {
        System.out.println("Car is honking");
    }
}