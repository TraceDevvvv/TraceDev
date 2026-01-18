/**
 * Custom exception class specifically for handling the LoginError use case.
 * This exception is thrown when a user provides incorrect authentication data
 * during the login process. It represents the "LoginError" scenario described
 * in the use case specification.
 * 
 * The LoginErrorException extends RuntimeException to allow for unchecked exception
 * handling, which is appropriate for login errors as they represent expected
 * business logic failures rather than unexpected system errors.
 * 
 * Key characteristics of this exception:
 * - Represents incorrect authentication data (LoginError use case)
 * - Provides informative error messages for users
 * - Supports cause chaining for debugging
 * - Includes error codes for different types of login failures
 */
public class LoginErrorException extends RuntimeException {
    
    /**
     * Error codes for different types of login errors.
     * These provide more granular information about what went wrong.
     */
    public enum ErrorCode {
        INVALID_CREDENTIALS("Invalid username or password"),
        ACCOUNT_LOCKED("Account is temporarily locked due to too many failed attempts"),
        ACCOUNT_DISABLED("Account is disabled. Please contact administrator"),
        PASSWORD_EXPIRED("Password has expired. Please reset your password"),
        INVALID_USERNAME_FORMAT("Invalid username format"),
        INVALID_PASSWORD_FORMAT("Invalid password format");
        
        private final String description;
        
        ErrorCode(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    private final ErrorCode errorCode;
    private final String usernameAttempted;
    
    /**
     * Constructs a new LoginErrorException with the specified detail message.
     * This constructor is appropriate for simple login failures.
     * 
     * @param message the detail message explaining the login error
     */
    public LoginErrorException(String message) {
        super(message);
        this.errorCode = ErrorCode.INVALID_CREDENTIALS;
        this.usernameAttempted = null;
    }
    
    /**
     * Constructs a new LoginErrorException with the specified detail message and cause.
     * This constructor is useful when the login error is caused by another exception.
     * 
     * @param message the detail message explaining the login error
     * @param cause the cause of the exception (can be null)
     */
    public LoginErrorException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCode.INVALID_CREDENTIALS;
        this.usernameAttempted = null;
    }
    
    /**
     * Constructs a new LoginErrorException with a specific error code.
     * This constructor provides more specific information about the type of login error.
     * 
     * @param errorCode the specific error code indicating the type of login failure
     * @param usernameAttempted the username that was attempted (can be null)
     */
    public LoginErrorException(ErrorCode errorCode, String usernameAttempted) {
        super(errorCode.getDescription() + 
              (usernameAttempted != null ? " (Username: " + maskUsername(usernameAttempted) + ")" : ""));
        this.errorCode = errorCode;
        this.usernameAttempted = usernameAttempted;
    }
    
    /**
     * Constructs a new LoginErrorException with a specific error code and cause.
     * This constructor provides both specific error information and the underlying cause.
     * 
     * @param errorCode the specific error code indicating the type of login failure
     * @param usernameAttempted the username that was attempted (can be null)
     * @param cause the cause of the exception (can be null)
     */
    public LoginErrorException(ErrorCode errorCode, String usernameAttempted, Throwable cause) {
        super(errorCode.getDescription() + 
              (usernameAttempted != null ? " (Username: " + maskUsername(usernameAttempted) + ")" : ""), 
              cause);
        this.errorCode = errorCode;
        this.usernameAttempted = usernameAttempted;
    }
    
    /**
     * Gets the specific error code for this login error.
     * 
     * @return the ErrorCode enum value representing the type of login failure
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    /**
     * Gets the username that was attempted during the failed login.
     * For security reasons, this might be null or masked.
     * 
     * @return the username that was attempted, or null if not available
     */
    public String getUsernameAttempted() {
        return usernameAttempted;
    }
    
    /**
     * Gets a user-friendly error message that includes the error code description.
     * This method provides a standardized way to display login errors to users.
     * 
     * @return a formatted error message suitable for user display
     */
    public String getUserFriendlyMessage() {
        String baseMessage = errorCode.getDescription();
        
        // Add security-conscious username info if available
        if (usernameAttempted != null && !usernameAttempted.isEmpty()) {
            return baseMessage + " Please check your credentials and try again.";
        }
        
        return baseMessage + " Please check your credentials and try again.";
    }
    
    /**
     * Helper method to mask usernames for security and privacy.
     * Shows first 3 characters and masks the rest with asterisks.
     * 
     * @param username the original username
     * @return a masked version of the username for security
     */
    private static String maskUsername(String username) {
        if (username == null || username.length() <= 3) {
            return "***"; // Fully masked for very short usernames
        }
        
        String firstPart = username.substring(0, Math.min(3, username.length()));
        return firstPart + "***";
    }
    
    /**
     * Checks if this exception represents an invalid credentials error.
     * This is the most common type of LoginError.
     * 
     * @return true if this is an invalid credentials error, false otherwise
     */
    public boolean isInvalidCredentials() {
        return errorCode == ErrorCode.INVALID_CREDENTIALS;
    }
    
    /**
     * Checks if this exception represents an account-related error
     * (locked, disabled, etc.) rather than just incorrect credentials.
     * 
     * @return true if this is an account-related error, false otherwise
     */
    public boolean isAccountError() {
        return errorCode == ErrorCode.ACCOUNT_LOCKED || 
               errorCode == ErrorCode.ACCOUNT_DISABLED ||
               errorCode == ErrorCode.PASSWORD_EXPIRED;
    }
    
    /**
     * Provides a detailed debug string with all exception information.
     * This is useful for logging and debugging purposes.
     * 
     * @return a detailed string representation of the exception
     */
    @Override
    public String toString() {
        return "LoginErrorException{" +
               "errorCode=" + errorCode +
               ", usernameAttempted='" + (usernameAttempted != null ? maskUsername(usernameAttempted) : "null") + '\'' +
               ", message='" + getMessage() + '\'' +
               ", cause=" + (getCause() != null ? getCause().getClass().getSimpleName() : "null") +
               '}';
    }
    
    /**
     * Static factory method for creating an invalid credentials exception.
     * This is a convenience method for the most common LoginError scenario.
     * 
     * @param username the username that was attempted
     * @return a new LoginErrorException for invalid credentials
     */
    public static LoginErrorException invalidCredentials(String username) {
        return new LoginErrorException(ErrorCode.INVALID_CREDENTIALS, username);
    }
    
    /**
     * Static factory method for creating an account locked exception.
     * 
     * @param username the username that was attempted
     * @return a new LoginErrorException for account locked scenario
     */
    public static LoginErrorException accountLocked(String username) {
        return new LoginErrorException(ErrorCode.ACCOUNT_LOCKED, username);
    }
}