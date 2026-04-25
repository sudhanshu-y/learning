import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeAsDeque {
    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();

        // Add at both ends
        dq.offerFirst(10);
        dq.offerLast(20);
        dq.offerLast(30);
        dq.offerFirst(5);
        System.out.println("Deque: " + dq);
        // [5, 10, 20, 30]

        // Peek without removing
        System.out.println("Peek First: " + dq.peekFirst()); // 5
        System.out.println("Peek Last: " + dq.peekLast());   // 30

        // Access ends (throws exception if empty)
        System.out.println("getFirst(): " + dq.getFirst()); // 5
        System.out.println("getLast(): " + dq.getLast());   // 30

        // Remove from both ends
        Integer removedFirst = dq.pollFirst();
        Integer removedLast = dq.pollLast();
        System.out.println("Polled First: " + removedFirst);    // 5
        System.out.println("Polled Last: " + removedLast);      // 30
        System.out.println("After pollFirst & pollLast: " + dq);
        // After pollFirst & pollLast: [10, 20]

        System.out.print("Forward: ");
        dq.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // Forward: 10 20
    }
}
