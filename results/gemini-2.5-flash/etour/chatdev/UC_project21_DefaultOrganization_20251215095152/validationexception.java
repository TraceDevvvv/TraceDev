/**
 * `ValidationException` is a custom exception class used to indicate errors
 * related to data validation in the news insertion process.
 * This aligns with the 'Errored' use case for invalid or insufficient data.
 */
public class ValidationException extends Exception {
    /**
     * Constructs a new `ValidationException` with the specified detail message.
     * The message should describe the nature of the validation error.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public ValidationException(String message) {
        super(message);
    }
}