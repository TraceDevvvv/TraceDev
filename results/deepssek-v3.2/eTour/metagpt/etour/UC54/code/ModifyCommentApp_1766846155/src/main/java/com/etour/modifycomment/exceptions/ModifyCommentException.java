package com.etour.modifycomment.exceptions;

/**
 * Base exception class for all ModifyComment application exceptions.
 * This exception hierarchy is used to handle various error cases
 * as specified in the use case requirements.
 * 
 * When data is invalid or insufficient, the system activates the
 * error case by throwing appropriate exceptions from this hierarchy.
 */
public class ModifyCommentException extends Exception {
    
    /**
     * Constructs a new ModifyCommentException with the specified detail message.
     * 
     * @param message the detail message explaining the error
     */
    public ModifyCommentException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ModifyCommentException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the error
     * @param cause the underlying cause of the exception
     */
    public ModifyCommentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new ModifyCommentException with the specified cause.
     * 
     * @param cause the underlying cause of the exception
     */
    public ModifyCommentException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Gets a user-friendly error message for display to the tourist.
     * 
     * @return a formatted error message suitable for user display
     */
    public String getUserFriendlyMessage() {
        return "Error: " + getMessage() + 
               "\nPlease check your input and try again, or contact support if the problem persists.";
    }
    
    /**
     * Gets a detailed error message for logging and debugging purposes.
     * 
     * @return a detailed error message including stack trace information
     */
    public String getDetailedErrorMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModifyCommentException: ").append(getMessage()).append("\n");
        sb.append("Timestamp: ").append(java.time.LocalDateTime.now()).append("\n");
        
        if (getCause() != null) {
            sb.append("Caused by: ").append(getCause().getClass().getName())
              .append(" - ").append(getCause().getMessage()).append("\n");
        }
        
        return sb.toString();
    }
}