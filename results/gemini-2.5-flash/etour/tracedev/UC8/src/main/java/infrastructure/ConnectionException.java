package infrastructure;

/**
 * Custom exception to simulate database or server connection issues.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}