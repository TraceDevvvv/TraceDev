package com.example.delay;

/**
 * Gateway to the SMOS server for sending delay data.
 */
public class SMOSServerGateway {
    private boolean connected = true; // Simulated connection status

    /**
     * Sends delay data to the server.
     * @param delay The delay to send.
     * @return true if successful.
     * @throws ConnectionException if connection is lost during sending.
     */
    public boolean sendDelayData(Delay delay) throws ConnectionException {
        // Simulate network operation
        if (!connected) {
            throw new ConnectionException("Not connected to server");
        }
        // Simulate random failure to mimic connection interrupted scenario.
        if (Math.random() < 0.1) {
            throw new ConnectionException("Connection interrupted during send");
        }
        System.out.println("Delay data sent to SMOS server: " + delay.getDate() + " -> " + delay.getDelay());
        return true;
    }

    /**
     * Checks if gateway is connected to server.
     * @return true if connected.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sets connection status (for simulation).
     * @param connected The connection status.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}