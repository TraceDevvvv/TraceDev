package com.etour.repository;

import com.etour.domain.Comment;
import com.etour.exception.ETOURConnectionException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of CommentRepository.
 * For simplicity, uses an in‑memory map; in reality would use DataSource.
 */
public class CommentRepositoryImpl implements CommentRepository {
    private DataSource dataSource; // Not used in this stub
    private Map<String, Comment> commentStore = new HashMap<>();

    public CommentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize with a sample comment for demonstration
        commentStore.put("comment-1", new Comment("comment-1", "Old comment",
                java.time.LocalDateTime.now().minusDays(1), "tourist-123", "site-1"));
    }

    @Override
    public Optional<Comment> findById(String id) throws ETOURConnectionException {
        // Simulate a connection loss randomly for demonstration of REQ-EXIT-002
        if (Math.random() < 0.1) {
            throw new ETOURConnectionException("ETOUR server error", "CONN-001", "ETOUR");
        }
        return Optional.ofNullable(commentStore.get(id));
    }

    @Override
    public Comment save(Comment comment) {
        commentStore.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        commentStore.put(comment.getId(), comment);
        return comment;
    }
}