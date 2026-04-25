class ElectricCar implements Vehicle {

    public void start() {
        System.out.println("Electric car starts silently");
    }

    // Optional but can Override
    @Override
    public void fuel() {
        System.out.println("Electric car uses electricity");
    }
}
