package com.etour.repository;

import com.etour.entity.Comment;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple in‑memory implementation of CommentRepository for demonstration.
 */
public class InMemoryCommentRepository implements CommentRepository {
    private final Map<Long, Comment> store = new HashMap<>();

    public InMemoryCommentRepository() {
        // Initialize with a sample comment for testing.
        Comment sample = new Comment(1L, 100L, 500L, "Original comment",
                LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1));
        store.put(1L, sample);
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }

    @Override
    public boolean update(Comment comment) {
        // Simulate a server call that could fail.
        if (Math.random() < 0.1) { // 10% chance of connection failure for demonstration.
            return false;
        }
        store.put(comment.getId(), comment);
        return true;
    }
}