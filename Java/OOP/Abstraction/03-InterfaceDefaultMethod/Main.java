public class Main {
    public static void main(String[] args) {

        Vehicle v = new Car();
        v.start();  // Car starts
        v.fuel();   // Vehicle uses fuel

        Vehicle ev = new ElectricCar();
        ev.start(); // Electric car starts silently
        ev.fuel();  // Electric car uses electricity
    }
}
