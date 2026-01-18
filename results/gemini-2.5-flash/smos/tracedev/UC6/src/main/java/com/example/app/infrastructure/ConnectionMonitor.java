package com.example.app.infrastructure;

/**
 * Monitors the application's connection status.
 * Added to satisfy R14.
 */
public class ConnectionMonitor {
    // For demonstration, this can be set by the Main class to simulate different scenarios.
    private static ConnectionStatus currentStatus = ConnectionStatus.STABLE;

    /**
     * Checks the current connection status.
     * Modified to satisfy R14.
     *
     * @return The current connection status (STABLE or INTERRUPTED).
     */
    public ConnectionStatus checkConnectionStatus() {
        System.out.println("[ConnectionMonitor] Checking connection status...");
        return currentStatus;
    }

    /**
     * Sets the connection status for testing purposes.
     * @param status The status to set.
     */
    public static void setConnectionStatus(ConnectionStatus status) {
        currentStatus = status;
        System.out.println("[ConnectionMonitor] Connection status set to: " + status);
    }

    /**
     * Handles the event of a system disconnect, typically notifying an administrator or logging the event.
     * Added to satisfy class diagram traceability for ConnectionMonitor.handleSystemDisconnect.
     * Also traces sequence diagram message m32 (CM -> Admin: notifyConnectionLost()).
     * @param adminId The ID or login of the administrator to be notified or whose session is affected.
     */
    public void handleSystemDisconnect(String adminId) {
        System.out.println("[ConnectionMonitor] System reports disconnect for admin: " + adminId + ". Initiating cleanup/reconnection strategy.");
        System.out.println("[ConnectionMonitor] Notifying admin of connection loss (m32: notifyConnectionLost()).");
    }
}