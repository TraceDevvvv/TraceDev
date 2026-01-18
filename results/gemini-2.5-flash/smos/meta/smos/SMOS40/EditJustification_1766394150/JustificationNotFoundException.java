/**
 * Custom exception class to indicate that a requested Justification could not be found.
 * This exception is typically thrown by service layers when an operation
 * (like retrieval or update) is attempted on a non-existent justification.
 * It extends {@link RuntimeException} to allow for unchecked propagation,
 * simplifying error handling in cases where immediate recovery isn't expected
 * or where the calling code might not explicitly catch it.
 */
public class JustificationNotFoundException extends RuntimeException {

    /**
     * Constructs a new JustificationNotFoundException with no detail message.
     */
    public JustificationNotFoundException() {
        super();
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public JustificationNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public JustificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public JustificationNotFoundException(Throwable cause) {
        super(cause);
    }
}