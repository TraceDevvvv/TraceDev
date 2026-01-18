import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TouristService {
    private Map<String, Tourist> touristDatabase;
    private static final double CONNECTION_FAILURE_PROBABILITY = 0.1; // 10% chance of server failure

    public TouristService() {
        touristDatabase = new HashMap<>();
        // Simulate initial data
        SearchPreference defaultPref = new SearchPreference(100.0, 10.0, Arrays.asList("Museum", "Park"), 3);
        touristDatabase.put("john", new Tourist("john", "pass123", defaultPref));
        touristDatabase.put("alice", new Tourist("alice", "alice456", defaultPref));
    }

    public boolean authenticate(String username, String password) {
        checkServerConnection();
        Tourist tourist = touristDatabase.get(username);
        return tourist != null && tourist.getPassword().equals(password);
    }

    public Tourist getTourist(String username) {
        checkServerConnection();
        Tourist tourist = touristDatabase.get(username);
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist not found.");
        }
        return tourist;
    }

    public void updateTourist(Tourist updatedTourist) {
        checkServerConnection();
        touristDatabase.put(updatedTourist.getUsername(), updatedTourist);
    }

    // Simulate potential server connection interruption
    private void checkServerConnection() {
        if (ThreadLocalRandom.current().nextDouble() < CONNECTION_FAILURE_PROBABILITY) {
            throw new ServerConnectionException("ETOUR server connection interrupted.");
        }
    }
}