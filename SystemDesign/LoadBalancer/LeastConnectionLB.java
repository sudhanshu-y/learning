import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LeastConnectionLB {

    private final Map<String, AtomicInteger> serverLoadMap;

    public LeastConnectionLB(List<String> servers) {
        this.serverLoadMap = new ConcurrentHashMap<>();
        for (String server : servers) {
            serverLoadMap.put(server, new AtomicInteger(0));
        }
    }

    // Get server with least connections
    public String getServer() {
        String bestServer = null;
        int minLoad = Integer.MAX_VALUE;

        for (Map.Entry<String, AtomicInteger> entry : serverLoadMap.entrySet()) {
            int load = entry.getValue().get();

            if (load < minLoad) {
                minLoad = load;
                bestServer = entry.getKey();
            }
        }

        // Increment load for selected server
        serverLoadMap.get(bestServer).incrementAndGet();

        return bestServer;
    }

    // Call this when request finishes
    public void release(String server) {
        serverLoadMap.get(server).decrementAndGet();
    }

    public static void main(String[] args) {

        List<String> servers = List.of("Server1", "Server2", "Server3");
        LeastConnectionLB lb = new LeastConnectionLB(servers);

        // Simulate multiple requests
        for (int i = 0; i < 6; i++) {
            String server = lb.getServer();
            System.out.println("Request " + i + " -> " + server);
        }

        System.out.println("serverLoadMap: " + lb.serverLoadMap);

        System.out.println("Releasing some connections...");
        lb.release("Server1");
        lb.release("Server2");
        
        System.out.println("serverLoadMap: " + lb.serverLoadMap);
        System.out.println(lb.serverLoadMap);
    }
}