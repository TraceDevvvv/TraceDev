/**
 * Custom exception class for login errors.
 * Thrown when login data validation fails, such as incorrect credentials,
 * invalid input format, or when the user cancels the operation.
 * This exception provides specific error messages to inform the user
 * about the nature of the login failure.
 */
public class LoginError extends Exception {
    
    /**
     * Constructs a new LoginError with the specified detail message.
     *
     * @param message the detail message explaining the cause of the error
     */
    public LoginError(String message) {
        super(message);
    }
    
    /**
     * Constructs a new LoginError with the specified detail message and cause.
     *
     * @param message the detail message explaining the cause of the error
     * @param cause the underlying cause of this exception
     */
    public LoginError(String message, Throwable cause) {
        super(message, cause);
    }
}