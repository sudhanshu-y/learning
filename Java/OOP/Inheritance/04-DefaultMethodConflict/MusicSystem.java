interface MusicSystem {
    void playMusic();

    default void info() {
        System.out.println("Music system info");
    }
}