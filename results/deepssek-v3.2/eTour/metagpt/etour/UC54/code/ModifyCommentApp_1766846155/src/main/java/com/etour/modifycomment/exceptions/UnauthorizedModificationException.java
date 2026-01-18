package com.etour.modifycomment.exceptions;

/**
 * Exception thrown when a tourist attempts to modify a comment they don't own
 * or have permission to modify.
 * 
 * This represents a security violation in the ModifyComment use case.
 * Tourists can only modify their own comments, and attempting to modify
 * someone else's comment should trigger this exception.
 */
public class UnauthorizedModificationException extends ModifyCommentException {
    
    private final String touristId;
    private final String commentId;
    private final String commentAuthorId;
    
    /**
     * Constructs a new UnauthorizedModificationException with detailed information.
     * 
     * @param touristId the ID of the tourist attempting the modification
     * @param commentId the ID of the comment being modified
     * @param commentAuthorId the ID of the actual author of the comment
     * @param message the detail message explaining the security violation
     */
    public UnauthorizedModificationException(String touristId, String commentId, 
                                            String commentAuthorId, String message) {
        super(message);
        this.touristId = touristId;
        this.commentId = commentId;
        this.commentAuthorId = commentAuthorId;
    }
    
    /**
     * Constructs a new UnauthorizedModificationException with the specified detail message.
     * 
     * @param message the detail message explaining the security violation
     */
    public UnauthorizedModificationException(String message) {
        super(message);
        this.touristId = null;
        this.commentId = null;
        this.commentAuthorId = null;
    }
    
    /**
     * Constructs a new UnauthorizedModificationException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the security violation
     * @param cause the underlying cause of the exception
     */
    public UnauthorizedModificationException(String message, Throwable cause) {
        super(message, cause);
        this.touristId = null;
        this.commentId = null;
        this.commentAuthorId = null;
    }
    
    /**
     * Gets the ID of the tourist who attempted the unauthorized modification.
     * 
     * @return the tourist ID, or null if not specified
     */
    public String getTouristId() {
        return touristId;
    }
    
    /**
     * Gets the ID of the comment that was attempted to be modified.
     * 
     * @return the comment ID, or null if not specified
     */
    public String getCommentId() {
        return commentId;
    }
    
    /**
     * Gets the ID of the actual author of the comment.
     * 
     * @return the author ID, or null if not specified
     */
    public String getCommentAuthorId() {
        return commentAuthorId;
    }
    
    /**
     * Gets a user-friendly error message for display to the tourist.
     * Overrides the base implementation to provide security-specific guidance.
     * 
     * @return a formatted error message suitable for user display
     */
    @Override
    public String getUserFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unauthorized Action\n\n");
        sb.append("You do not have permission to modify this comment.\n\n");
        
        if (touristId != null && commentAuthorId != null && !touristId.equals(commentAuthorId)) {
            sb.append("Reason: This comment was created by another tourist.\n");
            sb.append("Your ID: ").append(touristId).append("\n");
            sb.append("Comment Author ID: ").append(commentAuthorId).append("\n");
        }
        
        sb.append("\nYou can only modify comments that you have created.");
        
        return sb.toString();
    }
    
    /**
     * Gets a detailed error message for logging and debugging purposes.
     * This should be logged for security auditing.
     * 
     * @return a detailed error message including security audit details
     */
    @Override
    public String getDetailedErrorMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnauthorizedModificationException: ").append(getMessage()).append("\n");
        sb.append("Timestamp: ").append(java.time.LocalDateTime.now()).append("\n");
        
        if (touristId != null) {
            sb.append("Unauthorized Tourist ID: ").append(touristId).append("\n");
        }
        
        if (commentId != null) {
            sb.append("Target Comment ID: ").append(commentId).append("\n");
        }
        
        if (commentAuthorId != null) {
            sb.append("Comment Author ID: ").append(commentAuthorId).append("\n");
        }
        
        if (getCause() != null) {
            sb.append("Caused by: ").append(getCause().getClass().getName())
              .append(" - ").append(getCause().getMessage()).append("\n");
        }
        
        sb.append("Security Audit: This unauthorized access attempt has been logged.");
        
        return sb.toString();
    }
    
    /**
     * Creates a specific UnauthorizedModificationException for when a tourist
     * tries to modify someone else's comment.
     * 
     * @param touristId the ID of the tourist attempting the modification
     * @param comment the comment being modified
     * @return an UnauthorizedModificationException for the security violation
     */
    public static UnauthorizedModificationException forOtherTouristComment(String touristId, Comment comment) {
        return new UnauthorizedModificationException(
            touristId,
            comment.getId(),
            comment.getAuthorId(),
            "Tourist " + touristId + " attempted to modify comment " + comment.getId() + 
            " which belongs to tourist " + comment.getAuthorId()
        );
    }
    
    /**
     * Creates a specific UnauthorizedModificationException for when an
     * unauthenticated tourist tries to modify a comment.
     * 
     * @param touristId the ID of the unauthenticated tourist
     * @param commentId the ID of the comment being modified
     * @return an UnauthorizedModificationException for unauthenticated access
     */
    public static UnauthorizedModificationException forUnauthenticatedTourist(String touristId, String commentId) {
        return new UnauthorizedModificationException(
            touristId,
            commentId,
            null,
            "Unauthenticated tourist " + touristId + " attempted to modify comment " + commentId
        );
    }
    
    /**
     * Creates a string representation of the exception for debugging.
     * 
     * @return string representation including security details
     */
    @Override
    public String toString() {
        return "UnauthorizedModificationException{" +
                "touristId='" + touristId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", commentAuthorId='" + commentAuthorId + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}