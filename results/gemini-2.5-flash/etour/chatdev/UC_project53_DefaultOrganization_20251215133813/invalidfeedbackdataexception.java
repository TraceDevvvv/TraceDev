'''
Custom exception for when feedback data is invalid or insufficient.
This corresponds to the "Errored" use case.
'''
public class InvalidFeedbackDataException extends Exception {
    /**
     * Constructs a new InvalidFeedbackDataException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public InvalidFeedbackDataException(String message) {
        super(message);
    }
}