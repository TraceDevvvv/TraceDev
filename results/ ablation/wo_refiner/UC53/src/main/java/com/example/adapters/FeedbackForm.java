package com.example.adapters;

/**
 * Interface for feedback form data (interface adapters layer).
 */
public interface FeedbackForm {
    String getSiteId();
    int getVote();
    String getComment();
}