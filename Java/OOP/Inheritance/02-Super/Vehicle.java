class Vehicle {
    String type = "Vehicle";
    int speed;

    Vehicle() {
        System.out.println("Vehicle default constructor");
    }

    Vehicle(int speed) {
        this.speed = speed;
        System.out.println("Vehicle parameterized constructor");
    }

    void move() {
        System.out.println("Vehicle moves at speed " + speed);
    }
}