package com.etour.infrastructure;

/**
 * Client for communicating with the external ETOUR server.
 */
public class ETourServerClient {

    public ETourServerClient() {
        // Initialization code (e.g., setting up connection parameters) would go here.
    }

    /**
     * Notifies the ETOUR server that a bookmark was added.
     * Return true if notification succeeded, false otherwise.
     */
    public boolean notifyBookmarkAdded(String touristId, String siteId) {
        // Simulate server call; in reality this would be an HTTP request or similar.
        boolean connectionOk = simulateConnection();
        if (!connectionOk) {
            handleConnectionFailure();
            return false;
        }
        System.out.println("[ETourServer] Bookmark added for tourist " + touristId + ", site " + siteId);
        return true;
    }

    /**
     * Handles cases where connection to server ETOUR is interrupted (Exit Condition).
     */
    public void handleConnectionFailure() {
        System.err.println("[ETourServer] Connection interrupted – notification failed.");
        // In a real system, we might log the event, retry, or queue the notification.
    }

    // Simulates a flaky connection (fails roughly 20% of the time for demonstration).
    private boolean simulateConnection() {
        return Math.random() > 0.2;
    }
}