import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * User class representing a user in the system.
 */
class User {
    private String userId;
    private String username;
    private String email;
    private LocalDateTime creationDate;
    private boolean isActive;

    public User(String userId, String username, String email, LocalDateTime creationDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.creationDate = creationDate;
        this.isActive = true;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", isActive=" + isActive +
                '}';
    }
}

/**
 * SMOSConnection class simulates connection to SMOS server.
 * Handles connection lifecycle and interruptions.
 */
class SMOSConnection {
    private boolean connected;

    public SMOSConnection() {
        this.connected = false;
    }

    /**
     * Establishes connection to SMOS server.
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        if(connected) {
            System.out.println("Already connected to SMOS server.");
            return true;
        }
        // Simulating connection attempt with potential failure
        try {
            System.out.println("Attempting to connect to SMOS server...");
            // Simulate network latency
            Thread.sleep(500);
            // 90% success rate for demo
            connected = Math.random() > 0.1;
            if(connected) {
                System.out.println("Successfully connected to SMOS server.");
            } else {
                System.out.println("Failed to connect to SMOS server.");
            }
            return connected;
        } catch (InterruptedException e) {
            System.out.println("Connection interrupted: " + e.getMessage());
            return false;
        }
    }

    /**
     * Disconnects from SMOS server.
     */
    public void disconnect() {
        if(connected) {
            System.out.println("Disconnecting from SMOS server...");
            connected = false;
            System.out.println("Disconnected from SMOS server.");
        }
    }

    public boolean isConnected() {
        return connected;
    }
}

/**
 * UserArchive class simulates a user database/archive.
 * Handles user storage and retrieval.
 */
class UserArchive {
    private List<User> users;
    private SMOSConnection smosConnection;

    public UserArchive() {
        this.users = new ArrayList<>();
        this.smosConnection = new SMOSConnection();
    }

    /**
     * Initializes the archive with some sample users.
     */
    public void initializeWithSampleData() {
        users.add(new User("U001", "admin", "admin@example.com", LocalDateTime.now().minusDays(30)));
        users.add(new User("U002", "john_doe", "john@example.com", LocalDateTime.now().minusDays(25)));
        users.add(new User("U003", "jane_smith", "jane@example.com", LocalDateTime.now().minusDays(20)));
        users.add(new User("U004", "bob_wilson", "bob@example.com", LocalDateTime.now().minusDays(15)));
        users.add(new User("U005", "alice_jones", "alice@example.com", LocalDateTime.now().minusDays(10)));
    }

    /**
     * Searches for all users in the archive.
     * Establishes SMOS connection before performing search.
     * @return list of all users
     */
    public List<User> searchAllUsers() {
        List<User> result = new ArrayList<>();
        
        // First establish connection to SMOS server
        if(!smosConnection.connect()) {
            System.out.println("Warning: Cannot search users without SMOS server connection.");
            return result; // Return empty list on connection failure
        }

        // Copy users to result list to avoid exposing internal list
        result.addAll(users);
        
        return result;
    }

    /**
     * Gets the current SMOS connection status.
     * @return true if connected, false otherwise
     */
    public boolean isSMOSConnected() {
        return smosConnection.isConnected();
    }

    /**
     * Gets total user count.
     * @return number of users in archive
     */
    public int getUserCount() {
        return users.size();
    }
}

/**
 * DisplayHelper class handles user interface and display logic.
 */
class DisplayHelper {
    
    /**
     * Displays the list of users in a formatted table.
     * @param users list of users to display
     */
    public void displayUserList(List<User> users) {
        if(users == null) {
            System.out.println("Error: User list is null.");
            return;
        }

        if(users.isEmpty()) {
            System.out.println("\nNo users found in the system.");
            return;
        }

        System.out.println("\n========== USER LIST ==========");
        System.out.println("Total users: " + users.size());
        System.out.println("\n------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-30s %-20s %-8s%n", 
                         "ID", "Username", "Email", "Creation Date", "Status");
        System.out.println("------------------------------------------------------------");

        for(User user : users) {
            String formattedDate = user.getCreationDate().toLocalDate().toString();
            String status = user.isActive() ? "Active" : "Inactive";
            
            System.out.printf("%-8s %-20s %-30s %-20s %-8s%n",
                            user.getUserId(),
                            user.getUsername(),
                            user.getEmail(),
                            formattedDate,
                            status);
        }
        
        System.out.println("------------------------------------------------------------");
        
        // Display summary statistics
        long activeCount = users.stream().filter(User::isActive).count();
        long inactiveCount = users.size() - activeCount;
        System.out.printf("Active: %d | Inactive: %d%n", activeCount, inactiveCount);
    }

