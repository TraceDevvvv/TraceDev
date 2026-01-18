package com.etour.repository;

import com.etour.entity.Comment;

/**
 * Repository interface for Comment data access.
 */
public interface CommentRepository {
    Comment findById(Long id);
    boolean update(Comment comment);
}