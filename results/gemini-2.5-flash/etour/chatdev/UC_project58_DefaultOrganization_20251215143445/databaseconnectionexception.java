/**
 * Custom exception to indicate an interruption in the database connection.
 */
public class DatabaseConnectionException extends Exception {
    /**
     * Constructs a new DatabaseConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }
}