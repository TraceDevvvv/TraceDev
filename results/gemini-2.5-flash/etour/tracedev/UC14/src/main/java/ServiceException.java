/**
 * Custom exception class indicating a service-layer error.
 * Used to abstract underlying exceptions from the business/data layers.
 * Added to satisfy Exit Conditions: Interruption of the connection to the server ETOUR.
 */
public class ServiceException extends Exception {
    /**
     * Constructs a new ServiceException with the specified detail message.
     * @param message The detail message.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}