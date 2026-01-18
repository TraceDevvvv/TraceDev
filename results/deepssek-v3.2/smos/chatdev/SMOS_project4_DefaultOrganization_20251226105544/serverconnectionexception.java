/**
 * Custom exception for server connection errors.
 */
public class ServerConnectionException extends Exception {
    /**
     * Constructor with a custom message.
     * @param message The detail message.
     */
    public ServerConnectionException(String message) {
        super(message);
    }
}