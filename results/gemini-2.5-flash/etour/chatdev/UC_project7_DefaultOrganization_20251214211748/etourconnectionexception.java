'''
Custom exception to simulate an interruption in connection to the ETOUR server.
'''
/**
 * Custom exception to simulate an interruption in connection to the ETOUR server.
 */
public class ETOURConnectionException extends Exception {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}