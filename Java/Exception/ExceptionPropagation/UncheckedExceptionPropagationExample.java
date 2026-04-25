public class UncheckedExceptionPropagationExample {
    static void method3() {
        int x = 10 / 0; // ArithmeticException
    }

    static void method2() {
        method3(); // propagates
    }

    static void method1() {
        method2(); // propagates
    }

    public static void main(String[] args) {
        method1(); // not handled
    }
}
