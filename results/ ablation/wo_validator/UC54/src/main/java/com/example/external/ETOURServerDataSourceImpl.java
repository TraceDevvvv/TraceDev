
package com.example.external;

import com.example.entities.Comment;

/**
 * A mock implementation of the ETOUR server data source for demonstration.
 * In a real application, this would make actual network calls.
 */
public class ETOURServerDataSourceImpl implements ETOURServerDataSource {
    /**
     * Simulates fetching a comment from the server.
     * For demonstration, returns a mock comment if the ID is "comment123", otherwise null.
     * Throws a RuntimeException if the commentId is "connectionError".
     *
     * @param commentId the comment ID
     * @return the Comment or null
     */
    @Override
    public Comment fetchComment(String commentId) {
        // Simulate connection error
        if ("connectionError".equals(commentId)) {
            throw new RuntimeException("Server connection failed");
        }
        // Simulate found comment
        if ("comment123".equals(commentId)) {
            return new Comment(commentId, "tourist456", "site789", "Original comment content");
        }
        return null;
    }

    @Override
    public boolean updateComment(Comment comment) {
        // Mock implementation
        return true;
    }
}
