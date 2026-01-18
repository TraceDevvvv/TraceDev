/*
 * DOCSTRING: Simulates communication with an external SMOS server.
 * This class simulates network latency and potential connection errors.
 * It's not a real server connection but helps demonstrate error handling.
 */
package service;
import model.Absence;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class SMOSServerConnector {
    private static final double FAILURE_RATE = 0.15; // 15% chance of connection failure
    private static final int SIMULATED_DELAY_MS = 800; // Simulate network latency
    /**
     * Custom exception for SMOS server connection issues.
     */
    public static class SMOSServerConnectionException extends Exception {
        public SMOSServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
        public SMOSServerConnectionException(String message) {
            super(message);
        }
    }
    /**
     * Simulates sending absence data to the SMOS server.
     * Introduces simulated delay and random failure.
     *
     * @param absencesToSave A list of Absence objects to be saved (or updated/deleted) on the server.
     * @throws SMOSServerConnectionException If a simulated connection error occurs.
     */
    public void simulateSaveAbsences(List<Absence> absencesToSave) throws SMOSServerConnectionException {
        System.out.println("SMOSServerConnector: Simulating sending " + absencesToSave.size() + " absence records to SMOS server...");
        try {
            // Simulate network latency
            TimeUnit.MILLISECONDS.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SMOSServerConnectionException("SMOS server communication interrupted.", e);
        }
        // Simulate random connection failure
        if (Math.random() < FAILURE_RATE) {
            throw new SMOSServerConnectionException("Simulated SMOS server connection interrupted or timed out.");
        }
        // If successful, log the operation
        for (Absence absence : absencesToSave) {
            System.out.println("SMOSServerConnector: Successfully processed absence record for student " +
                               absence.getStudentId() + " on " + absence.getDate() + " (Status: " + absence.getStatus() + ")");
        }
        System.out.println("SMOSServerConnector: Absence data successfully 'saved' to SMOS server.");
    }
}