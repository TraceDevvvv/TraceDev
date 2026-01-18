/**
 * Simulates a backend service for managing tourist accounts.
 * This class handles the storage and modification of tourist account data,
 * including simulating potential connection issues to an external 'ETOUR' server.
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class TouristAccountService {
    // A map to store tourist accounts, simulating a database.
    // Key: touristId, Value: TouristAccount object.
    private Map<String, TouristAccount> accounts;
    private Random random; // For simulating random connection interruptions.
    /**
     * Constructor for TouristAccountService.
     * Initializes with some dummy data.
     */
    public TouristAccountService() {
        accounts = new HashMap<>();
        random = new Random();
        // Add some sample tourist accounts
        accounts.put("TR001", new TouristAccount("TR001", "Alice Smith", "alice@example.com", true));
        accounts.put("TR002", new TouristAccount("TR002", "Bob Johnson", "bob@example.com", false));
        accounts.put("TR003", new TouristAccount("TR003", "Charlie Brown", "charlie@example.com", true));
        accounts.put("TR004", new TouristAccount("TR004", "Diana Prince", "diana@example.com", false));
    }
    /**
     * Retrieves a tourist account by its ID.
     *
     * @param touristId The unique ID of the tourist.
     * @return The TouristAccount object if found, null otherwise.
     */
    public TouristAccount getAccount(String touristId) {
        return accounts.get(touristId);
    }
    /**
     * Returns a collection of all tourist accounts currently stored.
     *
     * @return A Collection of TouristAccount objects.
     */
    public Collection<TouristAccount> getAllAccounts() {
        return accounts.values();
    }
    /**
     * Updates the active status of a tourist account.
     * Simulates potential connection interruptions to an external 'ETOUR' server.
     *
     * @param touristId The ID of the tourist account to update.
     * @param newStatus The desired new status (true for active, false for disabled).
     * @return True if the update was successful, false if the account was not found.
     * @throws Exception If a simulated ETOUR connection interruption occurs.
     */
    public boolean updateAccountStatus(String touristId, boolean newStatus) throws Exception {
        // Simulate ETOUR server connection interruption with a 15% probability
        if (simulateETOURConnectionInterrupt()) {
            throw new Exception("Simulated ETOUR server connection interruption.");
        }
        TouristAccount account = accounts.get(touristId);
        if (account != null) {
            account.setActive(newStatus);
            return true;
        }
        return false; // Account not found
    }
    /**
     * Helper method to simulate a random connection interruption.
     *
     * @return True if a connection interruption is simulated, false otherwise.
     */
    private boolean simulateETOURConnectionInterrupt() {
        // 15% chance of connection interruption
        return random.nextInt(100) < 15;
    }
}