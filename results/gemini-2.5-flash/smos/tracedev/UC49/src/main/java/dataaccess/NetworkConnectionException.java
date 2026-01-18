package dataaccess;

/**
 * Custom exception to represent network connection issues when interacting with data sources.
 */
public class NetworkConnectionException extends Exception {
    /**
     * Constructs a new NetworkConnectionException with the specified detail message.
     * @param message The detail message.
     */
    public NetworkConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}