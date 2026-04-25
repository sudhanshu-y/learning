import java.util.Deque;
import java.util.LinkedList;

public class LinkedListAsDeque {
    public static void main(String[] args) {
        Deque<Integer> dq = new LinkedList<>();

        // Add elements at both ends
        dq.addFirst(10);
        dq.addLast(20);
        dq.addLast(30);
        dq.addFirst(5);
        System.out.println("Deque: " + dq);
        // Deque: [5, 10, 20, 30]

        // Remove from both ends
        dq.removeFirst();
        dq.removeLast();
        System.out.println("After removals: " + dq);
        // After removals: [10, 20]

        // Peek at both ends
        System.out.println("Peek First: " + dq.peekFirst());    // 10
        System.out.println("Peek Last: " + dq.peekLast());      // 20

        // Traversal
        System.out.print("Deque Traversal: ");
        dq.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // Deque Traversal: 10 20
    }
}