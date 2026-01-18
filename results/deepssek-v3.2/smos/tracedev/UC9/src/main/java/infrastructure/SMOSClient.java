package infrastructure;

/**
 * Client for interacting with an external SMOS service.
 * Simulates connection and notification sending.
 */
public class SMOSClient {
    public boolean connect() {
        System.out.println("SMOSClient: Connected to external service.");
        return true;
    }

    public void disconnect() {
        System.out.println("SMOSClient: Disconnected from external service.");
    }

    public boolean sendNotification(String message) {
        System.out.println("SMOSClient: Notification sent - " + message);
        return true;
    }
}