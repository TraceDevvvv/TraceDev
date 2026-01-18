package infrastructure;

/**
 * Custom exception indicating that a network connection to an external service (e.g., ETOUR server)
 * is unavailable or interrupted.
 */
public class NetworkUnavailableException extends Exception {
    public NetworkUnavailableException(String message) {
        super(message);
    }

    public NetworkUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}