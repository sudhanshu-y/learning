import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeAsStack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack: " + stack); 
        // [30, 20, 10]  (top = leftmost)

        // Peek top
        System.out.println("Peek: " + stack.peek());   // 30

        // Pop elements
        Integer popped = stack.pop();
        System.out.println("Popped element: " + popped);    // 30
        System.out.println("After pop(): " + stack);        // [20, 10]

        // Traversal
        System.out.print("Traversal: ");
        stack.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // Traversal: 20 10

        // Utility
        System.out.println("Size: " + stack.size());        // 2
        System.out.println("IsEmpty? " + stack.isEmpty());  // false

        stack.clear();
        System.out.println("After clear(): " + stack); // []
    }
}
