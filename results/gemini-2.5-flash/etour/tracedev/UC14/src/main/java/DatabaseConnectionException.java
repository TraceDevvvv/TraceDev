/**
 * Custom exception class indicating an error during database connection or operation.
 * Added to satisfy Exit Conditions: Interruption of the connection to the server ETOUR.
 */
public class DatabaseConnectionException extends Exception {
    /**
     * Constructs a new DatabaseConnectionException with the specified detail message.
     * @param message The detail message.
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}