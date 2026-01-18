'''
AuthService handles the core authentication logic, including validating input length
and checking credentials against a simulated "database" of users.
'''
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays; // Import Arrays for handling char[]
public class AuthService {
    // A set to store registered users for demonstration purposes.
    // In a real application, this would be a database connection or a more complex user store.
    private Set<User> registeredUsers;
    /**
     * Constructs an AuthService instance, initializing the set of registered users.
     */
    public AuthService() {
        this.registeredUsers = new HashSet<>();
    }
    /**
     * Adds a new user to the simulated archive of loggable users.
     * Stores the password as a char array for improved security.
     *
     * @param username The username of the user to add.
     * @param password The password of the user to add as a char array.
     */
    public void addUser(String username, char[] password) {
        // Create user with a copy of the password char array
        registeredUsers.add(new User(username, Arrays.copyOf(password, password.length)));
    }
    /**
     * Attempts to authenticate a user with the given username and password.
     * Implements the use case logic:
     * 1. Checks if username and password have length >= 5.
     * 2. Searches in the archive if Username and password are present.
     * Passwords are handled as char arrays for security.
     *
     * @param username The username provided by the user.
     * @param passwordChars The password provided by the user as a char array.
     * @return true if authentication is successful, false otherwise.
     * @throws IllegalArgumentException If username or password length is less than 5.
     */
    public boolean authenticate(String username, char[] passwordChars) throws IllegalArgumentException {
        // Event Sequence 1: Check that username and password have length >= 5.
        // If not, notify user error (via IllegalArgumentException here).
        if (username == null || username.length() < 5 || passwordChars == null || passwordChars.length < 5) {
            throw new IllegalArgumentException("Username and password must be at least 5 characters long.");
        }
        // Event Sequence 2: Search in the archive if Username and password entered by the user
        // are present among the loggable users.
        // Create a User object with the provided credentials for comparison.
        // The User constructor will make its own copy of the passwordChars array.
        User potentialUser = new User(username, passwordChars);
        // Check if the registeredUsers set contains a user with matching credentials.
        boolean isAuthenticated = registeredUsers.contains(potentialUser);
        // IMPORTANT SECURITY STEP: Clear the password from the temporary User object immediately after use
        potentialUser.clearPassword();
        return isAuthenticated;
    }
}