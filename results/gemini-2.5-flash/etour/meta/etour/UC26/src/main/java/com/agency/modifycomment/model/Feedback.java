package com.agency.modifycomment.model;

import java.util.Objects;

/**
 * Represents a Feedback in the system.
 * Each feedback is associated with a site and has a unique ID, a title, and a comment.
 */
public class Feedback {
    private String id;
    private String siteId; // The ID of the site this feedback belongs to
    private String title;
    private String comment;

    /**
     * Constructs a new Feedback with the specified ID, site ID, title, and comment.
     *
     * @param id     The unique identifier of the feedback.
     * @param siteId The ID of the site this feedback is associated with.
     * @param title  The title of the feedback.
     * @param comment The comment associated with the feedback.
     */
    public Feedback(String id, String siteId, String title, String comment) {
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.comment = comment;
    }

    /**
     * Returns the ID of the feedback.
     *
     * @return The feedback ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the feedback.
     *
     * @param id The new feedback ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the site this feedback belongs to.
     *
     * @return The site ID.
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the ID of the site this feedback belongs to.
     *
     * @param siteId The new site ID.
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * Returns the title of the feedback.
     *
     * @return The feedback title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the feedback.
     *
     * @param title The new feedback title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the comment of the feedback.
     *
     * @return The feedback comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the feedback.
     *
     * @param comment The new feedback comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Feedback objects are considered equal if their IDs are the same.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the feedback's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the Feedback object.
     *
     * @return A string containing the feedback's ID, site ID, title, and comment.
     */
    @Override
    public String toString() {
        return "Feedback{" +
               "id='" + id + '\'' +
               ", siteId='" + siteId + '\'' +
               ", title='" + title + '\'' +
               ", comment='" + comment + '\'' +
               '}';
    }
}