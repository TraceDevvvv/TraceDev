package com.etour.modifycomment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Tourist user who can modify comments on sites.
 * This class manages tourist information and provides methods
 * for comment-related operations.
 */
public class Tourist {
    private final String id;           // Unique identifier for the tourist
    private final String username;     // Tourist's username
    private final String fullName;     // Tourist's full name
    private boolean isAuthenticated;  // Whether the tourist is currently authenticated
    private final List<String> commentIds; // List of comment IDs created by this tourist
    
    /**
     * Constructor for creating a new Tourist.
     * 
     * @param id Unique identifier for the tourist
     * @param username Tourist's username
     * @param fullName Tourist's full name
     * @throws IllegalArgumentException if any required parameter is null or empty
     */
    public Tourist(String id, String username, String fullName) {
        validateTouristParameters(id, username, fullName);
        
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.isAuthenticated = false; // Tourist starts as not authenticated
        this.commentIds = new ArrayList<>();
    }
    
    /**
     * Validates tourist constructor parameters.
     * 
     * @param id Tourist ID
     * @param username Tourist username
     * @param fullName Tourist full name
     * @throws IllegalArgumentException if any parameter is invalid
     */
    private void validateTouristParameters(String id, String username, String fullName) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist username cannot be null or empty");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist full name cannot be null or empty");
        }
    }
    
    /**
     * Authenticates the tourist with the system.
     * This simulates the authentication process before allowing comment modifications.
     * 
     * @param authenticationToken Token for authentication
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String authenticationToken) {
        // In a real system, this would validate against a user database
        // For this implementation, we'll simulate authentication
        if (authenticationToken == null || authenticationToken.trim().isEmpty()) {
            this.isAuthenticated = false;
            return false;
        }
        
        // Simulate authentication - in real system, this would be more complex
        this.isAuthenticated = authenticationToken.equals("VALID_TOKEN_" + this.id);
        return this.isAuthenticated;
    }
    
    /**
     * Checks if the tourist is authenticated.
     * 
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    
    /**
     * Adds a comment ID to the tourist's list of comments.
     * This is called when the tourist creates a new comment.
     * 
     * @param commentId The ID of the comment created by this tourist
     */
    public void addComment(String commentId) {
        if (commentId != null && !commentId.trim().isEmpty()) {
            commentIds.add(commentId);
        }
    }
    
    /**
     * Checks if the tourist can modify a specific comment.
     * A tourist can only modify comments they have created.
     * 
     * @param comment The comment to check modification permission for
     * @return true if the tourist can modify the comment, false otherwise
     */
    public boolean canModifyComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        
        // Check if the tourist is the author of the comment
        return this.id.equals(comment.getAuthorId());
    }
    
    /**
     * Gets the tourist's unique identifier.
     * 
     * @return Tourist ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Gets the tourist's username.
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the tourist's full name.
     * 
     * @return Full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Gets the list of comment IDs created by this tourist.
     * 
     * @return List of comment IDs
     */
    public List<String> getCommentIds() {
        return new ArrayList<>(commentIds); // Return a copy for immutability
    }
    
    /**
     * Logs out the tourist from the system.
     */
    public void logout() {
        this.isAuthenticated = false;
    }
    
    /**
     * Creates a string representation of the tourist.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isAuthenticated=" + isAuthenticated +
                ", commentCount=" + commentIds.size() +
                '}';
    }
    
    /**
     * Checks if this tourist is equal to another object.
     * Tourists are considered equal if they have the same ID.
     * 
     * @param o The object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(id, tourist.id);
    }
    
    /**
     * Generates a hash code based on the tourist ID.
     * 
     * @return Hash code of the tourist
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}