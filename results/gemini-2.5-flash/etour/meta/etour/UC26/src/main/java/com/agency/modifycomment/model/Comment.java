package com.agency.modifycomment.model;

import java.util.Objects;

/**
 * Represents a Comment in the system.
 * In this use case, a Comment is directly associated with a Feedback.
 * This class is primarily used to encapsulate the comment text itself,
 * which can be modified.
 */
public class Comment {
    private String id; // Unique ID for the comment, though often tied to Feedback ID
    private String feedbackId; // The ID of the feedback this comment belongs to
    private String text; // The actual comment text

    /**
     * Constructs a new Comment with the specified ID, feedback ID, and text.
     *
     * @param id         The unique identifier of the comment.
     * @param feedbackId The ID of the feedback this comment is associated with.
     * @param text       The text content of the comment.
     */
    public Comment(String id, String feedbackId, String text) {
        this.id = id;
        this.feedbackId = feedbackId;
        this.text = text;
    }

    /**
     * Returns the ID of the comment.
     *
     * @return The comment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     *
     * @param id The new comment ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the feedback this comment belongs to.
     *
     * @return The feedback ID.
     */
    public String getFeedbackId() {
        return feedbackId;
    }

    /**
     * Sets the ID of the feedback this comment belongs to.
     *
     * @param feedbackId The new feedback ID.
     */
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Returns the text content of the comment.
     *
     * @return The comment text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text content of the comment.
     *
     * @param text The new comment text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Comment objects are considered equal if their IDs are the same.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the comment's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the Comment object.
     *
     * @return A string containing the comment's ID, feedback ID, and text.
     */
    @Override
    public String toString() {
        return "Comment{" +
               "id='" + id + '\'' +
               ", feedbackId='" + feedbackId + '\'' +
               ", text='" + text + '\'' +
               '}';
    }
}