package infrastructure;

/**
 * Client for the external ETOUR system.
 * Added for requirement EC-002.
 */
public class ETOURServiceClient {
    // HttpClient omitted; using simulation.

    public ETOURServiceClient() {
        // In real app, would inject HttpClient
    }

    /**
     * Checks connection to ETOUR server.
     * @return true if connected, false otherwise.
     */
    public boolean checkConnection() {
        // Simulate connection check; for demonstration, assume always connected.
        return true;
    }

    /**
     * Submits convention to ETOUR system.
     * @param conventionId the convention id
     * @return true if successful
     */
    public boolean submitToETOUR(String conventionId) {
        // Simulate submission; in real app would make HTTP call.
        System.out.println("Convention " + conventionId + " submitted to ETOUR system.");
        return true;
    }
}