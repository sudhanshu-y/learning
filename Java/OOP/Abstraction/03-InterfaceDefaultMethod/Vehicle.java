interface Vehicle {

    void start();

    // Java 8 default method
    default void fuel() {
        System.out.println("Vehicle uses fuel");
    }
}
