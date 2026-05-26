import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LeastResponseTimeLB {

    private static final long W = 50;
    private static final double alpha = 0.7;

    private final List<Server> servers;

    static class Server {
        final String name;
        final AtomicInteger activeConnections;
        final AtomicLong avgResponseTime;

        Server(String name) {
            this.name = name;
            this.activeConnections = new AtomicInteger(0);
            this.avgResponseTime = new AtomicLong(0);
        }

        // Score = AvgResponseTime + (ActiveConnections × Weight)
        // AvgResponseTime alone is NOT enough to calculate the score.
        // Example:
        // Server A → Avg = 50 ms (currently idle)
        // Server B → Avg = 80 ms
        // keep sending traffic to Server A -> A gets overloaded
        // Low weight → prefer fast servers
        // High weight → prefer less busy servers
        long getScore() {
            return avgResponseTime.get() + W * activeConnections.get();
        }
    }

    public LeastResponseTimeLB(List<String> serverNames) {
        List<Server> servers = new ArrayList<>();
        serverNames.forEach(s -> servers.add(new Server(s)));
        this.servers = servers;
    }

    // Get least response time server
    public String getServer() {
        Server bestServer = servers.stream()
                .min(Comparator.comparing(Server::getScore))
                .orElse(null);

        bestServer.activeConnections.incrementAndGet();
        return bestServer.name;
    }

    public void updateResponseTime(String serverName, long newResponseTime) {
        for (Server s : servers) {
            if (s.name.equals(serverName)) {
                s.activeConnections.updateAndGet(i -> Math.min(0, i - 1));
                s.avgResponseTime.updateAndGet(
                        old -> (long) (old * alpha + newResponseTime * (1 - alpha))
                );
            }
        }
    }

    public static void main(String[] args) {
        List<String> servers = List.of("Server1", "Server2", "Server3");
        LeastResponseTimeLB lb = new LeastResponseTimeLB(servers);
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            String server = lb.getServer();
            long newResponseTime = 50 + r.nextInt(200);

            System.out.println("Request " + i + " -> " + server);
            lb.updateResponseTime(server, newResponseTime);
        }
    }
}