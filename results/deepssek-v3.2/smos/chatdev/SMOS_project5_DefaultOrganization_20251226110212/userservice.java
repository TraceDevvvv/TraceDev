'''
Service class that simulates user data retrieval from a database.
Includes SMOS server connection simulation with realistic error handling.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class UserService {
    private boolean smosConnectionInterrupted;
    private Random random;
    public UserService() {
        this.random = new Random();
        // Initialize with 30% chance of interrupted connection (for simulation)
        this.smosConnectionInterrupted = random.nextInt(10) < 3;
    }
    /**
     * Simulates searching for users in the archive.
     * Returns a list of User objects.
     * @throws RuntimeException if SMOS server connection is interrupted
     */
    public List<User> getUsers() throws RuntimeException {
        // Check SMOS server connection before proceeding
        if (smosConnectionInterrupted) {
            throw new RuntimeException("SMOS server connection interrupted. Cannot retrieve user data.");
        }
        List<User> users = new ArrayList<>();
        // Simulate database data
        users.add(new User("1", "admin", "admin@example.com", "Administrator", "2023-01-15"));
        users.add(new User("2", "johndoe", "john.doe@example.com", "User", "2023-02-20"));
        users.add(new User("3", "janesmith", "jane.smith@example.com", "Manager", "2023-03-10"));
        users.add(new User("4", "bobjohnson", "bob.johnson@example.com", "User", "2023-04-05"));
        users.add(new User("5", "alicewilliams", "alice.williams@example.com", "User", "2023-05-12"));
        users.add(new User("6", "charliebrown", "charlie.brown@example.com", "Analyst", "2023-06-18"));
        users.add(new User("7", "dianamiller", "diana.miller@example.com", "User", "2023-07-22"));
        users.add(new User("8", "edwarddavis", "edward.davis@example.com", "Manager", "2023-08-30"));
        return users;
    }
    /**
     * Checks SMOS server connection status.
     * @return true if connection is active, false if interrupted
     */
    public boolean isSmosConnectionInterrupted() {
        return smosConnectionInterrupted;
    }
    /**
     * Attempts to reconnect to SMOS server.
     * @return true if reconnection successful, false otherwise
     */
    public boolean reconnectSMOS() {
        // Simulate reconnection attempt with 70% success rate
        smosConnectionInterrupted = random.nextInt(10) >= 7;
        return !smosConnectionInterrupted;
    }
    /**
     * Simulates connection interruption event.
     */
    public void interruptSMOSConnection() {
        smosConnectionInterrupted = true;
    }
}