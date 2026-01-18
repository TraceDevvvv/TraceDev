/**
 * Simulates a connection to an SMOS server.
 * This class is designed to mimic potential connection issues,
 * specifically an "interrupted" state as described in the use case.
 */
public class SMOSConnection {

    // A static flag to simulate a global connection state.
    // In a real application, this would be managed by a more robust connection manager.
    private static boolean connectionInterrupted = false;

    /**
     * Simulates establishing a connection to the SMOS server.
     * If the connection is currently marked as interrupted, it throws an exception.
     *
     * @throws SMOSConnectionException if the connection is interrupted.
     */
    public void connect() throws SMOSConnectionException {
        if (connectionInterrupted) {
            throw new SMOSConnectionException("Connection to SMOS server interrupted.");
        }
        // In a real scenario, this would involve network calls, authentication, etc.
        System.out.println("SMOSConnection: Successfully connected to SMOS server.");
    }

    /**
     * Simulates sending data to the SMOS server.
     * This operation can only proceed if the connection is not interrupted.
     *
     * @param data The data string to send.
     * @throws SMOSConnectionException if the connection is interrupted.
     */
    public void sendData(String data) throws SMOSConnectionException {
        if (connectionInterrupted) {
            throw new SMOSConnectionException("SMOSConnection: Cannot send data, connection to SMOS server interrupted.");
        }
        // Simulate data transmission
        System.out.println("SMOSConnection: Sending data to SMOS server: '" + data + "'");
        // In a real scenario, this would involve actual data transfer.
    }

    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnect() {
        System.out.println("SMOSConnection: Disconnected from SMOS server.");
        // Reset the interrupted state upon explicit disconnection, if desired.
        // For this use case, we might want the interruption to persist until explicitly reset.
        // setConnectionInterrupted(false);
    }

    /**
     * Sets the connection interrupted state.
     * This method is primarily for simulating error conditions for testing purposes.
     *
     * @param interrupted true to simulate an interrupted connection, false for a normal connection.
     */
    public static void setConnectionInterrupted(boolean interrupted) {
        connectionInterrupted = interrupted;
        if (interrupted) {
            System.err.println("SMOSConnection: !!! SMOS server connection has been INTERRUPTED !!!");
        } else {
            System.out.println("SMOSConnection: SMOS server connection is now stable.");
        }
    }

    /**
     * Custom exception for SMOS connection errors.
     */
    public static class SMOSConnectionException extends Exception {
        public SMOSConnectionException(String message) {
            super(message);
        }
    }
}