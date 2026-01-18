import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Service class for login operations.
 * Validates user credentials against a simulated user database,
 * handles server connection simulation, and throws LoginError on failure.
 * This class follows the use case flow: check correctness of data,
 * handle cancellation, and simulate server interruption.
 */
public class LoginService {
    
    // Simulated in-memory database of registered users
    // In a real system, this would be replaced with a database connection
    private final Map<String, User> userDatabase;
    
    // Flag to simulate server connection status
    private boolean serverConnected;
    
    /**
     * Constructs a new LoginService with a pre-populated user database
     * and initial server connection status.
     */
    public LoginService() {
        this.userDatabase = new HashMap<>();
        this.serverConnected = true; // Start with server connected
        
        // Initialize with some sample users for demonstration
        // In a real system, users would be loaded from a database
        initializeSampleUsers();
    }
    
    /**
     * Initializes the user database with sample users for testing.
     * Each user has username, password, and privileges.
     */
    private void initializeSampleUsers() {
        userDatabase.put("admin", new User("admin", "admin123", "administrator"));
        userDatabase.put("user1", new User("user1", "password1", "standard_user"));
        userDatabase.put("user2", new User("user2", "password2", "standard_user"));
        userDatabase.put("john_doe", new User("john_doe", "securePass", "manager"));
    }
    
    /**
     * Simulates checking server connection.
     * In a real system, this would make an actual network check.
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean checkServerConnection() {
        // Simulate occasional server disconnection (1 in 10 chance for demonstration)
        // In production, this would be a real network check
        if (Math.random() < 0.1) {
            serverConnected = false;
        }
        return serverConnected;
    }
    
    /**
     * Simulates reconnecting to the server.
     * 
     * @return true if reconnection successful, false otherwise
     */
    public boolean reconnectToServer() {
        // Simulate reconnection attempt
        serverConnected = true;
        return serverConnected;
    }
    
    /**
     * Validates user credentials against the user database.
     * 
     * @param username the username to validate
     * @param password the password to validate
     * @return the authenticated User object if credentials are valid
     * @throws LoginError if validation fails for any reason:
     *         - Server connection interrupted
     *         - Username not found
     *         - Password incorrect
     *         - Empty credentials
     */
    public User validateLogin(String username, String password) throws LoginError {
        // Check if user cancelled (simulated by empty username)
        if (username == null || username.trim().isEmpty()) {
            throw new LoginError("Login cancelled by user");
        }
        
        // Check server connection
        if (!checkServerConnection()) {
            throw new LoginError("Server connection interrupted (ETOUR). Please try again later.");
        }
        
        // Validate input format
        if (username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new LoginError("Username and password cannot be empty");
        }
        
        String trimmedUsername = username.trim();
        String trimmedPassword = password.trim();
        
        // Check if user exists in database
        User user = userDatabase.get(trimmedUsername);
        if (user == null) {
            throw new LoginError("User not found. Please check your username.");
        }
        
        // Validate password
        if (!user.validateCredentials(trimmedUsername, trimmedPassword)) {
            throw new LoginError("Incorrect password. Please try again.");
        }
        
        // Login successful
        return user;
    }
    
    /**
     * Displays the login form and collects user input.
     * This simulates the form display mentioned in the use case.
     * 
     * @param scanner the Scanner object to read user input
     * @return an array containing username and password, or null if cancelled
     */
    public String[] displayLoginForm(Scanner scanner) {
        System.out.println("\n=== LOGIN FORM ===");
        System.out.println("Enter your credentials (or type 'cancel' to exit):");
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        // Check for cancellation
        if (username.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        // Check for cancellation in password field
        if (password.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        return new String[]{username, password};
    }
    
    /**
     * Displays the work area for a successfully logged-in user.
     * This represents the "area of work registered" from the use case.
     * 
     * @param user the authenticated user
     */
    public void displayWorkArea(User user) {
        System.out.println("\n=== WORK AREA ===");
        System.out.println("Welcome, " + user.getUsername() + "!");
        System.out.println("Your privileges: " + user.getPrivileges());
        System.out.println("\nAvailable actions based on your privileges:");
        
        // Display different options based on user privileges
        switch (user.getPrivileges().toLowerCase()) {
            case "administrator":
                System.out.println("1. Manage users");
                System.out.println("2. System configuration");
                System.out.println("3. View all reports");
                System.out.println("4. Audit logs");
                break;
            case "manager":
                System.out.println("1. View team reports");
                System.out.println("2. Approve requests");
                System.out.println("3. Manage projects");
                break;
            case "standard_user":
                System.out.println("1. View personal dashboard");
                System.out.println("2. Submit requests");
                System.out.println("3. Update profile");
                break;
            default:
                System.out.println("1. Access basic features");
                System.out.println("2. View help documentation");
        }
        
        System.out.println("\nSelect an option or type 'logout' to exit.");
    }
    
    /**
     * Handles the complete login process as per the use case flow.
     * 1. Activates login feature
     * 2. Displays form
     * 3. Collects and submits credentials
     * 4. Validates data
     * 5. Displays work area or handles errors
     * 
     * @param scanner the Scanner object for user input
     */
    public void processLogin(Scanner scanner) {
        System.out.println("Login feature activated...");
        
        try {
            // Display form and get credentials
            String[] credentials = displayLoginForm(scanner);
            
            // Check if user cancelled
            if (credentials == null) {
                System.out.println("Login operation cancelled by user.");
                return;
            }
            
            // Validate login
            User authenticatedUser = validateLogin(credentials[0], credentials[1]);
            
            // Display work area for successful login
            displayWorkArea(authenticatedUser);
            
        } catch (LoginError e) {
            // Handle login errors as per use case
            System.out.println("\nLogin Error: " + e.getMessage());
            
            // Offer retry option for certain errors
            if (e.getMessage().contains("Server connection interrupted")) {
                System.out.println("Attempting to reconnect...");
                if (reconnectToServer()) {
                    System.out.println("Reconnected successfully. Please try logging in again.");
                } else {
                    System.out.println("Unable to reconnect. Please contact system administrator.");
                }
            }
        } catch (Exception e) {
            // Handle unexpected errors
            System.out.println("\nUnexpected error during login: " + e.getMessage());
            System.out.println("Please contact system administrator.");
        }
    }
}