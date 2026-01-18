/**
Manages the list of registration requests and provides business logic.
This class simulates data storage and handles operations like retrieving pending requests,
accepting a request, and simulating server interruptions.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RegistrationManager {
    private List<RegistrationRequest> registrationRequests;
    private Random random;
    /**
     * Constructor initializes the list of registration requests with sample data.
     * The sample data includes both pending and activated requests.
     */
    public RegistrationManager() {
        registrationRequests = new ArrayList<>();
        random = new Random();
        // Add sample registration requests for simulation
        registrationRequests.add(new RegistrationRequest("S001", "John Doe", "john@example.com", "PENDING"));
        registrationRequests.add(new RegistrationRequest("S002", "Jane Smith", "jane@example.com", "PENDING"));
        registrationRequests.add(new RegistrationRequest("S003", "Alice Johnson", "alice@example.com", "ACTIVATED"));
        registrationRequests.add(new RegistrationRequest("S004", "Bob Brown", "bob@example.com", "PENDING"));
        registrationRequests.add(new RegistrationRequest("S005", "Charlie Davis", "charlie@example.com", "PENDING"));
    }
    /**
     * Retrieves all registration requests with status "PENDING".
     * @return List of pending registration requests
     */
    public List<RegistrationRequest> getPendingRequests() {
        List<RegistrationRequest> pendingRequests = new ArrayList<>();
        for (RegistrationRequest request : registrationRequests) {
            if ("PENDING".equals(request.getStatus())) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }
    /**
     * Accepts a student's registration request by changing its status to "ACTIVATED".
     * Simulates a random server interruption with 10% probability.
     * @param studentId The ID of the student to accept
     * @return true if acceptance was successful, false if interrupted or student not found
     */
    public boolean acceptStudent(String studentId) {
        // Simulate SMOS server interruption: 10% chance
        if (random.nextDouble() < 0.1) {
            return false; // Server interrupted
        }
        // Find and update the registration request
        for (RegistrationRequest request : registrationRequests) {
            if (studentId.equals(request.getStudentId())) {
                request.setStatus("ACTIVATED");
                return true; // Successfully activated
            }
        }
        // Student ID not found (edge case)
        throw new IllegalArgumentException("Student ID not found: " + studentId);
    }
    /**
     * Adds a new registration request to the list (for testing purposes).
     * @param request The registration request to add
     */
    public void addRequest(RegistrationRequest request) {
        registrationRequests.add(request);
    }
}