/**
 * Simulates a database of users with connection management.
 * Handles user data storage and connection interruption simulation.
 */
import java.util.ArrayList;
import java.util.List;
public class UserDatabase {
    private List<User> users;
    private boolean connected;
    public UserDatabase() {
        users = new ArrayList<>();
        connected = true;
        // Populate with sample users for demonstration
        users.add(new User("John", "Doe", "john.doe@example.com", "1234567890", "johndoe", "password123"));
        users.add(new User("Jane", "Smith", "jane.smith@example.com", "0987654321", "janesmith", "securepass"));
        users.add(new User("Alice", "Johnson", "alice.j@example.com", "5551234567", "alicej", "alicepass"));
        users.add(new User("Bob", "Brown", "bob.brown@example.com", "4445556666", "bobbrown", "bobpass123"));
        users.add(new User("Charlie", "Wilson", "charlie.w@example.com", "7778889999", "charliew", "charliepass"));
    }
    /**
     * Returns a copy of the users list to prevent external modification
     * @return List of users in the database
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    /**
     * Method to simulate interruption of connection to SMOS server
     * Implements the postcondition from the use case
     */
    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("Connection to the SMOS server interrupted.");
        }
    }
    /**
     * Check if database connection is active
     * @return Connection status
     */
    public boolean isConnected() {
        return connected;
    }
}