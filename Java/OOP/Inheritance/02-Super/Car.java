class Car extends Vehicle {
    String type = "Car";

    Car() {
        super(); // calls Vehicle default constructor
        System.out.println("Car default constructor");
    }

    Car(int speed) {
        super(speed); // calls Vehicle parameterized constructor
        System.out.println("Car parameterized constructor");
    }

    void displayType() {
        System.out.println("Child type: " + type);
        System.out.println("Parent type: " + super.type);
    }

    @Override
    void move() {
        super.move(); // call parent method
        System.out.println("Car moves smoothly");
    }
}