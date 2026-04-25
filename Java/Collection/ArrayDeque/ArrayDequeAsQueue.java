import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeAsQueue {
    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();

        // Add (rear side)
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        System.out.println("Queue: " + queue); 
        // [10, 20, 30]

        // Access (front and rear)
        System.out.println("Peek (front): " + queue.peek());       // 10
        System.out.println("Peek First: " + queue.peekFirst());    // 10
        System.out.println("Peek Last: " + queue.peekLast());      // 30

        // Remove from front
        Integer removed = queue.poll();
        System.out.println("Polled element: " + removed);   // 10
        System.out.println("After poll(): " + queue);       // [20, 30]

        // Utility
        System.out.println("Size: " + queue.size());        // Size: 2
        System.out.println("IsEmpty? " + queue.isEmpty());  // IsEmpty? false

        queue.clear();
        System.out.println("After clear(): " + queue); // []
    }
}