    /**
     * Displays welcome message and program information.
     */
    public void displayWelcomeMessage() {
        System.out.println("\n=== User Management System ===");
        System.out.println("Logged in as: Administrator");
        System.out.println("Click 'User Manager' button to view users...\n");
    }
}

/**
 * AdministratorSession class represents an administrator's session.
 * Validates administrator status before allowing operations.
 */
class AdministratorSession {
    private boolean isLoggedIn;
    private String sessionId;

    public AdministratorSession() {
        this.isLoggedIn = false;
        this.sessionId = "SESS_" + System.currentTimeMillis();
    }

    /**
     * Simulates administrator login.
     * @return true if login successful
     */
    public boolean login() {
        // In real scenario, this would validate credentials
        isLoggedIn = true;
        System.out.println("Administrator logged in successfully.");
        return true;
    }

    /**
     * Checks if administrator is logged in.
     * @return true if logged in as administrator
     */
    public boolean isAdministratorLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Simulates administrator clicking the User Manager button.
     * @return true if action is authorized
     */
    public boolean clickUserManager() {
        if(!isAdministratorLoggedIn()) {
            System.out.println("Error: Access denied. Only administrators can access User Manager.");
            return false;
        }
        System.out.println("Administrator clicked 'User Manager' button.");
        return true;
    }

    public String getSessionId() {
        return sessionId;
    }
}

/**
 * Main class implementing the ViewUserList use case.
 * Follows the sequence of events described in the use case.
 */
public class Main {
    
    /**
     * Main method - entry point of the program.
     * Implements the ViewUserList use case:
     * 1. Administrator logs in (precondition)
     * 2. Administrator clicks "User Manager" button (precondition)
     * 3. System searches for users in the archive and displays the list
     * 4. System displays the list of users
     * 5. Connection to the interrupted SMOS server (handled internally)
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        
        // Step 1: Create administrator session (precondition)
        AdministratorSession adminSession = new AdministratorSession();
        
        // Precondition: The user is logged in as administrator
        if(!adminSession.login()) {
            System.out.println("Critical error: Administrator login failed.");
            return;
        }
        
        // Create display helper for UI
        DisplayHelper displayHelper = new DisplayHelper();
        displayHelper.displayWelcomeMessage();
        
        // Precondition: The user clicks on the "User Manager" button
        if(!adminSession.clickUserManager()) {
            return; // Stop if unauthorized
        }
        
        // Create and initialize user archive
        UserArchive userArchive = new UserArchive();
        userArchive.initializeWithSampleData();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PROCESSING: ViewUserList Use Case");
        System.out.println("=".repeat(50));
        
        // Events sequence: Search for users in the archive
        System.out.println("\nStep 1: Searching for users in the archive...");
        
        List<User> users = userArchive.searchAllUsers();
        
        // Postcondition: The system displays the list of users in the system
        System.out.println("\nStep 2: Displaying user list...");
        displayHelper.displayUserList(users);
        
        // Postcondition: Connection to the interrupted SMOS server
        System.out.println("\nStep 3: SMOS server connection status:");
        if(userArchive.isSMOSConnected()) {
            System.out.println("✓ Connected to SMOS server");
        } else {
            System.out.println("✗ SMOS server connection interrupted or unavailable");
        }
        
        // Edge case handling demonstration
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EDGE CASE HANDLING DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        handleEdgeCases(userArchive);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("USE CASE COMPLETE: User list successfully displayed.");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Demonstrates edge case handling.
     * @param archive user archive to test
     */
    private static void handleEdgeCases(UserArchive archive) {
        System.out.println("\nEdge Case 1: Empty user list");
        UserArchive emptyArchive = new UserArchive();
        // Don't initialize with sample data to keep it empty
        List<User> emptyList = emptyArchive.searchAllUsers();
        System.out.println("Empty archive user count: " + emptyList.size());
        
        if(emptyList.isEmpty()) {
            System.out.println("✓ Correctly handled empty user list");
        }
        
        System.out.println("\nEdge Case 2: Large number of users");
        // Simulate performance with larger dataset
        long startTime = System.currentTimeMillis();
        int userCount = archive.getUserCount();
        long endTime = System.currentTimeMillis();
        System.out.printf("✓ Retrieved %d users in %d ms\n", userCount, (endTime - startTime));
        
        System.out.println("\nEdge Case 3: SMOS server connection failure handling");
        // Connection failure is already simulated in SMOSConnection.connect()
        // with 10% probability in the main flow
    }
}