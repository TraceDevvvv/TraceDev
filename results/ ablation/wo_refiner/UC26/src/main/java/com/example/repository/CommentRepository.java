package com.example.repository;

import com.example.entities.Comment;
import java.util.Optional;

/**
 * Repository interface for Comment entities.
 */
public interface CommentRepository {
    Optional<Comment> findById(int commentId);
    boolean update(Comment comment);
}