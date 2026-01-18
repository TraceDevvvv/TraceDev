package com.example.editabsence;

/**
 * Simulates connection to an external SMOS server for data synchronization.
 * In a real application, this would involve actual network communication
 * and error handling for server interactions.
 */
public class SMOSServerConnection {
    private boolean connected;

    /**
     * Constructs a new SMOSServerConnection.
     * Initially, the connection is assumed to be disconnected.
     */
    public SMOSServerConnection() {
        this.connected = false;
    }

    /**
     * Simulates establishing a connection to the SMOS server.
     *
     * @return true if connection is successful, false otherwise.
     */
    public boolean connect() {
        System.out.println("Attempting to connect to SMOS server...");
        // Simulate connection success/failure
        // For demonstration, let's always succeed for now.
        this.connected = true;
        System.out.println("Successfully connected to SMOS server.");
        return true;
    }

    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnect() {
        if (connected) {
            System.out.println("Disconnecting from SMOS server...");
            this.connected = false;
            System.out.println("Disconnected from SMOS server.");
        } else {
            System.out.println("SMOS server is already disconnected.");
        }
    }

    /**
     * Checks if the application is currently connected to the SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Simulates sending modified data (e.g., absence changes) to the SMOS server.
     * This method can simulate connection interruptions.
     *
     * @param data The data to be sent to the server (e.g., a JSON string or a serialized object).
     * @return true if data was successfully sent, false if connection was interrupted or sending failed.
     */
    public boolean sendDataToServer(String data) {
        if (!connected) {
            System.err.println("Error: Cannot send data. Not connected to SMOS server.");
            return false;
        }

        System.out.println("Sending data to SMOS server: [" + data + "]");
        // Simulate potential network issues or server errors
        // For this example, let's assume it always succeeds unless explicitly disconnected.
        // In a real scenario, this would involve try-catch blocks for network exceptions.
        System.out.println("Data successfully sent to SMOS server.");
        return true;
    }

    /**
     * Simulates an unexpected interruption of the connection to the SMOS server.
     * This could be called to test error handling.
     */
    public void simulateConnectionInterruption() {
        if (connected) {
            System.err.println("Simulating SMOS server connection interruption!");
            this.connected = false;
        } else {
            System.out.println("SMOS server is already disconnected, no interruption to simulate.");
        }
    }
}