import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Custom exception for SMOS Server related errors.
 * This helps in distinguishing server-specific issues from other application errors.
 */
class SMOSServerException extends Exception {
    public SMOSServerException(String message) {
        super(message);
    }
}

/**
 * Simulates the SMOS server responsible for managing delay settings.
 * This class handles data storage for delays and simulates server connection status.
 */
public class SMOSServer {
    // A map to store delays, where the key is the date (e.g., "YYYY-MM-DD") and the value is the delay in minutes.
    private final Map<String, Integer> delaySettings;
    // Simulates the connection status of the server.
    private boolean connected;

    /**
     * Constructor for SMOSServer.
     * Initializes the delay settings and sets the server to a disconnected state by default.
     */
    public SMOSServer() {
        this.delaySettings = new HashMap<>();
        this.connected = false; // Server starts disconnected
        initializeDelays(); // Populate some initial data
    }

    /**
     * Initializes some sample delay data for demonstration purposes.
     */
    private void initializeDelays() {
        delaySettings.put("2023-10-26", 30); // 30 minutes delay for Oct 26, 2023
        delaySettings.put("2023-10-27", 60); // 60 minutes delay for Oct 27, 2023
        delaySettings.put("2023-10-28", 0);  // No delay for Oct 28, 2023
    }

    /**
     * Simulates connecting to the SMOS server.
     *
     * @throws SMOSServerException if the server is already connected.
     */
    public void connect() throws SMOSServerException {
        if (this.connected) {
            throw new SMOSServerException("SMOS Server is already connected.");
        }
        System.out.println("SMOS Server: Attempting to connect...");
        // Simulate a delay for connection
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SMOSServerException("SMOS Server connection interrupted.");
        }
        this.connected = true;
        System.out.println("SMOS Server: Connected successfully.");
    }

    /**
     * Simulates disconnecting from the SMOS server.
     *
     * @throws SMOSServerException if the server is already disconnected.
     */
    public void disconnect() throws SMOSServerException {
        if (!this.connected) {
            throw new SMOSServerException("SMOS Server is already disconnected.");
        }
        System.out.println("SMOS Server: Disconnecting...");
        // Simulate a delay for disconnection
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SMOSServerException("SMOS Server disconnection interrupted.");
        }
        this.connected = false;
        System.out.println("SMOS Server: Disconnected.");
    }

    /**
     * Checks if the SMOS server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Retrieves the delay setting for a specific date.
     *
     * @param date The date in "YYYY-MM-DD" format for which to retrieve the delay.
     * @return The delay in minutes for the given date, or null if no delay is set for that date.
     * @throws SMOSServerException if the server is not connected.
     */
    public Integer getDelay(String date) throws SMOSServerException {
        if (!connected) {
            throw new SMOSServerException("Connection to SMOS server interrupted. Cannot retrieve delay.");
        }
        Objects.requireNonNull(date, "Date cannot be null.");
        System.out.println("SMOS Server: Retrieving delay for date: " + date);
        // Simulate a small delay for data retrieval
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SMOSServerException("SMOS Server operation interrupted.");
        }
        return delaySettings.get(date);
    }

    /**
     * Updates the delay setting for a specific date.
     * This is the core server-side logic for the "EditDelay" use case.
     *
     * @param date      The date in "YYYY-MM-DD" format for which to update the delay.
     * @param newDelay  The new delay value in minutes. Must be non-negative.
     * @throws SMOSServerException if the server is not connected, or if the newDelay is invalid.
     */
    public void updateDelay(String date, int newDelay) throws SMOSServerException {
        if (!connected) {
            throw new SMOSServerException("Connection to SMOS server interrupted. Cannot update delay.");
        }
        Objects.requireNonNull(date, "Date cannot be null.");
        if (newDelay < 0) {
            throw new SMOSServerException("Invalid delay value. Delay cannot be negative.");
        }

        System.out.println("SMOS Server: Attempting to update delay for date '" + date + "' to " + newDelay + " minutes.");
        // Simulate a small delay for data update
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SMOSServerException("SMOS Server operation interrupted.");
        }

        delaySettings.put(date, newDelay);
        System.out.println("SMOS Server: Delay for date '" + date + "' updated to " + newDelay + " minutes.");
    }
}