import java.util.PriorityQueue;
import java.util.Iterator;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Min-Heap (default)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements
        pq.offer(50);
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);
        pq.offer(40);

        System.out.println("PriorityQueue (Min-Heap): " + pq);
        // Order may not look sorted, but head = smallest (10)
        // [10, 20, 30, 50, 40]

        // Peek head
        System.out.println("Peek: " + pq.peek()); // 10

        // Remove head
        System.out.println("Polled: " + pq.poll()); // 10
        System.out.println("After poll: " + pq);
        // [20, 40, 30, 50]

        // Remove specific element
        pq.remove(30);
        System.out.println("After remove(30): " + pq);
        // [20, 40, 50]

        // Traversals
        System.out.print("For-Each: ");
        for (int x : pq) {
            System.out.print(x + " ");
        }
        System.out.println();
        // For-Each: 20 40 50 

        System.out.print("Iterator: ");
        Iterator<Integer> itr = pq.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        // Iterator: 20 40 50

        System.out.print("forEach (Lambda): ");
        pq.forEach(x -> System.out.print(x + " "));
        System.out.println();
        // forEach (Lambda): 20 40 50

        // Custom Ordering (Max-Heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x,y)->y-x);

        maxHeap.offer(50);
        maxHeap.offer(10);
        maxHeap.offer(30);
        maxHeap.offer(20);
        maxHeap.offer(40);

        System.out.println("PriorityQueue (Max-Heap): " + maxHeap);
        // [50, 40, 30, 10, 20]

        System.out.println("Peek: " + maxHeap.peek()); // 50

        // Utility
        System.out.println("Size: " + maxHeap.size()); // 5
        System.out.println("IsEmpty? " + maxHeap.isEmpty()); // false

        maxHeap.clear();
        System.out.println("After clear(): " + maxHeap); // []
    }
}
