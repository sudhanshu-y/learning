import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightedRoundRobinLB {

    private final List<String> extendedServers;
    private final AtomicInteger index;

    public WeightedRoundRobinLB(Map<String, Integer> serverWeights) {
        this.extendedServers = new ArrayList<>();
        this.index = new AtomicInteger(0);

        serverWeights.forEach((server, weight) -> {
            for (int i = 0; i < weight; i++) {
                extendedServers.add(server);
            }
        });
    }

    public String getServer() {
        if (extendedServers.isEmpty()) return null;
        
        int n = extendedServers.size();
        int currentIndex = index.getAndUpdate(i -> (i + 1) % n);

        return extendedServers.get(currentIndex);
    }

    public static void main(String[] args) {

        Map<String, Integer> servers = Map.of(
                "Server1", 3,
                "Server2", 1,
                "Server3", 2
        );

        WeightedRoundRobinLB lb = new WeightedRoundRobinLB(servers);

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + " -> " + lb.getServer());
        }
    }
}