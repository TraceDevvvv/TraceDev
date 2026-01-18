/**
 * Data Access Object for registration requests.
 * Simulates database operations for managing registration requests.
 * In a real application, this would connect to an actual database.
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class RequestDAO {
    // In-memory storage simulating a database
    private Map<String, RegistrationRequest> requests;
    private SimpleDateFormat dateFormat;
    private Random random;
    public RequestDAO() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        requests = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order
        random = new Random();
        initializeSampleData();
    }
    /**
     * Initialize with some sample pending requests for demonstration
     */
    private void initializeSampleData() {
        try {
            // Create 5 sample pending requests
            String[] ids = {"REQ001", "REQ002", "REQ003", "REQ004", "REQ005"};
            String[] names = {"John Smith", "Jane Doe", "Bob Johnson", "Alice Brown", "Charlie Wilson"};
            String[] studentIds = {"S1001", "S1002", "S1003", "S1004", "S1005"};
            String[] emails = {"john@university.edu", "jane@university.edu", 
                               "bob@university.edu", "alice@university.edu", 
                               "charlie@university.edu"};
            for (int i = 0; i < ids.length; i++) {
                // Create dates for the last 5 days
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -(i + 1));
                cal.set(Calendar.HOUR_OF_DAY, 14);
                cal.set(Calendar.MINUTE, 30);
                cal.set(Calendar.SECOND, 0);
                Date requestDate = cal.getTime();
                RegistrationRequest request = new RegistrationRequest(
                    ids[i], names[i], studentIds[i], emails[i], requestDate
                );
                requests.put(ids[i], request);
            }
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
        }
    }
    /**
     * Get all pending registration requests
     * 
     * @return List of pending registration requests
     */
    public List<RegistrationRequest> getPendingRequests() {
        List<RegistrationRequest> pending = new ArrayList<>();
        for (RegistrationRequest request : requests.values()) {
            if ("PENDING".equals(request.getStatus())) {
                pending.add(request);
            }
        }
        // Sort by request date (newest first)
        pending.sort((r1, r2) -> r2.getRequestDate().compareTo(r1.getRequestDate()));
        return pending;
    }
    /**
     * Simulate the system behavior for rejecting a request
     * 1. Validates the request exists and is pending
     * 2. Updates the request status
     * 3. Simulates interaction with external SMOS server
     * 
     * @param requestId The ID of the request to reject
     * @throws SMOSException if SMOS server connection is interrupted (simulated)
     * @throws IllegalArgumentException if request not found or already processed
     */
    public void rejectRequest(String requestId) throws SMOSException {
        RegistrationRequest request = requests.get(requestId);
        // Validate request exists
        if (request == null) {
            throw new IllegalArgumentException("Request with ID '" + requestId + "' not found in the system");
        }
        // Check if request is already processed
        if (!"PENDING".equals(request.getStatus())) {
            throw new IllegalStateException("Request '" + requestId + "' is already '" + 
                                           request.getStatus() + "'. Cannot reject again.");
        }
        System.out.println("Processing rejection for request: " + requestId);
        // Simulate database update delay
        try {
            Thread.sleep(100 + random.nextInt(200)); // 100-300ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Events sequence step 1: Eliminate the registration request from the system
        request.setStatus("REJECTED");
        // Simulate the postcondition: SMOS server interruption (happens randomly for demo)
        // In 30% of cases, simulate an SMOS server interruption
        if (random.nextDouble() < 0.3) {
            // Simulate network delay before interruption
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throw new SMOSException("SMOS server connection interrupted at " + 
                                   new Date() + ". " +
                                   "The rejection was recorded locally but may not be synced.");
        }
        // Simulate successful SMOS server communication
        try {
            Thread.sleep(50 + random.nextInt(100)); // 50-150ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Get a specific request by ID
     * 
     * @param requestId The request ID
     * @return The registration request or null if not found
     */
    public RegistrationRequest getRequest(String requestId) {
        return requests.get(requestId);
    }
    /**
     * Get all requests (for debugging purposes)
     * 
     * @return All registration requests
     */
    public List<RegistrationRequest> getAllRequests() {
        return new ArrayList<>(requests.values());
    }
    /**
     * Get total count of requests by status
     * 
     * @return Map with status as key and count as value
     */
    public Map<String, Integer> getRequestStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("PENDING", 0);
        stats.put("APPROVED", 0);
        stats.put("REJECTED", 0);
        for (RegistrationRequest request : requests.values()) {
            Integer count = stats.get(request.getStatus());
            if (count != null) {
                stats.put(request.getStatus(), count + 1);
            }
        }
        return stats;
    }
}