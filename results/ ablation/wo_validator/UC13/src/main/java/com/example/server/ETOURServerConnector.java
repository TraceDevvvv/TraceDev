package com.example.server;

/**
 * Connector to external ETOUR server for notifying account status changes.
 */
public class ETOURServerConnector {
    private String endPoint;
    private String apiKey;

    public ETOURServerConnector(String endPoint, String apiKey) {
        this.endPoint = endPoint;
        this.apiKey = apiKey;
    }

    /**
     * Notifies the ETOUR server about a tourist account status change.
     * @param touristId the tourist ID
     * @param isActive the new active status
     * @return true if notification succeeded, false otherwise
     */
    public boolean notifyAccountStatusChange(String touristId, boolean isActive) {
        // Simulate server notification
        System.out.println("Notifying ETOUR server at " + endPoint + " for tourist " + touristId + " active=" + isActive);
        // Assume success for demonstration
        return true;
    }

    /**
     * Disconnects from the ETOUR server.
     */
    public void disconnect() {
        System.out.println("Disconnected from ETOUR server.");
    }
}