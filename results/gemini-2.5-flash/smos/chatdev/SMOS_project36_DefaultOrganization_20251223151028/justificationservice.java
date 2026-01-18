/**
 * A mock service class to handle the business logic related to justifications.
 * In a real application, this would interact with a database or an external system (like SMOS).
 * For this simulation, it provides mock implementations for saving justifications
 * and checking server connection status.
 */
import java.time.LocalDate;
import java.util.Random;
public class JustificationService {
    // A random number generator to simulate server connection issues
    private static final Random random = new Random();
    // Static field to simulate server connection status. Can be toggled for testing.
    private static boolean smosServerConnected = true;
    /**
     * Simulates saving a justification.
     * This method mimics a persistence operation and includes a simulated check
     * for SMOS server connection and potential random failures.
     * @param justification The Justification object to be saved.
     * @return True if the justification was "saved" successfully, false otherwise.
     */
    public boolean saveJustification(Justification justification) {
        // Precondition: Check SMOS server connection
        if (!isSmosServerConnected()) {
            System.err.println("JustificationService: SMOS server connection interrupted.");
            return false;
        }
        System.out.println("JustificationService: Attempting to save justification for Absence ID: " +
                           justification.getAbsenceId() + " on date: " + justification.getJustificationDate());
        // Simulate some processing delay
        try {
            Thread.sleep(500); // Simulate network latency/processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("JustificationService: Interrupted during save operation.");
            return false;
        }
        // Simulate a database save operation.
        // For this mock, we just print to console. In a real app, this would involve SQL or ORM.
        System.out.println("JustificationService: Justification data saved: " + justification);
        // Simulate a random failure for demonstration purposes
        if (random.nextInt(10) < 1) { // 10% chance of a simulated failure
            System.err.println("JustificationService: Simulated random database error during save!");
            return false;
        }
        return true;
    }
    /**
     * Checks the simulated SMOS server connection status.
     * @return True if the SMOS server is considered connected, false otherwise.
     */
    public static boolean isSmosServerConnected() {
        return smosServerConnected;
    }
    /**
     * Toggles the simulated SMOS server connection status for testing purposes.
     * @param connected The new connection status.
     */
    public static void setSmosServerConnected(boolean connected) {
        smosServerConnected = connected;
        System.out.println("JustificationService: SMOS server connection status set to: " + (connected ? "Connected" : "Disconnected"));
    }
}