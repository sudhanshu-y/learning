public class SwitchExample {

    public static void main(String[] args) {

        // Fall-through
        int x1 = 2;
        switch (x1) {
            case 1: 
                System.out.println("A");
            case 2: 
                System.out.println("B");
            case 3: 
                System.out.println("C");
        }
        // Output: BC

        // default in middle
        int x2 = 5;
        switch (x2) {
            default: 
                System.out.println("D");
            case 1: 
                System.out.println("A");
            case 2: 
                System.out.println("B");
        }
        // Output: DAB

        // String switch (case-sensitive + fall-through)
        String s = "Java";
        switch (s) {
            case "Java": System.out.println("A");
            case "java": System.out.println("B");
        }
        // Output: AB

        // final constant allowed
        final int a = 10;
        switch (a) {
            case 10: System.out.println("Ten");
        }
        // Output: Ten

        // Enhanced switch (Java 14+)
        int x3 = 2;
        String r = switch (x3) {
            case 1 -> "One";
            case 2 -> "Two";
            default -> "Other";
        };
        System.out.println(r); 
        // Output: Two

    }
}
