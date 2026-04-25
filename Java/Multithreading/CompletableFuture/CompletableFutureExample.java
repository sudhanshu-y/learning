import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) {

        // Custom thread pool (recommended in production)
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // runAsync()  → used when task does NOT return a result
        CompletableFuture<Void> runAsyncExample =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Running async task without return value");
                }, executor);

        runAsyncExample.join(); 
        // Running async task without return value



        // supplyAsync() → used when task RETURNS a result
        CompletableFuture<String> supplyAsyncExample =
                CompletableFuture.supplyAsync(() -> {
                    return "Hello from supplyAsync";
                }, executor);

        System.out.println(supplyAsyncExample.join());
        // Hello from supplyAsync



        // thenAccept(): consumes the result (no return)
        CompletableFuture<Void> thenAcceptExample =
                CompletableFuture.supplyAsync(() -> {
                    return "User Data";
                }, executor)
                .thenAccept(data -> {
                    System.out.println("Received: " + data);
                });

        thenAcceptExample.join();
        // Received: User Data



        // Chaining using thenApply(): transforms the result
        CompletableFuture<Integer> chainingExample =
                CompletableFuture.supplyAsync(() -> {
                    return 10;
                }, executor)
                .thenApply(num -> num * 2)      // multiply result
                .thenApply(num -> num + 5);     // add value

        System.out.println("Chaining result: " + chainingExample.join());
        // Chaining result: 25



        // Parallel API calls (very common in microservices)
        CompletableFuture<String> userService =
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(1000); } catch(Exception e){}
                    return "User Service Result";
                }, executor);

        CompletableFuture<String> orderService =
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(2000); } catch(Exception e){}
                    return "Order Service Result";
                }, executor);

        CompletableFuture<String> paymentService =
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(1500); } catch(Exception e){}
                    return "Payment Service Result";
                }, executor);


        // allOf() → wait for ALL tasks to finish
        CompletableFuture<Void> allServices = CompletableFuture.allOf(userService, orderService, paymentService);

        allServices.join();
        System.out.println(userService.join()); // User Service Result
        System.out.println(orderService.join()); // Order Service Result
        System.out.println(paymentService.join()); // Payment Service Result



        // thenCombine(): combine two futures
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20, executor);

        CompletableFuture<Integer> combined = future1.thenCombine(future2, (a, b) -> a + b);

        System.out.println("Combined result: " + combined.join());
        // Combined result: 30



        // thenCompose(): used when second task depends on first
        CompletableFuture<String> composeExample =
                CompletableFuture.supplyAsync(() -> {
                    return "User";
                }, executor)
                .thenCompose(user ->
                        CompletableFuture.supplyAsync(() -> {
                            return user + " Orders";
                        }, executor)
                );

        System.out.println("Compose result: " + composeExample.join());
        // Compose result: User Orders



        // Exception Handling using exceptionally()
        CompletableFuture<Integer> exceptionExample =
                CompletableFuture.supplyAsync(() -> {
                    if(true)
                        throw new RuntimeException("Something went wrong");
                    return 10;
                }, executor)
                .exceptionally(ex -> {
                    System.out.println("Exception handled: " + ex.getMessage());
                    return 0;
                });

        System.out.println("Exception result: " + exceptionExample.join());
        // Exception handled: java.lang.RuntimeException: Something went wrong
        // Exception result: 0
    }
}