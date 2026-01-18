/**
 * Custom exception for SMOS server connection issues.
 */
/**
 * Custom exception for SMOS server connection issues.
 */
public class ServerConnectionException extends Exception {
    /**
     * Constructor with error message.
     *
     * @param message The error message describing the server connection issue.
     */
    public ServerConnectionException(String message) {
        super(message);
    }
}