import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Stream5ParallelStream {
    public static void main(String[] args) throws Exception {

        List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe", "Alice", "Bob");

        // Using default parallel stream (uses ForkJoinPool.commonPool())
        names.parallelStream()
                .map(String::toUpperCase)
                .forEach(name ->
                        System.out.println(Thread.currentThread().getName() + " -> " + name)
                );

        // Sample Output (order may vary because it is parallel):
        // ForkJoinPool.commonPool-worker-1 -> JOHN
        // main -> JANE
        // ForkJoinPool.commonPool-worker-3 -> JACK
        // ForkJoinPool.commonPool-worker-5 -> DOE
        // ForkJoinPool.commonPool-worker-7 -> ALICE
        // main -> BOB

        System.out.println("---- Using custom ForkJoinPool ----");
        // ---- Using custom ForkJoinPool ----

        // Using custom ForkJoinPool with 3 threads
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        forkJoinPool.submit(() ->
                names.parallelStream()
                        .map(String::toUpperCase)
                        .forEach(name ->
                                System.out.println(Thread.currentThread().getName() + " -> " + name)
                        )
        ).get();

        // Sample Output (order may vary):
        // ForkJoinPool-1-worker-1 -> JOHN
        // ForkJoinPool-1-worker-2 -> JANE
        // ForkJoinPool-1-worker-3 -> JACK
        // ForkJoinPool-1-worker-1 -> DOE
        // ForkJoinPool-1-worker-2 -> ALICE
        // ForkJoinPool-1-worker-3 -> BOB

        forkJoinPool.shutdown();

        forkJoinPool.close();
    }
}
