'''
Custom exception for service-related errors, such as connection interruption or save failures.
'''
public class ServiceException extends Exception {
    /**
     * Constructs a new ServiceException with the specified detail message.
     * @param message The detail message.
     */
    public ServiceException(String message) {
        super(message);
    }
    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}