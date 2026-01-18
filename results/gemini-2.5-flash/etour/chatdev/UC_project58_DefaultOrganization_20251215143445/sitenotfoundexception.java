/**
 * Custom exception to indicate that a requested site was not found in the database.
 */
public class SiteNotFoundException extends Exception {
    /**
     * Constructs a new SiteNotFoundException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SiteNotFoundException(String message) {
        super(message);
    }
}