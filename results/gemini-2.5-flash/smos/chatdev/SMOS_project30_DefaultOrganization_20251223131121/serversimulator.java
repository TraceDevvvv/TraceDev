'''
This class simulates a backend server for handling delay data and sending
notifications. It includes methods for sending data, sending notifications,
and simulating connection interruptions.
'''
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
public class ServerSimulator {
    // A flag to simulate whether the server connection is active
    private static volatile boolean connectionActive = true;
    private static final Random random = new Random();
    private static final int SIMULATED_LATENCY_MS = 2000; // 2 seconds
    /**
     * Simulates sending student delay data to a remote server.
     * Includes a delay to mimic network latency.
     * Also checks for connection interruption.
     *
     * @param date The date for which delays are being registered.
     * @param studentDelays A list of student identifiers (names/IDs) who are delayed.
     * @return true if data was successfully "sent", false otherwise (e.g., simulated server error).
     * @throws ServerConnectionException if the server connection is interrupted.
     */
    public static boolean sendDelayData(LocalDate date, List<String> studentDelays) {
        if (!connectionActive) {
            System.err.println("ServerSimulator: Connection is interrupted. Cannot send delay data.");
            // Simulate throwing a specific exception for an interrupted connection
            throw new ServerConnectionException("SMOS Server connection interrupted.");
        }
        try {
            // Simulate network latency
            Thread.sleep(SIMULATED_LATENCY_MS);
            System.out.println("ServerSimulator: Received delay data for " + date + ": " + studentDelays.size() + " students.");
            // Simulate potential server errors (e.g., 10% chance of failure)
            if (random.nextInt(10) == 0) {
                System.err.println("ServerSimulator: Simulated server error during data processing.");
                return false;
            }
            // In a real application, this would involve database operations or API calls.
            System.out.println("ServerSimulator: Delay data processed successfully.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("ServerSimulator: Sending data interrupted: " + e.getMessage());
            return false;
        }
    }
    /**
     * Simulates sending notifications to parents for delayed students.
     * Includes a delay to mimic network latency.
     * Also checks for connection interruption.
     *
     * @param studentDelays A list of student identifiers (names/IDs) for whom notifications should be sent.
     * @return true if notifications were successfully "sent", false otherwise (e.g., simulated notification service error).
     * @throws ServerConnectionException if the server connection is interrupted.
     */
    public static boolean sendNotificationsToParents(List<String> studentDelays) {
        if (!connectionActive) {
            System.err.println("ServerSimulator: Connection is interrupted. Cannot send notifications.");
            // Simulate throwing a specific exception for an interrupted connection
            throw new ServerConnectionException("SMOS Server connection interrupted.");
        }
        try {
            // Simulate network latency
            Thread.sleep(SIMULATED_LATENCY_MS);
            System.out.println("ServerSimulator: Sending notifications for " + studentDelays.size() + " parents.");
            // Simulate potential notification service errors (e.g., 5% chance of failure)
            if (random.nextInt(20) == 0) {
                System.err.println("ServerSimulator: Simulated notification service error.");
                return false;
            }
            // In a real application, this would involve an external messaging API.
            System.out.println("ServerSimulator: Notifications sent successfully.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("ServerSimulator: Sending notifications interrupted: " + e.getMessage());
            return false;
        }
    }
    /**
     * Simulates an interruption of the connection to the SMOS server.
     * Changes the internal state `connectionActive` to false.
     * Any subsequent calls to `sendDelayData` or `sendNotificationsToParents` will fail.
     */
    public static void interruptConnection() {
        System.out.println("ServerSimulator: Simulating connection interruption to SMOS server.");
        connectionActive = false;
        try {
            // Simulate some time for the connection to actually "interrupt"
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("ServerSimulator: Interruption process itself was interrupted: " + e.getMessage());
        }
        System.out.println("ServerSimulator: SMOS server connection has been interrupted.");
    }
    /**
     * Resets the server connection state.
     * Useful for testing or if the application needs to re-establish connection logic.
     */
    public static void resetConnection() {
        System.out.println("ServerSimulator: Resetting server connection state to active.");
        connectionActive = true;
    }
}