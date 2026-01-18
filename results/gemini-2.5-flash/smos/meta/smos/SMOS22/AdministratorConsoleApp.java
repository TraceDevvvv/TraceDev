```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Objects; // Added for Teaching class equals/hashCode
import java.util.concurrent.TimeUnit; // For Thread.sleep clarity

/**
 * Custom exception for SMOS connection related errors.
 * This exception is thrown when there are issues connecting to or interacting with the SMOS server.
 */
class SMOSConnectionException extends Exception {
    public SMOSConnectionException(String message) {
        super(message);
    }

    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Represents a user in the system, holding their username and roles.
 * This class is used for authentication and authorization checks.
 */
class User {
    private final String username;
    private final List<String> roles;

    /**
     * Constructs a new User instance.
     *
     * @param username The unique username of the user.
     * @param roles A list of roles assigned to the user.
     */
    public User(String username, List<String> roles) {
        this.username = username;
        this.roles = new ArrayList<>(roles); // Defensive copy to prevent external modification
    }

    /**
     * Returns the username of this user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the user has a specific role.
     *
     * @param role The role name to check (e.g., "Administrator", "User").
     * @return true if the user has the specified role, false otherwise.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    /**
     * Provides a string representation of the User object.
     *
     * @return A formatted string containing the username and roles.
     */
    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", roles=" + roles +
               '}';
    }
}

/**
 * Manages the current user's session, including login status and roles.
 * This class simulates user authentication and session management.
 */
class UserSession {
    private User currentUser;
    private boolean loggedIn;

    /**
     * Constructs a new UserSession, initially with no user logged in.
     */
    public UserSession() {
        this.loggedIn = false;
        this.currentUser = null;
    }

    /**
     * Simulates a user login process.
     * In a real system, this would involve database lookup, password hashing, and session token generation.
     * For this simulation, it uses hardcoded credentials.
     *
     * @param username The username to attempt to log in with.
     * @param password The password for authentication.
     * @return true if login is successful and credentials match, false otherwise.
     */
    public boolean login(String username, String password) {
        // Simulate user database lookup
        if ("admin".equals(username) && "adminpass".equals(password)) {
            this.currentUser = new User(username, Arrays.asList("Administrator", "User"));
            this.loggedIn = true;
            System.out.println("User '" + username + "' logged in successfully as Administrator.");
            return true;
        } else if ("user".equals(username) && "userpass".equals(password)) {
            this.currentUser = new User(username, Arrays.asList("User"));
            this.loggedIn = true;
            System.out.println("User '" + username + "' logged in successfully as User.");
            return true;
        } else {
            System.out.println("Login failed for username: " + username + ". Invalid credentials.");
            this.loggedIn = false;
            this.currentUser = null;
            return false;
        }
    }

    /**
     * Logs out the current user, clearing the session.
     */
    public void logout() {
        if (loggedIn) {
            System.out.println("User '" + currentUser.getUsername() + "' logged out.");
        } else {
            System.out.println("No user was logged in to log out.");
        }
        this.loggedIn = false;
        this.currentUser = null;
    }

    /**
     * Checks if a user is currently logged in to this session.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Checks if the currently logged-in user possesses a specific role.
     *
     * @param roleName The name of the role to verify.
     * @return true if a user is logged in and has the specified role, false otherwise.
     */
    public boolean hasRole(String roleName) {
        return loggedIn && currentUser != null && currentUser.hasRole(roleName);
    }

    /**
     * Returns the current logged-in User object.
     *
     * @return The User object if logged in, otherwise null.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}

/**
 * Simulates a connection manager for the SMOS (School Management and Operations System) server.
 * It provides methods to establish and terminate connections, and to retrieve data like teachings.
 * Includes simulated network delays and potential connection failures.
 */
class SMOSConnectionManager {
    private boolean connected;
    private final Random random; // Used to simulate random connection failures

    /**
     * Constructs a new SMOSConnectionManager, initially not connected.
     */
    public SMOSConnectionManager() {
        this.connected = false;
        this.random = new Random();
    }

    /**
     * Establishes a simulated connection to the SMOS server.
     * This method includes a simulated network delay and a random chance of connection failure.
     *
     * @throws SMOSConnectionException if the connection attempt fails (e.g., network issues).
     */
    public void connect() throws SMOSConnectionException {
        System.out.println("Attempting to connect to SMOS server...");
        try {
            // Simulate network delay
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new SMOSConnectionException("Connection attempt interrupted.", e);
        }

        // Simulate connection failure with a 20% chance
        if (random.nextInt(100) < 20) {
            this.connected = false;
            throw new SMOSConnectionException("Failed to connect to SMOS server: Network unreachable or server busy.");
        }

        this.connected = true;
        System.out.println("Successfully connected to SMOS server.");
    }

    /**
     * Disconnects from the simulated SMOS server.
     * If already disconnected, it prints a message indicating so.
     */
    public void disconnect() {
        if (connected) {
            System.out.println("Disconnecting from SMOS server.");
            this.connected = false;
        } else {
            System.out.println("SMOS server was not connected, no disconnection needed.");
        }
    }

    /**
     * Retrieves a list of simulated teachings from the SMOS server.
     * This method requires an active connection to the SMOS server.
     *
     * @return A list of {@link Teaching} objects.
     * @throws SMOSConnectionException if not connected to the SMOS server.
     */
    public List<Teaching> getTeachings() throws SMOSConnectionException {
        if (!connected) {
            throw new SMOSConnectionException("Not connected to SMOS server. Please connect first to retrieve teachings.");
        }

        System.out.println("Retrieving teachings from SMOS archive...");
        try {
            // Simulate data retrieval delay
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new SMOSConnectionException("Teachings retrieval interrupted.", e);
        }

        // Return some dummy data for demonstration purposes
        List<Teaching> teachings = new ArrayList<>();
        teachings.add(new Teaching("T001", "Introduction to Java Programming", "Dr. Alice Smith"));
        teachings.add(new Teaching("T002", "Advanced Data Structures", "Prof. Bob Johnson"));
        teachings.add(new Teaching("T003", "Database Systems Design", "Ms. Carol Davis"));
        teachings.add(new Teaching("T004", "Software Engineering Principles", "Dr. David Brown"));
        teachings.add(new Teaching("T005", "Web Development Fundamentals", "Mr. Eve White"));
        return teachings;
    }

    /**
     * Checks if the manager is currently connected to the SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }
}

/**
 * Represents a single teaching item in the archive.
 * This class holds details about a teaching, such as its unique identifier,
 * name, and the teacher associated with it.
 */
class Teaching {
    private final String id;
    private final String name;
    private final String teacher;

    /**
     * Constructs a new Teaching instance.
     *
     * @param id The unique identifier for the teaching. Must not be null or empty.
     * @param name The name or title of the teaching. Must not be null or empty.
     * @param teacher The name of the teacher for this teaching. Must not be null or empty.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Teaching(String id, String name, String teacher) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
        if (teacher == null || teacher.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * Returns the unique identifier of the teaching.
     *
     * @return The teaching ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name or title of the teaching.
     *
     * @return The teaching name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the teacher for this teaching.
     *
     * @return The teacher's name.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Provides a string representation of the Teaching object,
     * useful for displaying teaching details.
     *
     * @return A formatted string containing the teaching's ID, name, and teacher.
     */
    @Override
    public String toString() {
        return "Teaching [ID: " + id + ", Name: '" + name + "', Teacher: '" + teacher + "']";
    }

    /**
     * Compares this Teaching object with another object for equality.
     * Two Teaching objects are considered equal if they have the same ID.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(id, teaching.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the teaching's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


/**
 * Main application class for the Administrator Console.
 * This class orchestrates the "View Teachings List" use case,
 * handling user interaction simulation, preconditions, and system responses.
 */
public class AdministratorConsoleApp {

    /**
     * Simulates the administrator clicking on the "Management Management" button.
     * This method acts as the entry point for the "View Teachings List" use case.
     *
     * @param session The current user session, used to check user roles and login status.
     * @param smosManager The SMOS connection manager, used to interact with the backend server.
     */
    public void clickManagementManagementButton(UserSession session, SMOSConnectionManager smosManager) {
        System.out.println("\n--- User Action: Clicks on 'Management Management' button ---");
        viewTeachingsList(session, smosManager);
    }

    /**
     * Implements the core logic for the "View Teachings List" use case.
     * This method performs the following steps:
     * 1. Checks if the user is logged in as an Administrator (Precondition).
     * 2. Attempts to connect to the SMOS server (Event Sequence 1: Look for archive).
     * 3. Retrieves the list of teachings from the SMOS server (Event Sequence 2: View teachings).
     * 4. Displays the retrieved list to the console (Postcondition).
     * 5. Ensures the connection to the SMOS server is interrupted (Postcondition).
     *
     * @param session The current user session, used for role and login checks.
     * @param smosManager The SMOS connection manager to interact with the server.
     */
    public void viewTeachingsList(UserSession session, SMOSConnectionManager smos