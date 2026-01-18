package com.etour.modifycomment.exceptions;

/**
 * Exception thrown when comment data is invalid or insufficient.
 * This activates the error case as specified in the use case requirements.
 * 
 * Use case step 2 states: "Verify the data entered and asks for confirmation
 * of the change. Where the data is invalid or insufficient, the system 
 * activates the use case Errored."
 * 
 * This exception is thrown when comment validation fails due to:
 * - Empty or null content
 * - Content that's too short or too long
 * - Content containing inappropriate words
 * - Any other validation rule violation
 */
public class InvalidCommentException extends ModifyCommentException {
    
    private final String fieldName;
    private final String invalidValue;
    private final String validationRule;
    
    /**
     * Constructs a new InvalidCommentException with the specified detail message.
     * 
     * @param message the detail message explaining the validation error
     */
    public InvalidCommentException(String message) {
        super(message);
        this.fieldName = "content";
        this.invalidValue = null;
        this.validationRule = null;
    }
    
    /**
     * Constructs a new InvalidCommentException with detailed field information.
     * 
     * @param fieldName the name of the field that failed validation
     * @param invalidValue the invalid value that caused the failure
     * @param validationRule the validation rule that was violated
     * @param message the detail message explaining the validation error
     */
    public InvalidCommentException(String fieldName, String invalidValue, 
                                   String validationRule, String message) {
        super(message);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
        this.validationRule = validationRule;
    }
    
    /**
     * Constructs a new InvalidCommentException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the validation error
     * @param cause the underlying cause of the exception
     */
    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
        this.fieldName = "content";
        this.invalidValue = null;
        this.validationRule = null;
    }
    
    /**
     * Gets the name of the field that failed validation.
     * 
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }
    
    /**
     * Gets the invalid value that caused the validation failure.
     * 
     * @return the invalid value, or null if not specified
     */
    public String getInvalidValue() {
        return invalidValue;
    }
    
    /**
     * Gets the validation rule that was violated.
     * 
     * @return the validation rule, or null if not specified
     */
    public String getValidationRule() {
        return validationRule;
    }
    
    /**
     * Gets a user-friendly error message for display to the tourist.
     * Overrides the base implementation to provide more specific guidance.
     * 
     * @return a formatted error message suitable for user display
     */
    @Override
    public String getUserFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comment Validation Error\n\n");
        sb.append("We encountered an issue with your comment:\n");
        sb.append("• ").append(getMessage()).append("\n\n");
        
        if (fieldName != null) {
            sb.append("Field: ").append(fieldName).append("\n");
        }
        
        if (validationRule != null) {
            sb.append("Rule: ").append(validationRule).append("\n");
        }
        
        sb.append("\nPlease correct the issue and try again.");
        
        return sb.toString();
    }
    
    /**
     * Gets a detailed error message for logging and debugging purposes.
     * 
     * @return a detailed error message including validation details
     */
    @Override
    public String getDetailedErrorMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvalidCommentException: ").append(getMessage()).append("\n");
        sb.append("Timestamp: ").append(java.time.LocalDateTime.now()).append("\n");
        
        if (fieldName != null) {
            sb.append("Field: ").append(fieldName).append("\n");
        }
        
        if (invalidValue != null) {
            sb.append("Invalid Value: '").append(invalidValue).append("'\n");
        }
        
        if (validationRule != null) {
            sb.append("Validation Rule: ").append(validationRule).append("\n");
        }
        
        if (getCause() != null) {
            sb.append("Caused by: ").append(getCause().getClass().getName())
              .append(" - ").append(getCause().getMessage()).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Creates a specific InvalidCommentException for empty content.
     * 
     * @return an InvalidCommentException for empty content
     */
    public static InvalidCommentException forEmptyContent() {
        return new InvalidCommentException(
            "content", 
            "", 
            "Comment content cannot be empty or null", 
            "Comment content is required and cannot be empty."
        );
    }
    
    /**
     * Creates a specific InvalidCommentException for content that's too short.
     * 
     * @param actualLength the actual length of the content
     * @param minLength the minimum required length
     * @return an InvalidCommentException for content that's too short
     */
    public static InvalidCommentException forContentTooShort(int actualLength, int minLength) {
        return new InvalidCommentException(
            "content",
            "(content length: " + actualLength + ")",
            "Minimum length: " + minLength + " characters",
            "Comment must be at least " + minLength + " characters long. " +
            "Current length: " + actualLength + " characters."
        );
    }
    
    /**
     * Creates a specific InvalidCommentException for content that's too long.
     * 
     * @param actualLength the actual length of the content
     * @param maxLength the maximum allowed length
     * @return an InvalidCommentException for content that's too long
     */
    public static InvalidCommentException forContentTooLong(int actualLength, int maxLength) {
        return new InvalidCommentException(
            "content",
            "(content length: " + actualLength + ")",
            "Maximum length: " + maxLength + " characters",
            "Comment cannot exceed " + maxLength + " characters. " +
            "Current length: " + actualLength + " characters."
        );
    }
    
    /**
     * Creates a specific InvalidCommentException for inappropriate content.
     * 
     * @param inappropriateWord the inappropriate word found in the content
     * @return an InvalidCommentException for inappropriate content
     */
    public static InvalidCommentException forInappropriateContent(String inappropriateWord) {
        return new InvalidCommentException(
            "content",
            "(contains: '" + inappropriateWord + "')",
            "No inappropriate content allowed",
            "Comment contains inappropriate content: '" + inappropriateWord + "'. " +
            "Please remove it and try again."
        );
    }
    
    /**
     * Creates a string representation of the exception for debugging.
     * 
     * @return string representation including validation details
     */
    @Override
    public String toString() {
        return "InvalidCommentException{" +
                "fieldName='" + fieldName + '\'' +
                ", invalidValue='" + invalidValue + '\'' +
                ", validationRule='" + validationRule + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}