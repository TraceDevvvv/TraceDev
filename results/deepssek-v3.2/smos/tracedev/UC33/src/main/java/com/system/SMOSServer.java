package com.system;

/**
 * Simulates a connection to the SMOS server for archive operations.
 */
public class SMOSServer {
    private boolean connected = true; // Default to connected for simulation.

    /**
     * Checks if the server is connected.
     * @return connection status.
     */
    public boolean isConnected() {
        // Simulate occasional disconnection.
        if (Math.random() < 0.2) {
            connected = false;
            System.out.println("SMOSServer: Connection lost.");
        }
        return connected;
    }

    /**
     * Saves an archive entry to the server.
     * @param entry the entry to save.
     * @return true if saved successfully.
     */
    public boolean saveToArchive(ArchiveEntry entry) {
        if (!isConnected()) {
            return false;
        }
        System.out.println("SMOSServer: Saved entry " + entry.getEntryId() + " to archive.");
        return true;
    }

    /**
     * Sets the connection status (for testing).
     * @param connected new connection status.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}