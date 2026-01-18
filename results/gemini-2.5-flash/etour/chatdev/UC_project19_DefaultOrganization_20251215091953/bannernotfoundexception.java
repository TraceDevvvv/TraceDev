'''
Custom exception to represent a scenario where a requested banner is not found.
This can occur if a banner ID is invalid or the banner has already been deleted.
'''
package exception;
public class BannerNotFoundException extends Exception {
    /**
     * Constructs a new BannerNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public BannerNotFoundException(String message) {
        super(message);
    }
}