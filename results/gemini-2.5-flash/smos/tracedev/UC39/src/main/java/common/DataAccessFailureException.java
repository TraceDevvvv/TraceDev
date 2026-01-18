package common;

/**
 * Custom exception for data access failures, as specified in the Class Diagram (REQ-013).
 * This typically indicates issues like database connectivity problems, unavailable serv, etc.
 */
public class DataAccessFailureException extends Exception {

    /**
     * Constructs a new DataAccessFailureException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public DataAccessFailureException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataAccessFailureException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DataAccessFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}