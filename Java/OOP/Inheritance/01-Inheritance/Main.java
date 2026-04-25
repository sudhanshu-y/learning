public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.move();      // inherited method
        car.honk();      // own method
        System.out.println("Wheels: " + car.wheels);
    }
}