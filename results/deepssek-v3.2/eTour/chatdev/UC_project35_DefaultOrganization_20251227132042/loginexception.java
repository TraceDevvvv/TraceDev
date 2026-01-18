'''
Custom exception class to handle login errors as specified in the use case.
This provides detailed error types for different login failure scenarios.
'''
public class LoginException extends Exception {
    private LoginErrorType errorType;
    /**
     * Constructs a new LoginException with specific error type and message.
     * @param errorType The type of login error that occurred.
     * @param message Detailed error message.
     */
    public LoginException(LoginErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
    /**
     * Gets the error type associated with this exception.
     * @return The LoginErrorType.
     */
    public LoginErrorType getErrorType() {
        return errorType;
    }
}