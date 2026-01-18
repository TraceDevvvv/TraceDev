import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DatabaseSimulator class simulates database operations for user registration.
 * This class stores users in memory and provides methods to check for duplicates,
 * insert new users, and retrieve user information.
 * It simulates the "Inserts the registration request into the system" step from the use case.
 */
public class DatabaseSimulator {
    
    // In-memory storage for users (simulating database tables)
    private List<User> users;
    
    // Indexes for fast lookup (simulating database indexes)
    private Map<String, User> usernameIndex;
    private Map<String, User> emailIndex;
    
    // Counter for generating unique user IDs (simulating auto-increment primary key)
    private int nextUserId;
    
    /**
     * Constructor initializes the simulated database
     */
    public DatabaseSimulator() {
        users = new ArrayList<>();
        usernameIndex = new HashMap<>();
        emailIndex = new HashMap<>();
        nextUserId = 1;
        
        // Add some sample users for testing (optional)
        initializeSampleData();
    }
    
    /**
     * Initializes the database with some sample users for testing
     */
    private void initializeSampleData() {
        // Add a few sample users to demonstrate duplicate checking
        User sampleUser1 = new User("John", "Doe", "+1234567890", 
                                    "john.doe@example.com", "johndoe", 
                                    "Password123!", "Password123!");
        insertUser(sampleUser1);
        
        User sampleUser2 = new User("Jane", "Smith", "+0987654321", 
                                    "jane.smith@example.com", "janesmith", 
                                    "SecurePass456@", "SecurePass456@");
        insertUser(sampleUser2);
    }
    
    /**
     * Inserts a new user into the simulated database
     * 
     * @param user the User object to insert
     * @return true if insertion was successful, false if user already exists
     */
    public boolean insertUser(User user) {
        if (user == null) {
            System.out.println("Error: Cannot insert null user");
            return false;
        }
        
        String username = user.getUsername();
        String email = user.getEmail();
        
        // Check for duplicates before insertion
        if (usernameIndex.containsKey(username)) {
            System.out.println("Error: Username '" + username + "' already exists in the database");
            return false;
        }
        
        if (emailIndex.containsKey(email)) {
            System.out.println("Error: Email '" + email + "' already exists in the database");
            return false;
        }
        
        // Create a copy of the user to avoid external modification
        User userToStore = user.copy();
        
        // Add to the main users list
        users.add(userToStore);
        
        // Update indexes for fast lookup
        usernameIndex.put(username, userToStore);
        emailIndex.put(email, userToStore);
        
        // Simulate database commit/transaction
        System.out.println("Database: User '" + username + "' successfully inserted into the system.");
        
        return true;
    }
    
    /**
     * Checks if a username already exists in the database
     * 
     * @param username the username to check
     * @return true if username exists, false otherwise
     */
    public boolean isUsernameExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return usernameIndex.containsKey(username.trim());
    }
    
    /**
     * Checks if an email already exists in the database
     * 
     * @param email the email to check
     * @return true if email exists, false otherwise
     */
    public boolean isEmailExists(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return emailIndex.containsKey(email.trim().toLowerCase());
    }
    
    /**
     * Retrieves a user by username
     * 
     * @param username the username to search for
     * @return the User object if found, null otherwise
     */
    public User getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return usernameIndex.get(username.trim());
    }
    
    /**
     * Retrieves a user by email
     * 
     * @param email the email to search for
     * @return the User object if found, null otherwise
     */
    public User getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return emailIndex.get(email.trim().toLowerCase());
    }
    
    /**
     * Gets all registered users
     * 
     * @return a list of all users in the database
     */
    public List<User> getAllUsers() {
        // Return a copy to prevent external modification
        return new ArrayList<>(users);
    }
    
    /**
     * Gets the total number of registered users
     * 
     * @return the count of users in the database
     */
    public int getUserCount() {
        return users.size();
    }
    
    /**
     * Displays all registered users in a formatted way
     * Useful for demonstration and debugging
     */
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered in the system.");
            return;
        }
        
        System.out.println("Total registered users: " + users.size());
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ". " + user.getName() + " " + user.getSurname());
            System.out.println("   Username: " + user.getUsername());
            System.out.println("   Email: " + user.getEmail());
            System.out.println("   Phone: " + user.getMobilePhone());
            System.out.println("----------------------------------------");
        }
    }
    
    /**
     * Simulates a database connection interruption
     * This method represents the postcondition scenario where
     * "the connection to the SMOS server interrupted"
     * 
     * @return always returns false to simulate failed operation
     */
    public boolean simulateConnectionInterruption() {
        System.out.println("⚠️  Database Error: Connection to SMOS server interrupted.");
        System.out.println("   Registration request could not be processed.");
        System.out.println("   Please try again later.");
        return false;
    }
    
    /**
     * Clears all users from the database (for testing/reset purposes)
     * Use with caution - this simulates database truncate operation
     */
    public void clearAllUsers() {
        users.clear();
        usernameIndex.clear();
        emailIndex.clear();
        System.out.println("Database: All users have been cleared.");
    }
    
    /**
     * Simulates database backup operation
     * In a real system, this would create a backup of the database
     */
    public void simulateBackup() {
        System.out.println("Database: Creating backup of user data...");
        System.out.println("Database: Backup completed successfully.");
    }
    
    /**
     * Validates database integrity by checking consistency between lists and indexes
     * 
     * @return true if database is consistent, false otherwise
     */
    public boolean validateDatabaseIntegrity() {
        // Check if all users in the list are also in the indexes
        for (User user : users) {
            String username = user.getUsername();
            String email = user.getEmail();
            
            if (!usernameIndex.containsKey(username) || 
                !emailIndex.containsKey(email) ||
                usernameIndex.get(username) != user ||
                emailIndex.get(email) != user) {
                return false;
            }
        }
        
        // Check if all indexed users are in the list
        if (usernameIndex.size() != users.size() || emailIndex.size() != users.size()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Simulates the "inserts the registration request into the system" step
     * from the use case events sequence
     * 
     * @param user the user to register
     * @return true if registration was successful
     */
    public boolean processRegistrationRequest(User user) {
        System.out.println("System: Processing registration request...");
        
        // Simulate network/database latency
        try {
            Thread.sleep(500); // Simulate 500ms processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return simulateConnectionInterruption();
        }
        
        // Attempt to insert the user
        return insertUser(user);
    }
}