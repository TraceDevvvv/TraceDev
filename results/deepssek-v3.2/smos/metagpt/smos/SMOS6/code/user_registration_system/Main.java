import java.util.Scanner;

/**
 * Main class - Entry point of the User Registration System.
 * This class simulates the admin login and coordinates the user registration flow
 * according to the specified use case.
 */
public class Main {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static UserService userService;
    private static UserRegistration userRegistration;
    private static SMOSConnection smosConnection;
    private static Scanner scanner;
    
    /**
     * Main method - Entry point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   USER REGISTRATION SYSTEM - SMOS");
        System.out.println("==========================================");
        
        // Initialize components
        initializeSystem();
        
        try {
            // Step 1: Administrator login (precondition)
            if (!authenticateAdministrator()) {
                System.out.println("Authentication failed. Exiting system.");
                return;
            }
            
            // Step 2: Connect to SMOS server (precondition)
            connectToSMOSServer();
            
            // Step 3: Main application loop
            runApplicationLoop();
            
        } finally {
            // Step 4: Cleanup and disconnect (postcondition)
            cleanup();
        }
    }
    
    /**
     * Initializes all system components
     */
    private static void initializeSystem() {
        userService = new UserService();
        userRegistration = new UserRegistration(userService);
        smosConnection = new SMOSConnection();
        scanner = new Scanner(System.in);
        
        System.out.println("System initialized successfully.");
        System.out.println("Loading user archive...");
        
        // Display system status
        if (userService.hasUsers()) {
            System.out.println("Found " + userService.getAllUsers().size() + " users in archive.");
        } else {
            System.out.println("No users found in archive. Archive is empty.");
        }
    }
    
    /**
     * Authenticates the administrator (precondition: user is logged in as administrator)
     * @return true if authentication successful, false otherwise
     */
    private static boolean authenticateAdministrator() {
        System.out.println("\n======= ADMINISTRATOR LOGIN =======");
        System.out.println("Precondition: User must be logged in as administrator");
        
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("\nLogin: ");
            String login = scanner.nextLine().trim();
            
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            
            if (ADMIN_LOGIN.equals(login) && ADMIN_PASSWORD.equals(password)) {
                System.out.println("\n✓ Authentication successful! Welcome, Administrator.");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("✗ Invalid credentials. " + attempts + " attempt(s) remaining.");
                } else {
                    System.out.println("✗ Maximum login attempts exceeded.");
                }
            }
        }
        
        return false;
    }
    
    /**
     * Connects to SMOS server (simulated)
     */
    private static void connectToSMOSServer() {
        System.out.println("\n======= CONNECTING TO SMOS SERVER =======");
        
        boolean connected = smosConnection.connect();
        if (connected) {
            System.out.println("✓ Successfully connected to SMOS server.");
        } else {
            System.out.println("✗ Failed to connect to SMOS server. Continuing in offline mode...");
        }
    }
    
    /**
     * Main application loop with menu system
     */
    private static void runApplicationLoop() {
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            
            System.out.print("\nEnter your choice (1-4): ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    // View list of users (precondition for new user registration)
                    viewUserList();
                    break;
                    
                case "2":
                    // Register new user (main use case)
                    registerNewUser();
                    break;
                    
                case "3":
                    // Search for a user
                    searchUser();
                    break;
                    
                case "4":
                    // Exit system
                    System.out.println("\nExiting User Registration System...");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Displays the main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n======= MAIN MENU =======");
        System.out.println("1. View List of Users");
        System.out.println("2. Register New User");
        System.out.println("3. Search User");
        System.out.println("4. Exit System");
        System.out.println("=========================");
    }
    
    /**
     * Handles viewing the list of users (precondition for new user registration)
     */
    private static void viewUserList() {
        System.out.println("\n======= VIEWING USER LIST =======");
        System.out.println("Precondition: Administrator must view user list before registering new user");
        
        if (userService.hasUsers()) {
            System.out.println(userService.viewAllUsersFormatted());
        } else {
            System.out.println("No users in the system.");
            System.out.println("You can proceed to register the first user.");
        }
    }
    
    /**
     * Handles the new user registration process (main use case)
     */
    private static void registerNewUser() {
        System.out.println("\n======= NEW USER REGISTRATION =======");
        System.out.println("Use Case: Inserting a new user in the system");
        
        // Check precondition: user must have viewed the list first
        System.out.println("Precondition check: Have you viewed the user list? (Y/N)");
        System.out.print("Enter Y to continue, N to view list first: ");
        String response = scanner.nextLine().trim();
        
        if (!response.equalsIgnoreCase("Y")) {
            System.out.println("Please view the user list first (select option 1 from main menu).");
            return;
        }
        
        System.out.println("\nClicking 'New user' button...");
        System.out.println("System displays user entry form with fields:");
        System.out.println("Name, Surname, Email, Cell, Login, Password, Confirm password");
        
        // Use the simplified registration flow for console compatibility
        boolean success = userRegistration.runRegistrationFlowSimple();
        
        // Handle postconditions
        if (success) {
            System.out.println("\n✓ POSTCONDITION: New user has been created successfully!");
        } else {
            System.out.println("\n✗ POSTCONDITION: Data error - registration failed.");
            System.out.println("Error case activated: User notified of data error.");
        }
        
        // Postcondition: Interrupt connection to SMOS server
        System.out.println("\nPOSTCONDITION: Interrupting connection to SMOS server...");
        smosConnection.disconnect();
        
        // Reconnect for next operation
        System.out.println("Reconnecting to SMOS server for next operation...");
        smosConnection.connect();
    }
    
    /**
     * Handles user search functionality
     */
    private static void searchUser() {
        System.out.println("\n======= SEARCH USER =======");
        
        if (!userService.hasUsers()) {
            System.out.println("No users in the system to search.");
            return;
        }
        
        System.out.print("Enter login to search: ");
        String login = scanner.nextLine().trim();
        
        User user = userService.getUser(login);
        if (user != null) {
            System.out.println("\n✓ User found:");
            System.out.println("Name: " + user.getName() + " " + user.getSurname());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Cell: " + user.getCell());
            System.out.println("Login: " + user.getLogin());
        } else {
            System.out.println("\n✗ User with login '" + login + "' not found.");
        }
    }
    
    /**
     * Cleans up resources and disconnects from SMOS server (postcondition)
     */
    private static void cleanup() {
        System.out.println("\n======= SYSTEM CLEANUP =======");
        
        // Postcondition: Interrupt connection to SMOS server
        System.out.println("Interrupting connection to SMOS server...");
        smosConnection.disconnect();
        
        // Close resources
        if (userRegistration != null) {
            userRegistration.close();
        }
        
        if (scanner != null) {
            scanner.close();
        }
        
        System.out.println("System cleanup completed.");
        System.out.println("==========================================");
        System.out.println("   USER REGISTRATION SYSTEM - TERMINATED");
        System.out.println("==========================================");
    }
    
    /**
     * Helper method to display system information
     */
    public static void displaySystemInfo() {
        System.out.println("\n======= SYSTEM INFORMATION =======");
        System.out.println("User Registration System v1.0");
        System.out.println("Designed for SMOS Integration");
        System.out.println("Use Case: Inserting a new user in the system");
        System.out.println("Actors: Administrator");
        System.out.println("===================================");
    }
}