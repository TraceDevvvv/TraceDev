/**
 * Custom exception thrown when the data provided for a tag is invalid or insufficient.
 * Corresponds to the 'Errored' use case when data is invalid or insufficient.
 */
public class InvalidTagDataException extends Exception {
    /**
     * Constructs a new InvalidTagDataException with the specified detail message.
     * @param message The detail message.
     */
    public InvalidTagDataException(String message) {
        super(message);
    }
}