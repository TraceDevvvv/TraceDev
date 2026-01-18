import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Manages a collection of User objects, providing methods to add, retrieve, and display user details.
 * This class simulates a user management system where administrators can view user information.
 * It acts as a data layer or service for user-related operations.
 */
public class UserManager {
    // Stores users, mapping their login (username) to their User object.
    // Using a HashMap allows for efficient retrieval of users by their unique login.
    private final Map<String, User> users;

    /**
     * Constructs a new UserManager.
     * Initializes the internal storage for users.
     */
    public UserManager() {
        this.users = new HashMap<>();
    }

    /**
     * Adds a new user to the manager.
     * If a user with the same login already exists, the existing user will be overwritten.
     * This method could be extended to handle duplicate login attempts more gracefully
     * (e.g., throwing an exception or returning a boolean indicating success/failure).
     *
     * @param user The User object to add. Must not be null.
     * @throws IllegalArgumentException if the provided user is null or has a null login.
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (user.getLogin() == null || user.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("User login cannot be null or empty.");
        }
        users.put(user.getLogin(), user);
        System.out.println("User '" + user.getLogin() + "' added successfully.");
    }

    /**
     * Retrieves a user by their login username.
     *
     * @param login The login username of the user to retrieve. Must not be null or empty.
     * @return The User object if found, or null if no user with the given login exists.
     * @throws IllegalArgumentException if the provided login is null or empty.
     */
    public User getUserByLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty.");
        }
        return users.get(login);
    }

    /**
     * Simulates the "ViewingLanciutenti" use case by providing a list of all registered user logins.
     * In a real system, this might return a more complex list of user summaries or IDs.
     *
     * @return An unmodifiable set of all user logins currently managed.
     */
    public Set<String> getAllUserLogins() {
        // Return an unmodifiable set to prevent external modification of the internal state.
        return Collections.unmodifiableSet(users.keySet());
    }

    /**
     * Displays the detailed information of a specific user.
     * This method fulfills the core requirement of the "ViewUserDetails" use case.
     * It retrieves the user and then prints their details using the User object's toString method.
     *
     * @param login The login username of the user whose details are to be displayed.
     * @return true if the user was found and details displayed, false otherwise.
     */
    public boolean displayUserDetails(String login) {
        User user = getUserByLogin(login);
        if (user != null) {
            System.out.println("\n--- Detailed User Information for Login: " + login + " ---");
            System.out.println(user.toString()); // User.toString() provides formatted details
            System.out.println("----------------------------------------------------");
            return true;
        } else {
            System.out.println("\nError: User with login '" + login + "' not found in the system.");
            return false;
        }
    }

    /**
     * Simulates the interruption of connection to the SMOS server.
     * This is a placeholder for actual server interaction logic as described in the postconditions.
     * In a real application, this would involve closing network connections,
     * releasing resources, or updating system status related to the SMOS server.
     */
    public void interruptSMOSConnection() {
        System.out.println("System Notification: Connection to the SMOS server interrupted.");
    }
}