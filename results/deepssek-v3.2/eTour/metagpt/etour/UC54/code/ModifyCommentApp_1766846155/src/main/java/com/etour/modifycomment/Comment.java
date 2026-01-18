package com.etour.modifycomment;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a comment made by a tourist on a site.
 * This class encapsulates all the attributes of a comment and provides
 * methods for modification while maintaining data integrity.
 */
public class Comment {
    private final String id;           // Unique identifier for the comment
    private String content;           // The actual comment text
    private final String authorId;     // ID of the tourist who wrote the comment
    private final String siteId;       // ID of the site this comment belongs to
    private LocalDateTime createdAt;  // When the comment was originally created
    private LocalDateTime modifiedAt; // When the comment was last modified
    private boolean isModified;       // Flag to track if comment has been modified
    
    /**
     * Constructor for creating a new comment.
     * 
     * @param id Unique identifier for the comment
     * @param content The comment text
     * @param authorId ID of the tourist who wrote the comment
     * @param siteId ID of the site this comment belongs to
     * @throws IllegalArgumentException if any required parameter is null or empty
     */
    public Comment(String id, String content, String authorId, String siteId) {
        validateParameters(id, content, authorId, siteId);
        
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.siteId = siteId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
        this.isModified = false;
    }
    
    /**
     * Validates constructor parameters to ensure data integrity.
     * 
     * @param id Comment ID
     * @param content Comment content
     * @param authorId Author ID
     * @param siteId Site ID
     * @throws IllegalArgumentException if any parameter is invalid
     */
    private void validateParameters(String id, String content, String authorId, String siteId) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment ID cannot be null or empty");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty");
        }
        if (authorId == null || authorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Author ID cannot be null or empty");
        }
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty");
        }
    }
    
    /**
     * Modifies the comment content with validation.
     * This method implements the core functionality of the ModifyComment use case.
     * 
     * @param newContent The new content for the comment
     * @return true if modification was successful, false otherwise
     * @throws IllegalArgumentException if new content is invalid
     */
    public boolean modifyContent(String newContent) {
        // Validate the new content
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("New comment content cannot be null or empty");
        }
        
        // Check if content is actually changing
        if (this.content.equals(newContent)) {
            return false; // No change needed
        }
        
        // Update the content and modification metadata
        this.content = newContent;
        this.modifiedAt = LocalDateTime.now();
        this.isModified = true;
        
        return true;
    }
    
    /**
     * Gets the current content of the comment.
     * 
     * @return The comment content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Gets the unique identifier of the comment.
     * 
     * @return Comment ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Gets the ID of the tourist who wrote the comment.
     * 
     * @return Author ID
     */
    public String getAuthorId() {
        return authorId;
    }
    
    /**
     * Gets the ID of the site this comment belongs to.
     * 
     * @return Site ID
     */
    public String getSiteId() {
        return siteId;
    }
    
    /**
     * Gets the creation timestamp of the comment.
     * 
     * @return Creation date and time
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Gets the last modification timestamp of the comment.
     * 
     * @return Last modification date and time
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    
    /**
     * Checks if the comment has been modified.
     * 
     * @return true if the comment has been modified, false otherwise
     */
    public boolean isModified() {
        return isModified;
    }
    
    /**
     * Creates a string representation of the comment for debugging and logging.
     * 
     * @return String representation of the comment
     */
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", authorId='" + authorId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", isModified=" + isModified +
                '}';
    }
    
    /**
     * Checks if this comment is equal to another object.
     * Comments are considered equal if they have the same ID.
     * 
     * @param o The object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }
    
    /**
     * Generates a hash code based on the comment ID.
     * 
     * @return Hash code of the comment
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}