class Car implements Engine, MusicSystem {

    @Override
    public void startEngine() {
        System.out.println("Car engine started");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music in the car");
    }
}
