public class Main {
    public static void main(String[] args) {

        Car c1 = new Car();
        c1.displayType();
        c1.move();

        System.out.println();

        Car c2 = new Car(120);
        c2.displayType();
        c2.move();
    }
}