package com.example.external;

import com.example.entities.Comment;

/**
 * Interface for the external ETOUR server data source.
 * Simulates communication with an external server.
 */
public interface ETOURServerDataSource {
    /**
     * Fetches a comment from the server by its ID.
     *
     * @param commentId the comment ID
     * @return the Comment object (null if not found)
     * @throws RuntimeException if connection fails
     */
    Comment fetchComment(String commentId);

    /**
     * Updates a comment on the server.
     *
     * @param comment the comment to update
     * @return true if update succeeded, false otherwise
     * @throws RuntimeException if connection fails
     */
    boolean updateComment(Comment comment);
}