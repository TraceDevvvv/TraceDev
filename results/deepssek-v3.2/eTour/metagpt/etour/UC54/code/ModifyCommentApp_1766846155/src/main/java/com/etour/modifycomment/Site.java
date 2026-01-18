package com.etour.modifycomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Site that contains comments and feedback from tourists.
 * This class manages the site information and provides methods for
 * handling comments associated with the site.
 */
public class Site {
    private final String id;                    // Unique identifier for the site
    private final String name;                  // Name of the site
    private final String description;          // Description of the site
    private final Map<String, Comment> comments; // Map of comment ID to Comment object
    private double averageRating;              // Average rating based on feedback
    private int totalComments;                 // Total number of comments on this site
    
    /**
     * Constructor for creating a new Site.
     * 
     * @param id Unique identifier for the site
     * @param name Name of the site
     * @param description Description of the site
     * @throws IllegalArgumentException if any required parameter is null or empty
     */
    public Site(String id, String name, String description) {
        validateSiteParameters(id, name, description);
        
        this.id = id;
        this.name = name;
        this.description = description;
        this.comments = new HashMap<>();
        this.averageRating = 0.0;
        this.totalComments = 0;
    }
    
    /**
     * Validates site constructor parameters.
     * 
     * @param id Site ID
     * @param name Site name
     * @param description Site description
     * @throws IllegalArgumentException if any parameter is invalid
     */
    private void validateSiteParameters(String id, String name, String description) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Site name cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Site description cannot be null or empty");
        }
    }
    
    /**
     * Adds a comment to the site.
     * This method is called when a tourist posts a new comment on the site.
     * 
     * @param comment The comment to add
     * @return true if comment was added successfully, false otherwise
     * @throws IllegalArgumentException if comment is null or doesn't belong to this site
     */
    public boolean addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        
        if (!this.id.equals(comment.getSiteId())) {
            throw new IllegalArgumentException("Comment does not belong to this site");
        }
        
        // Check if comment already exists
        if (comments.containsKey(comment.getId())) {
            return false; // Comment already exists
        }
        
        // Add the comment to the map
        comments.put(comment.getId(), comment);
        totalComments++;
        
        // In a real system, we would update the average rating here
        // based on any rating information in the comment
        
        return true;
    }
    
    /**
     * Gets a comment by its ID.
     * 
     * @param commentId The ID of the comment to retrieve
     * @return The Comment object, or null if not found
     */
    public Comment getComment(String commentId) {
        if (commentId == null || commentId.trim().isEmpty()) {
            return null;
        }
        return comments.get(commentId);
    }
    
    /**
     * Gets all comments for this site.
     * 
     * @return List of all comments on this site
     */
    public List<Comment> getAllComments() {
        return new ArrayList<>(comments.values()); // Return a copy for immutability
    }
    
    /**
     * Gets comments written by a specific tourist.
     * 
     * @param touristId The ID of the tourist
     * @return List of comments written by the specified tourist
     */
    public List<Comment> getCommentsByTourist(String touristId) {
        List<Comment> touristComments = new ArrayList<>();
        
        if (touristId == null || touristId.trim().isEmpty()) {
            return touristComments;
        }
        
        for (Comment comment : comments.values()) {
            if (touristId.equals(comment.getAuthorId())) {
                touristComments.add(comment);
            }
        }
        
        return touristComments;
    }
    
    /**
     * Updates a comment on the site.
     * This method is used to modify an existing comment.
     * 
     * @param commentId The ID of the comment to update
     * @param newContent The new content for the comment
     * @return true if update was successful, false otherwise
     * @throws IllegalArgumentException if commentId or newContent is invalid
     */
    public boolean updateComment(String commentId, String newContent) {
        if (commentId == null || commentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment ID cannot be null or empty");
        }
        
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("New content cannot be null or empty");
        }
        
        Comment comment = comments.get(commentId);
        if (comment == null) {
            return false; // Comment not found
        }
        
        return comment.modifyContent(newContent);
    }
    
    /**
     * Removes a comment from the site.
     * 
     * @param commentId The ID of the comment to remove
     * @return true if comment was removed successfully, false otherwise
     */
    public boolean removeComment(String commentId) {
        if (commentId == null || commentId.trim().isEmpty()) {
            return false;
        }
        
        Comment removedComment = comments.remove(commentId);
        if (removedComment != null) {
            totalComments--;
            return true;
        }
        
        return false;
    }
    
    /**
     * Gets the site's unique identifier.
     * 
     * @return Site ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Gets the site's name.
     * 
     * @return Site name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the site's description.
     * 
     * @return Site description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the total number of comments on this site.
     * 
     * @return Total comments count
     */
    public int getTotalComments() {
        return totalComments;
    }
    
    /**
     * Gets the average rating of the site.
     * 
     * @return Average rating
     */
    public double getAverageRating() {
        return averageRating;
    }
    
    /**
     * Updates the average rating of the site.
     * This method would typically be called when new feedback is added.
     * 
     * @param newRating The new rating to include in the average
     */
    public void updateAverageRating(double newRating) {
        // In a real implementation, this would recalculate the average
        // based on all ratings. For simplicity, we'll just update it.
        this.averageRating = newRating;
    }
    
    /**
     * Creates a string representation of the site.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "Site{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", totalComments=" + totalComments +
                ", averageRating=" + averageRating +
                '}';
    }
    
    /**
     * Checks if this site is equal to another object.
     * Sites are considered equal if they have the same ID.
     * 
     * @param o The object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(id, site.id);
    }
    
    /**
     * Generates a hash code based on the site ID.
     * 
     * @return Hash code of the site
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}