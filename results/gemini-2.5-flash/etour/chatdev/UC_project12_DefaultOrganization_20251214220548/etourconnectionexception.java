/**
 * Custom exception to represent an interruption of the connection to the ETOUR server.
 * This helps in specifically catching and handling connection-related errors.
 */
public class ETOURConnectionException extends Exception {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the getCause() method).
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}