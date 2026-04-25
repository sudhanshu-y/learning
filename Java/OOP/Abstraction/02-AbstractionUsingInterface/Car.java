class Car implements Vehicle {

    @Override
    public void start() {
        System.out.println("Car starts with key or button");
    }

    @Override
    public void fuel() {
        System.out.println("Car uses petrol or diesel");
    }

    void honk() {
        System.out.println("Car is honking");
    }
}
