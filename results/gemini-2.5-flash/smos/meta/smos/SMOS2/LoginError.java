import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the LoginError use case.
 * Displays a login form, handles incorrect authentication data,
 * and allows the user to try logging in again.
 */
public class LoginError {

    /**
     * Simple in-memory user store for demonstration purposes.
     * In production, this would be replaced by a secure database.
     */
    private static class UserStore {
        private final Map<String, String> users = new HashMap<>();

        public UserStore() {
            // Add some registered users (username -> password)
            users.put("alice", "password123");
            users.put("bob", "securePass");
            users.put("charlie", "charliePwd");
        }

        /**
         * Checks if the provided username and password match a registered user.
         *
         * @param username the username entered
         * @param password the password entered
         * @return true if credentials are correct, false otherwise
         */
        public boolean authenticate(String username, String password) {
            if (username == null || password == null) {
                return false;
            }
            String storedPassword = users.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }
    }

    /**
     * Displays the login form and handles user input.
     * Allows repeated attempts if authentication fails.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserStore userStore = new UserStore();

        System.out.println("=== Welcome to the Login System ===");

        while (true) {
            // Display login form
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            // Authenticate user
            if (userStore.authenticate(username, password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                break; // Exit loop on successful login
            } else {
                // Handle incorrect authentication data
                System.out.println("Login error: Incorrect username or password.");
                System.out.println("Please try again.\n");
            }
        }

        scanner.close();
    }
}