'''
Simulates a service layer responsible for fetching teaching details.
This class acts as a mock backend system, providing sample data and
simulating potential server interruptions.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * Simulates a service layer responsible for fetching teaching details.
 * This class acts as a mock backend system, providing sample data and
 * simulating potential server interruptions.
 */
public class TeachingService {
    private final Map<String, Teaching> mockDatabase;
    private final Random random;
    /**
     * Constructs a new TeachingService and populates a mock database with sample teachings.
     */
    public TeachingService() {
        mockDatabase = new HashMap<>();
        random = new Random();
        populateMockData();
    }
    /**
     * Populates the mock database with predefined teaching objects.
     */
    private void populateMockData() {
        mockDatabase.put("T001", new Teaching("T001", "Introduction to Java", "Fundamentals of Java programming for beginners.", "Dr. Alice Smith", 40, "2024-09-01"));
        mockDatabase.put("T002", new Teaching("T002", "Advanced Data Structures", "In-depth study of complex data structures and algorithms.", "Prof. Bob Johnson", 60, "2024-10-15"));
        mockDatabase.put("T003", new Teaching("T003", "Web Development with Spring", "Building web applications using the Spring Framework.", "Ms. Carol White", 50, "2024-11-01"));
    }
    /**
     * Retrieves the details of a teaching by its ID.
     * This method simulates a connection to an external SMOS server and can
     * randomly simulate an interruption (failure to fetch data).
     *
     * @param teachingId The unique identifier of the teaching to retrieve.
     * @return A Teaching object if found and no server interruption, otherwise null.
     * @throws ConnectionInterruptedException If the simulated connection to the SMOS server is interrupted.
     */
    public Teaching getTeachingDetails(String teachingId) throws ConnectionInterruptedException {
        // Simulate network latency or processing time
        try {
            Thread.sleep(300); // Simulate a short delay
        } catch (InterruptedException e) {
            // Restore the interrupted status as good practice when catching InterruptedException
            Thread.currentThread().interrupt();
            // Wrap in a custom exception if appropriate for a service layer or rethrow.
            // Here, we rethrow as our custom ConnectionInterruptedException, which is caught by the SwingWorker in MainApplication.
            throw new ConnectionInterruptedException("Service operation interrupted during simulated delay.", e);
        }
        // Simulate an arbitrary server interruption for demonstration purposes
        // Approximately 20% chance of interruption
        if (random.nextInt(10) < 2) {
            System.err.println("Simulating SMOS server interruption for ID: " + teachingId);
            throw new ConnectionInterruptedException("Connection to SMOS server interrupted. Please try again.");
        }
        // Retrieve from mock database
        Teaching teaching = mockDatabase.get(teachingId);
        if (teaching == null) {
            // Log for debugging purposes, but the MainApplication will also handle this
            System.out.println("Teaching with ID " + teachingId + " not found in mock database.");
        }
        return teaching;
    }
    /**
     * Custom exception to simulate an interrupted connection to the SMOS server.
     */
    public static class ConnectionInterruptedException extends Exception {
        /**
         * Constructs a new ConnectionInterruptedException with the specified detail message.
         * @param message The detail message.
         */
        public ConnectionInterruptedException(String message) {
            super(message);
        }
        /**
         * Constructs a new ConnectionInterruptedException with the specified detail message and cause.
         * @param message The detail message.
         * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method).
         */
        public ConnectionInterruptedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}