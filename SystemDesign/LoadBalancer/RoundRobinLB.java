import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLB {

    private final List<String> servers;
    private final AtomicInteger index;

    public RoundRobinLB(List<String> servers) {
        this.servers = servers;
        this.index = new AtomicInteger(0);
    }

    public String getServer() {
        if (servers == null || servers.isEmpty()) {
            return null;
        }

        int n = servers.size();
        int currentIndex = index.getAndUpdate(i -> (i + 1) % n);

        return servers.get(currentIndex);
    }

    public static void main(String[] args) {

        List<String> servers = List.of("Server1", "Server2", "Server3");

        RoundRobinLB lb = new RoundRobinLB(servers);

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + " -> " + lb.getServer());
        }
    }
}