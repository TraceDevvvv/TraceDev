import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a registration request from a student.
 * This class holds the data for a single registration request.
 */
class RegistrationRequest {
    private final String studentId;
    private final String studentName;
    private final String email;
    private final LocalDateTime requestDate;
    private boolean activated;

    /**
     * Constructor to create a new registration request.
     *
     * @param studentId   Unique identifier for the student.
     * @param studentName Full name of the student.
     * @param email       Contact email of the student.
     */
    public RegistrationRequest(String studentId, String studentName, String email) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.requestDate = LocalDateTime.now(); // Set request time to now
        this.activated = false; // By default, request is not activated
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public boolean isActivated() {
        return activated;
    }

    /**
     * Activates the registration request.
     * Once activated, the request should no longer appear in the pending list.
     */
    public void activate() {
        this.activated = true;
    }

    @Override
    public String toString() {
        return String.format("RegistrationRequest{studentId='%s', studentName='%s', email='%s', requestDate=%s, activated=%s}",
                studentId, studentName, email, requestDate, activated);
    }
}

/**
 * Represents an Administrator user in the system.
 * This class handles the action of viewing pending registration requests.
 */
class Administrator {
    private final String username;
    private boolean loggedIn;

    /**
     * Constructor for Administrator.
     *
     * @param username Unique username for the administrator.
     */
    public Administrator(String username) {
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.loggedIn = false; // Initially not logged in
    }

    /**
     * Simulates the login process for the administrator.
     * Precondition for viewing requests is that the admin is logged in.
     */
    public void login() {
        this.loggedIn = true;
        System.out.println("Administrator " + username + " logged in successfully.");
    }

    /**
     * Simulates the logout process.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println("Administrator " + username + " logged out.");
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Displays the list of registration requests that are not yet activated.
     * This method simulates the "View list request list" button action.
     * Precondition: Administrator must be logged in.
     *
     * @param requests List of all registration requests (both pending and activated).
     */
    public void viewPendingRequests(List<RegistrationRequest> requests) {
        // Check preconditions
        if (!isLoggedIn()) {
            System.err.println("Error: Administrator must be logged in to view requests.");
            return;
        }

        if (requests == null) {
            System.err.println("Error: Request list is null.");
            return;
        }

        // Filter requests that are not activated
        List<RegistrationRequest> pendingRequests = new ArrayList<>();
        for (RegistrationRequest request : requests) {
            if (!request.isActivated()) {
                pendingRequests.add(request);
            }
        }

        // Display the list
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending registration requests.");
        } else {
            System.out.println("=== Pending Registration Requests ===");
            for (int i = 0; i < pendingRequests.size(); i++) {
                RegistrationRequest req = pendingRequests.get(i);
                System.out.printf("%d. ID: %s, Name: %s, Email: %s, Request Date: %s%n",
                        i + 1,
                        req.getStudentId(),
                        req.getStudentName(),
                        req.getEmail(),
                        req.getRequestDate());
            }
            System.out.println("Total pending requests: " + pendingRequests.size());
        }
    }
}

/**
 * Simulates a connection to the SMOS server.
 * This class handles server connectivity and simulates interruption.
 */
class SmosServerConnection {
    private boolean connected;

    public SmosServerConnection() {
        this.connected = true; // Assume initially connected
    }

    /**
     * Checks if the connection to SMOS server is active.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Simulates interrupting the connection to the SMOS server.
     * This mimics the postcondition where connection is interrupted.
     */
    public void interruptConnection() {
        this.connected = false;
        System.out.println("SMOS server connection interrupted.");
    }

    /**
     * Re-establishes the connection.
     */
    public void reconnect() {
        this.connected = true;
        System.out.println("SMOS server reconnected.");
    }
}

/**
 * Main class that orchestrates the ViewRequestsSubscription use case.
 * This program simulates the entire flow from login to viewing requests.
 */
public class ViewRequestsSubscription {
    public static void main(String[] args) {
        System.out.println("=== Starting ViewRequestsSubscription Use Case Simulation ===\n");

        // 1. Create an administrator and log them in (precondition)
        Administrator admin = new Administrator("admin_john");
        admin.login();

        // 2. Create a list of sample registration requests (some pending, some activated)
        List<RegistrationRequest> allRequests = new ArrayList<>();
        allRequests.add(new RegistrationRequest("S1001", "Alice Johnson", "alice@example.com"));
        allRequests.add(new RegistrationRequest("S1002", "Bob Smith", "bob@example.com"));
        allRequests.add(new RegistrationRequest("S1003", "Charlie Brown", "charlie@example.com"));
        allRequests.add(new RegistrationRequest("S1004", "Diana Prince", "diana@example.com"));

        // Activate one request to simulate mixed list
        allRequests.get(1).activate(); // Bob's request is activated

        // 3. Simulate SMOS server connection
        SmosServerConnection serverConnection = new SmosServerConnection();
        if (!serverConnection.isConnected()) {
            System.err.println("Error: Cannot proceed without SMOS server connection.");
            return;
        }

        // 4. Administrator clicks "View list request list" button (events sequence)
        System.out.println("\nAdministrator clicks 'View list request list' button...");
        admin.viewPendingRequests(allRequests);

        // 5. Simulate postcondition: user interrupts the connection to the SMOS server
        System.out.println("\n--- Simulating postcondition ---");
        serverConnection.interruptConnection();

        // 6. Attempt to view requests again after interruption (edge case handling)
        System.out.println("\nAttempting to view requests after server interruption...");
        if (!serverConnection.isConnected()) {
            System.err.println("Error: SMOS server connection is interrupted. Cannot fetch requests.");
        } else {
            admin.viewPendingRequests(allRequests);
        }

        // 7. Cleanup: logout
        admin.logout();

        System.out.println("\n=== Use Case Simulation Completed ===");
    }
}