package com.example;

/**
 * Repository interface for Comment entities.
 */
public interface CommentRepository {
    Comment findById(String id);
    Comment save(Comment comment);
}