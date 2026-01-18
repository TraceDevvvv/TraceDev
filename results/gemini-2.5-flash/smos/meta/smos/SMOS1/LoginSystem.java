import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a simple user with username and password.
 */
class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

/**
 * Manages the archive of registered users.
 */
class UserArchive {
    // In-memory storage for users. In a real system, this would be a database.
    private final Map<String, String> userMap = new HashMap<>();

    public UserArchive() {
        // Pre-populate with some users for demonstration.
        // In production, users would be registered via a registration process.
        addUser("alice123", "alicepass");
        addUser("bobsmith", "bobspassword");
        addUser("charlie", "charliepw");
    }

    /**
     * Adds a user to the archive.
     */
    public void addUser(String username, String password) {
        userMap.put(username, password);
    }

    /**
     * Checks if the username and password match a registered user.
     */
    public boolean authenticate(String username, String password) {
        if (!userMap.containsKey(username)) {
            return false;
        }
        return userMap.get(username).equals(password);
    }
}

/**
 * Handles the login logic and session state.
 */
class LoginManager {
    private final UserArchive userArchive;
    private boolean isLoggedIn = false;
    private User loggedInUser = null;

    public LoginManager(UserArchive userArchive) {
        this.userArchive = userArchive;
    }

    /**
     * Attempts to log in a user with the given credentials.
     * Returns true if successful, false otherwise.
     */
    public boolean login(String username, String password) {
        // Check precondition: username and password length >= 5
        if (username == null || password == null || username.length() < 5 || password.length() < 5) {
            System.out.println("Error: Username and password must be at least 5 characters long.");
            return false;
        }

        // Check if user is already logged in
        if (isLoggedIn) {
            System.out.println("Error: A user is already logged in.");
            return false;
        }

        // Authenticate against the archive
        if (userArchive.authenticate(username, password)) {
            isLoggedIn = true;
            loggedInUser = new User(username, password);
            System.out.println("Login successful. Welcome, " + username + "!");
            displayWorkspace();
            disconnectFromSMOS();
            return true;
        } else {
            System.out.println("Error: Invalid username or password.");
            return false;
        }
    }

    /**
     * Displays the registered user workspace.
     */
    private void displayWorkspace() {
        System.out.println("=== User Workspace ===");
        System.out.println("You are now in your workspace, " + loggedInUser.getUsername() + ".");
        // Additional workspace logic can be added here.
    }

    /**
     * Simulates disconnection from the SMOS server.
     */
    private void disconnectFromSMOS() {
        System.out.println("Disconnected from SMOS server.");
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        if (isLoggedIn) {
            System.out.println("User " + loggedInUser.getUsername() + " logged out.");
            isLoggedIn = false;
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}

/**
 * Main class to run the login system.
 */
public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserArchive userArchive = new UserArchive();
        LoginManager loginManager = new LoginManager(userArchive);

        System.out.println("=== Welcome to the Login System ===");

        // Only allow login if not already logged in
        while (!loginManager.isLoggedIn()) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            // Attempt login
            loginManager.login(username, password);

            // If login failed, prompt again or exit
            if (!loginManager.isLoggedIn()) {
                System.out.print("Try again? (y/n): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y")) {
                    System.out.println("Exiting login system.");
                    break;
                }
            }
        }

        scanner.close();
    }
}