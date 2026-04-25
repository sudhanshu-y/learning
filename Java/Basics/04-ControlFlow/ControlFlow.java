public class ControlFlow {

    public static void main(String[] args) {

        // ==============================
        // 1. DECISION MAKING
        // ==============================

        int number = 5;

        if (number > 0) {
            System.out.println("Number is positive");
        }

        if (number % 2 == 0) {
            System.out.println("Even number");
        } else {
            System.out.println("Odd number");
        }

        // if-else-if ladder
        int marks = 72;
        if (marks >= 90) {
            System.out.println("Grade: A");
        } else if (marks >= 75) {
            System.out.println("Grade: B");
        } else if (marks >= 60) {
            System.out.println("Grade: C");
        } else if (marks >= 40) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: Fail");
        }

        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid day");
        }

        // ==============================
        // 2. LOOPS
        // ==============================

        // For loop
        System.out.println("\nFor Loop:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("i = " + i);
        }

        // While loop
        System.out.println("\nWhile Loop:");
        int count = 1;
        while (count <= 3) {
            System.out.println("count = " + count);
            count++;
        }

        // Do-While loop
        System.out.println("\nDo-While Loop:");
        int x = 1;
        do {
            System.out.println("x = " + x);
            x++;
        } while (x <= 2);

        // Enhanced For Loop (Array)
        System.out.println("\nEnhanced For Loop:");
        int[] numbers = {10, 20, 30, 40};
        for (int n : numbers) {
            System.out.println(n);
        }

        // ==============================
        // 3. JUMP STATEMENTS
        // ==============================

        System.out.println("\nBreak and Continue:");
        for (int i = 1; i <= 5; i++) {

            if (i == 2) {
                continue; // skip 2
            }

            if (i == 4) {
                break; // exit loop
            }

            System.out.println("i = " + i);
        }

        return; // ends main method
    }
}
