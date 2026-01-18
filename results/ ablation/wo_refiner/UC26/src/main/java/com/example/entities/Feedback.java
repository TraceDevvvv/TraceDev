package com.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a feedback entity containing comments.
 */
public class Feedback {
    private int id;
    private int siteId;
    private String title;
    private List<Comment> comments;

    public Feedback(int id, int siteId, String title) {
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Optional<Comment> findCommentById(int commentId) {
        return comments.stream()
                .filter(c -> c.getId() == commentId)
                .findFirst();
    }
}