/**
 * JusticeService handles business logic for justice operations including
 * retrieval, modification, deletion, and SMOS server communication.
 */
import java.util.HashMap;
import java.util.Map;
public class JusticeService {
    private boolean serverConnected = true;
    private Map<String, JusticeData> justiceDatabase;
    /**
     * Constructor initializes the justice database with sample data.
     */
    public JusticeService() {
        justiceDatabase = new HashMap<>();
        // Initialize with sample justice records
        justiceDatabase.put("J001", new JusticeData("J001", "John Doe", "2023-10-15", "Medical Appointment", "Approved"));
        justiceDatabase.put("J002", new JusticeData("J002", "Jane Smith", "2023-10-16", "Family Emergency", "Pending"));
        justiceDatabase.put("J003", new JusticeData("J003", "Robert Johnson", "2023-10-17", "Car Breakdown", "Rejected"));
    }
    /**
     * Retrieves justice details by ID.
     *
     * @param justiceId The ID of the justice to retrieve.
     * @return JusticeData object if found, null otherwise.
     * @throws ServerConnectionException if SMOS server connection is interrupted.
     */
    public JusticeData getJusticeDetails(String justiceId) throws ServerConnectionException {
        checkServerConnection();
        return justiceDatabase.get(justiceId);
    }
    /**
     * Updates an existing justice record.
     *
     * @param justiceId The ID of the justice to update.
     * @param updatedJustice The updated JusticeData object.
     * @return true if update successful, false otherwise.
     * @throws ServerConnectionException if SMOS server connection is interrupted.
     */
    public boolean updateJustice(String justiceId, JusticeData updatedJustice) throws ServerConnectionException {
        checkServerConnection();
        if (justiceDatabase.containsKey(justiceId)) {
            justiceDatabase.put(justiceId, updatedJustice);
            return true;
        }
        return false;
    }
    /**
     * Deletes a justice record.
     *
     * @param justiceId The ID of the justice to delete.
     * @return true if deletion successful, false otherwise.
     * @throws ServerConnectionException if SMOS server connection is interrupted.
     */
    public boolean deleteJustice(String justiceId) throws ServerConnectionException {
        checkServerConnection();
        if (justiceDatabase.containsKey(justiceId)) {
            justiceDatabase.remove(justiceId);
            return true;
        }
        return false;
    }
    /**
     * Simulates checking SMOS server connection.
     * Throws exception if connection is interrupted.
     *
     * @throws ServerConnectionException when server connection is lost.
     */
    private void checkServerConnection() throws ServerConnectionException {
        if (!serverConnected) {
            throw new ServerConnectionException("SMOS server connection interrupted");
        }
    }
    /**
     * Simulates server connection interruption.
     * Sets the server connection status to false.
     */
    public void interruptServerConnection() {
        serverConnected = false;
        System.out.println("[SYSTEM] SMOS server connection has been interrupted by administrator.");
    }
    /**
     * Restores server connection.
     * Sets the server connection status to true.
     */
    public void restoreServerConnection() {
        serverConnected = true;
        System.out.println("[SYSTEM] SMOS server connection restored.");
    }
    /**
     * Checks if server is currently connected.
     *
     * @return true if server is connected, false otherwise.
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
}