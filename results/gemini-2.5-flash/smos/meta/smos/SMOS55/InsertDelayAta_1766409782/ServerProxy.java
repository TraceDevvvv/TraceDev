import java.util.List;
import java.util.Random;

/**
 * Simulates a proxy for sending delay data to a remote server (e.g., SMOS server).
 * This class handles the logic for attempting to send data and simulating potential
 * connection issues or server responses.
 */
public class ServerProxy {
    // Simulate connection status. True means connected, false means disconnected.
    private boolean isConnected;
    // Random generator to simulate server response variability
    private Random random;

    /**
     * Constructs a new ServerProxy instance.
     * Initially, the proxy is considered connected.
     */
    public ServerProxy() {
        this.isConnected = true; // Assume connected initially
        this.random = new Random();
    }

    /**
     * Simulates sending delay data for a list of students to the server.
     * This method can simulate success, failure due to connection issues,
     * or a server-side processing error.
     *
     * @param delayedStudents A list of Student objects for whom delay data needs to be sent.
     *                        Each student object should have its `isDelayed` and `delayMinutes`
     *                        fields correctly set.
     * @return true if the data was successfully "sent" and processed by the server, false otherwise.
     */
    public boolean sendDelayData(List<Student> delayedStudents) {
        if (!isConnected) {
            System.err.println("ServerProxy: Cannot send data. Connection to SMOS server is interrupted.");
            return false;
        }

        if (delayedStudents == null || delayedStudents.isEmpty()) {
            System.out.println("ServerProxy: No delayed students to send data for.");
            return true; // Consider it a success if there's nothing to send
        }

        // Simulate network latency and server processing time
        try {
            Thread.sleep(random.nextInt(500) + 200); // Simulate 200-700ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("ServerProxy: Data sending interrupted.");
            return false;
        }

        // Simulate various server responses:
        // 1. Connection failure (e.g., 10% chance)
        if (random.nextInt(100) < 10) {
            System.err.println("ServerProxy: Simulated connection timeout or network error during data transmission.");
            // Optionally, disconnect the server to reflect a persistent issue
            // this.disconnect();
            return false;
        }

        // 2. Server-side error (e.g., 5% chance)
        if (random.nextInt(100) < 5) {
            System.err.println("ServerProxy: Simulated server-side error during data processing.");
            return false;
        }

        // If we reach here, assume success
        System.out.println("ServerProxy: Successfully sent delay data for " + delayedStudents.size() + " students.");
        for (Student student : delayedStudents) {
            System.out.println("  - Student ID: " + student.getStudentId() + ", Delayed: " + student.isDelayed() + ", Minutes: " + student.getDelayMinutes());
        }
        return true;
    }

    /**
     * Simulates interrupting the connection to the SMOS server.
     * After this, `sendDelayData` will fail until reconnected.
     */
    public void disconnect() {
        this.isConnected = false;
        System.out.println("ServerProxy: Connection to SMOS server has been interrupted.");
    }

    /**
     * Simulates re-establishing the connection to the SMOS server.
     */
    public void reconnect() {
        this.isConnected = true;
        System.out.println("ServerProxy: Connection to SMOS server has been re-established.");
    }

    /**
     * Checks the current connection status to the server.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
}