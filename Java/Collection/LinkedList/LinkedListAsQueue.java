import java.util.LinkedList;
import java.util.Queue;

public class LinkedListAsQueue {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();

        // Enqueue
        q.add("Apple");
        q.add("Banana");
        q.add("Cherry");
        System.out.println("Queue: " + q);
        // Queue: [Apple, Banana, Cherry]

        // Peek (front element)
        System.out.println("Peek: " + q.peek());
        // Peek: Apple

        // Dequeue
        System.out.println("Poll: " + q.poll()); // Poll: Apple
        System.out.println("After poll: " + q);
        // After poll: [Banana, Cherry]

        // Traversal
        System.out.print("Queue Traversal: ");
        q.forEach(e -> System.out.print(e + " "));
        System.out.println();
        // Queue Traversal: Banana Cherry
    }
}
