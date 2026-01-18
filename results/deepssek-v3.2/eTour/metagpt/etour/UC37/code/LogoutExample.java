/*
 * LogoutExample.java
 * A complete Java program demonstrating logout use case with session management.
 * Handles edge cases: no active session, invalid confirmation, and resource cleanup.
 */

import java.util.Scanner;

/**
 * Represents a registered user in the system.
 * In a real application, this would have more attributes and methods.
 */
class User {
    private String username;
    private String userId;
    
    public User(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getUserId() {
        return userId;
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', userId='" + userId + "'}";
    }
}

/**
 * Represents a user session with login time and user information.
 * Provides methods to manage session state.
 */
class Session {
    private User user;
    private long loginTime;
    private boolean active;
    
    public Session(User user) {
        this.user = user;
        this.loginTime = System.currentTimeMillis();
        this.active = true;
    }
    
    public User getUser() {
        return user;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public long getSessionDuration() {
        return System.currentTimeMillis() - loginTime;
    }
    
    /**
     * Logs out the user by deactivating the session.
     * Performs necessary cleanup operations.
     */
    public void logout() {
        if (active) {
            active = false;
            System.out.println("Session for user '" + user.getUsername() + "' has been terminated.");
            // In a real application, additional cleanup would happen here:
            // - Clearing user-specific caches
            // - Releasing resources
            // - Logging the logout event
        }
    }
}

/**
 * Manages user authentication and session state for the application.
 * Singleton pattern ensures only one session manager exists.
 */
class SessionManager {
    private static SessionManager instance;
    private Session currentSession;
    
    private SessionManager() {
        // Private constructor for singleton
    }
    
    /**
     * Gets the singleton instance of SessionManager.
     * @return The SessionManager instance
     */
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    /**
     * Creates a new session for the logged-in user.
     * Throws exception if a session already exists.
     * @param user The authenticated user
     * @throws IllegalStateException if a session is already active
     */
    public void createSession(User user) {
        if (currentSession != null && currentSession.isActive()) {
            throw new IllegalStateException("Cannot create new session: a session is already active");
        }
        currentSession = new Session(user);
        System.out.println("Login successful. Session created for user: " + user.getUsername());
    }
    
    /**
     * Gets the current active session.
     * @return The current session, or null if no active session exists
     */
    public Session getCurrentSession() {
        return currentSession;
    }
    
    /**
     * Checks if there is an active session.
     * @return true if an active session exists, false otherwise
     */
    public boolean hasActiveSession() {
        return currentSession != null && currentSession.isActive();
    }
    
    /**
     * Performs the logout process with confirmation.
     * Handles edge cases like no active session or user cancellation.
     */
    public void performLogout() {
        // Edge case: No active session
        if (!hasActiveSession()) {
            System.out.println("No active session found. Logout is not applicable.");
            return;
        }
        
        User currentUser = currentSession.getUser();
        System.out.println("\n=== Logout Process ===");
        System.out.println("Current user: " + currentUser.getUsername());
        
        // Step 2: System asks for confirmation
        System.out.println("Are you sure you want to logout? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        // Step 3: User confirms
        if (confirmation.equals("yes") || confirmation.equals("y")) {
            // Step 4: System disconnects the user
            System.out.println("Processing logout...");
            
            // Record session duration before logout
            long sessionDuration = currentSession.getSessionDuration();
            
            // Perform the logout operation
            currentSession.logout();
            currentSession = null;
            
            // Exit condition: System notifies successful logout
            System.out.println("\n✓ Logout successful!");
            System.out.println("Session duration: " + (sessionDuration / 1000) + " seconds");
            System.out.println("You have been securely disconnected from the system.");
        } else {
            // User cancelled the logout
            System.out.println("Logout cancelled. Session remains active.");
        }
    }
    
    /**
     * Simulates a login process for demonstration purposes.
     * In a real application, this would involve authentication.
     */
    public void simulateLogin() {
        if (hasActiveSession()) {
            System.out.println("A session is already active. Please logout first.");
            return;
        }
        
        // Create a sample user (in real app, this would come from authentication)
        User sampleUser = new User("john_doe", "U12345");
        createSession(sampleUser);
    }
}

/**
 * Main application class that demonstrates the logout use case.
 * This class handles the user interaction flow.
 */
public class LogoutExample {
    
    /**
     * Displays the main menu and handles user cho.
     */
    private static void displayMenu() {
        System.out.println("\n========== Session Management System ==========");
        System.out.println("1. Simulate Login (for demonstration)");
        System.out.println("2. Logout");
        System.out.println("3. Check Session Status");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    /**
     * Main method - entry point of the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SessionManager sessionManager = SessionManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Session Management System!");
        System.out.println("This program demonstrates the Logout use case.");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        // Simulate login (entry condition: user must be logged in to logout)
                        sessionManager.simulateLogin();
                        break;
                        
                    case 2:
                        // Step 1: User accesses logout functionality
                        System.out.println("\nInitiating logout process...");
                        sessionManager.performLogout();
                        break;
                        
                    case 3:
                        // Check current session status
                        if (sessionManager.hasActiveSession()) {
                            Session session = sessionManager.getCurrentSession();
                            User user = session.getUser();
                            System.out.println("Active session found:");
                            System.out.println("  User: " + user.getUsername());
                            System.out.println("  Session duration: " + 
                                (session.getSessionDuration() / 1000) + " seconds");
                        } else {
                            System.out.println("No active session. Please login first.");
                        }
                        break;
                        
                    case 4:
                        // Exit the program
                        System.out.println("\nThank you for using the Session Management System!");
                        System.out.println("Exiting...");
                        running = false;
                        
                        // Cleanup: logout if session is still active
                        if (sessionManager.hasActiveSession()) {
                            System.out.println("Auto-logout for active session...");
                            sessionManager.performLogout();
                        }
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        break;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        scanner.close();
        System.out.println("Program terminated.");
    }
}