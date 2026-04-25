interface Engine {
    void startEngine();

    default void info() {
        System.out.println("Engine info");
    }
}