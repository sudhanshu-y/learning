import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {

    private final Set<String> servers;  // Physical servers 
    private final int noVNs;    // Virtual nodes per server
    private final TreeMap<Integer, String> ring;    // Hash ring

    public ConsistentHashing(Set<String> servers, int noVNs) {
        this.servers = new HashSet<>();
        this.noVNs = noVNs;
        this.ring = new TreeMap<>();

        // Add initial servers
        servers.forEach(server -> add(server));
    }

    // Hash function (ensures non-negative)
    private int hash(String key) {
        return key.hashCode() & 0x7fffffff;
    }
    // 7fffffff in hex = 01111111 11111111 11111111 11111111 in binary.
    // String.hashCode() can return negative values because int is signed.
    // hash & 0x7fffffff; // ensures non-negative

    public void add(String server) {
        if (servers.contains(server)) return;

        servers.add(server);

        for (int i = 0; i < noVNs; i++) {
            int hash = hash(server + "-VN-" + i);
            ring.put(hash, server);
        }
    }

    public void remove(String server) {
        if (!servers.contains(server)) return;

        servers.remove(server);
        
        for (int i = 0; i < noVNs; i++) {
            int hash = hash(server + "-VN-" + i);
            ring.remove(hash);
        }
    }

    // Find to which server, data request goes. 
    public String get(String key) {
        if (ring.isEmpty()) return null;

        int hash = hash(key);

        // Get all servers whose hash is ≥ key’s hash
        SortedMap<Integer, String> tailMap = ring.tailMap(hash);

        return tailMap.isEmpty() ? ring.get(ring.firstKey()) : tailMap.get(tailMap.firstKey());
        // tailMap empty --> Key is bigger than all server hashes -- Pick the first server
    }

    public static void main(String[] args) {

        List<String> nodes = Arrays.asList("Server1", "Server2", "Server3");
        ConsistentHashing ch = new ConsistentHashing(new HashSet<>(nodes), 100);

        String[] keys = {"mango", "apple", "banana", "cherry", "pineapple", "fig", "grape"};

        System.out.println("Initial key mappings:");
        for (String key : keys) {
            System.out.println("Key '" + key + "' -> " + ch.get(key));
        }

        System.out.println("\nAdding Server4...");
        ch.add("Server4");

        System.out.println("\nAfter adding Server4:");
        for (String key : keys) {
            System.out.println("Key '" + key + "' -> " + ch.get(key));
        }

        System.out.println("\nRemoving Server2...");
        ch.remove("Server2");

        System.out.println("\nAfter removing Server2:");
        for (String key : keys) {
            System.out.println("Key '" + key + "' -> " + ch.get(key));
        }
    }
}