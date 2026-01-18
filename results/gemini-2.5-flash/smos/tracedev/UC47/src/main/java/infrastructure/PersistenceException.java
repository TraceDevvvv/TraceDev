package infrastructure;

/**
 * Custom runtime exception for persistence layer errors.
 * Added to satisfy REQ-005. Stereotype added for R14 traceability clarification.
 */
public class PersistenceException extends RuntimeException {
    /**
     * Constructs a new PersistenceException with the specified detail message.
     * @param message the detail message.
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new PersistenceException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}