public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(2, 3));        // 5
        System.out.println(c.add(2.5, 3.5));    // 6.0
        System.out.println(c.add(1, 2, 3));   // 6
    }
}
