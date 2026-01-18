import java.util.Scanner;

/**
 * A fully runnable Java program that demonstrates the LoginError use case.
 * The program displays a login form, handles incorrect authentication data,
 * and allows the user to try logging in again.
 */
public class LoginErrorDemo {

    // Hardcoded credentials for demonstration purposes
    private static final String VALID_USERNAME = "user123";
    private static final String VALID_PASSWORD = "pass123";

    /**
     * Entry point of the program.
     * Displays the login form and handles authentication attempts.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Login System ===");

        boolean isAuthenticated = false;

        // Loop until the user successfully logs in
        while (!isAuthenticated) {
            // Display the login form
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Check credentials
            if (authenticate(username, password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                isAuthenticated = true;
            } else {
                // Handle incorrect authentication data (LoginError)
                System.out.println("LoginError: Incorrect username or password.");
                System.out.println("Please try again.\n");
            }
        }

        scanner.close();
    }

    /**
     * Authenticates the user by comparing input credentials to valid ones.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if credentials are correct, false otherwise.
     */
    private static boolean authenticate(String username, String password) {
        // Check for null or empty input to handle edge cases
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
}