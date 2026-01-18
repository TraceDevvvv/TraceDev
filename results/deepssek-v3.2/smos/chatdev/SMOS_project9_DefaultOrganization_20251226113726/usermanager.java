import java.util.ArrayList;
import java.util.List;
/**
 * UserManager class manages the collection of users and provides operations
 * to add, delete, and retrieve users. This includes the DeleteUser functionality.
 */
public class UserManager {
    private List<User> users;
    private boolean smosServerConnected;
    public UserManager() {
        this.users = new ArrayList<>();
        this.smosServerConnected = true; // Initially connected
        initializeSampleUsers();
    }
    /**
     * Initialize with some sample users for testing
     */
    private void initializeSampleUsers() {
        users.add(new User("admin", "admin@example.com", "Administrator", true));
        users.add(new User("john", "john@example.com", "User", false));
        users.add(new User("jane", "jane@example.com", "User", false));
        users.add(new User("bob", "bob@example.com", "Moderator", false));
    }
    /**
     * Verifies if the logged in user is an administrator
     * @param username The username to check
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdministrator(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isAdmin()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Finds a user by username for displaying details (simulating viewdetTailsente use case)
     * @param username The username to find
     * @return The user object or null if not found
     */
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    /**
     * Deletes a user from the archive (main DeleteUser functionality)
     * @param username The username to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                // Postcondition: Interrupt connection to SMOS server
                interruptSMOSConnection();
                return true;
            }
        }
        return false;
    }
    /**
     * Displays the list of updated users after deletion
     */
    public void displayUpdatedUserList() {
        System.out.println("Updated User List:");
        System.out.println("==================");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("==================");
    }
    /**
     * Gets the current list of users
     * @return List of users
     */
    public List<User> getUsers() {
        return new ArrayList<>(users); // Return a copy for encapsulation
    }
    /**
     * Interrupts connection to SMOS server as per postconditions
     */
    private void interruptSMOSConnection() {
        this.smosServerConnected = false;
        System.out.println("Note: Connection to SMOS server has been interrupted.");
    }
    /**
     * Check if SMOS server is connected
     * @return connection status
     */
    public boolean isSMOSServerConnected() {
        return smosServerConnected;
    }
    /**
     * Reconnect to SMOS server (for testing purposes)
     */
    public void reconnectSMOSServer() {
        this.smosServerConnected = true;
        System.out.println("SMOS server reconnected.");
    }
}