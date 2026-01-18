package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of CommentRepository using an in‑memory map.
 */
public class CommentRepositoryImpl implements CommentRepository {
    // Simulating a data source with a map
    private Map<String, Comment> dataSource = new HashMap<>();

    @Override
    public Comment findById(String id) {
        System.out.println("Repository: finding comment with ID: " + id);
        return dataSource.get(id);
    }

    @Override
    public Comment save(Comment comment) {
        System.out.println("Repository: saving comment with ID: " + comment.getId());
        dataSource.put(comment.getId(), comment);
        return comment;
    }
}