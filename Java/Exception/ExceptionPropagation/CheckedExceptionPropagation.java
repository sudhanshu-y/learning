import java.io.IOException;

public class CheckedExceptionPropagation {

    static void method3() throws IOException {
        throw new IOException("File error");
    }

    static void method2() throws IOException {
        method3(); // exception propagates
    }

    static void method1() throws IOException {
        method2(); // exception propagates
    }

    public static void main(String[] args) {
        try {
            method1(); // handled here
        } catch (IOException e) {
            System.out.println("Exception handled in main: " + e.getMessage());
        }
    }
}
