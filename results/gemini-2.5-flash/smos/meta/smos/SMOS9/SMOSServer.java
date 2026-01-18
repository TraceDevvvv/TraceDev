import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Simulates a SMOS (System Management and Operations) server.
 * This server manages a list of users and simulates a connection status.
 * It provides functionalities to add, delete, and retrieve users,
 * as well as to establish and interrupt a connection.
 */
public class SMOSServer {
    // Using CopyOnWriteArrayList for thread-safety if multiple threads were to access it,
    // though for this single-threaded simulation, ArrayList would also suffice.
    // It's a good practice for shared mutable state.
    private final List<User> userArchive;
    private boolean isConnected;

    /**
     * Constructs a new SMOSServer instance.
     * Initializes the user archive and sets the connection status to disconnected.
     */
    public SMOSServer() {
        this.userArchive = new CopyOnWriteArrayList<>();
        this.isConnected = false;
    }

    /**
     * Establishes a connection to the SMOS server.
     *
     * @return true if the connection was successfully established, false if already connected.
     */
    public boolean connect() {
        if (!isConnected) {
            isConnected = true;
            System.out.println("SMOS Server: Connection established.");
            return true;
        }
        System.out.println("SMOS Server: Already connected.");
        return false;
    }

    /**
     * Interrupts the connection to the SMOS server.
     * This is a postcondition of the DeleteUser use case.
     *
     * @return true if the connection was successfully interrupted, false if already disconnected.
     */
    public boolean interruptConnection() {
        if (isConnected) {
            isConnected = false;
            System.out.println("SMOS Server: Connection interrupted.");
            return true;
        }
        System.out.println("SMOS Server: Already disconnected.");
        return false;
    }

    /**
     * Checks if the SMOS server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Adds a user to the server's archive.
     *
     * @param user The user to add.
     * @return true if the user was added, false if a user with the same ID already exists.
     * @throws IllegalStateException if the server is not connected.
     */
    public boolean addUser(User user) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot add user. Server is not connected.");
        }
        // Check for existing user with the same ID
        if (userArchive.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
            System.out.println("SMOS Server: User with ID '" + user.getUserId() + "' already exists.");
            return false;
        }
        userArchive.add(user);
        System.out.println("SMOS Server: User '" + user.getUserName() + "' (ID: " + user.getUserId() + ") added to archive.");
        return true;
    }

    /**
     * Deletes a user from the server's archive based on their user ID.
     * This is the core action for the DeleteUser use case.
     *
     * @param userId The ID of the user to delete.
     * @return true if the user was found and deleted, false otherwise.
     * @throws IllegalStateException if the server is not connected.
     */
    public boolean deleteUser(String userId) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot delete user. Server is not connected.");
        }
        // Find the user by ID and remove them
        boolean removed = userArchive.removeIf(user -> user.getUserId().equals(userId));
        if (removed) {
            System.out.println("SMOS Server: User with ID '" + userId + "' deleted from archive.");
        } else {
            System.out.println("SMOS Server: User with ID '" + userId + "' not found in archive.");
        }
        return removed;
    }

    /**
     * Retrieves a user from the archive by their user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return An Optional containing the User if found, or an empty Optional if not found.
     * @throws IllegalStateException if the server is not connected.
     */
    public Optional<User> getUserById(String userId) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot retrieve user. Server is not connected.");
        }
        return userArchive.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    /**
     * Returns an unmodifiable list of all users currently in the archive.
     *
     * @return A list of users.
     * @throws IllegalStateException if the server is not connected.
     */
    public List<User> getAllUsers() {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot retrieve all users. Server is not connected.");
        }
        return Collections.unmodifiableList(new ArrayList<>(userArchive));
    }

    /**
     * Displays the current list of users in the archive.
     * This simulates "Displays the list of updated users" from the use case.
     *
     * @throws IllegalStateException if the server is not connected.
     */
    public void displayAllUsers() {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot display users. Server is not connected.");
        }
        System.out.println("\n--- Current User Archive ---");
        if (userArchive.isEmpty()) {
            System.out.println("No users in the archive.");
        } else {
            userArchive.forEach(System.out::println);
        }
        System.out.println("----------------------------");
    }
}