/**
 * Custom exception class for AddressService related errors,
 * such as validation failures or simulated server errors.
 */
public class AddressServiceException extends Exception {
    /**
     * Constructs a new AddressServiceException with the specified detail message.
     * @param message The detail message.
     */
    public AddressServiceException(String message) {
        super(message);
    }
}