import java.util.List;

public class IPHashLB {

    private final List<String> servers;

    public IPHashLB(List<String> servers) {
        this.servers = servers;
    }

    public String getServer(String clientIP) {
        if (servers == null || servers.isEmpty()) {
            return null;
        }

        int hash = Math.abs(clientIP.hashCode());
        int index = hash % servers.size();

        return servers.get(index);
    }

    public static void main(String[] args) {
        List<String> servers = List.of("Server1", "Server2", "Server3");

        IPHashLB lb = new IPHashLB(servers);

        String ip = "192.168.1.10";

        System.out.println(lb.getServer(ip));
        System.out.println(lb.getServer(ip)); // same server
    }
}