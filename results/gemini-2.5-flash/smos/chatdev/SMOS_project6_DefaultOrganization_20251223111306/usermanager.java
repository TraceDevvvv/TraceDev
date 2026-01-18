'''
Manages the collection of users in the system.
In this simplified example, it stores users in memory.
It provides functionality to add new users and check for login uniqueness.
'''
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Removed: import PasswordHasher; // No explicit import needed for classes in the same default package
public class UserManager {
    // A simple in-memory list to store users.
    // In a real application, this would interact with a database or a persistent store.
    private final List<User> users;
    /**
     * Constructs a new UserManager.
     * Initializes the internal list of users.
     */
    public UserManager() {
        this.users = new ArrayList<>();
        // Add a dummy admin user for testing purposes (e.g., to simulate existing users)
        // Pass a pre-hashed password for the dummy user.
        users.add(new User("Admin", "User", "admin@example.com", "1234567890", "admin", PasswordHasher.hashPassword("adminpass")));
    }
    /**
     * Adds a new user to the system.
     *
     * @param user The User object to be added.
     * @return true if the user was added successfully, false otherwise (e.g., login already exists).
     */
    public boolean addUser(User user) {
        // In a real system, more complex checks and error handling would be here,
        // potentially saving to a database.
        if (isLoginTaken(user.getLogin())) {
            return false; // Login already exists
        }
        users.add(user);
        return true;
    }
    /**
     * Checks if a given login ID is already taken by another user in the system.
     *
     * @param login The login ID to check for uniqueness.
     * @return true if the login is already taken, false otherwise.
     */
    public boolean isLoginTaken(String login) {
        return users.stream().anyMatch(u -> u.getLogin().equalsIgnoreCase(login));
    }
    /**
     * Returns an unmodifiable list of all users currently in the system.
     *
     * @return A List of User objects.
     */
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }
}