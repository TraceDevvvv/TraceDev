'''
Represents a Feedback entry associated with a specific site.
Includes a unique ID, the site ID it belongs to, and the comment text.
'''
package com.chatdev.models;
public class Feedback {
    private int id;
    private int siteId;
    private String comment;
    /**
     * Constructs a new Feedback object.
     * @param id The unique identifier of the feedback.
     * @param siteId The ID of the site this feedback is associated with.
     * @param comment The comment text of the feedback.
     */
    public Feedback(int id, int siteId, String comment) {
        this.id = id;
        this.siteId = siteId;
        this.comment = comment;
    }
    /**
     * Gets the ID of the feedback.
     * @return The feedback's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the feedback.
     * @param id The new ID for the feedback.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets the site ID this feedback belongs to.
     * @return The site ID.
     */
    public int getSiteId() {
        return siteId;
    }
    /**
     * Sets the site ID for this feedback.
     * @param siteId The new site ID.
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
    /**
     * Gets the comment text of the feedback.
     * @return The comment text.
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets the comment text of the feedback.
     * @param comment The new comment text.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Returns a string representation of the Feedback object, primarily its comment.
     * This is useful for displaying Feedback objects in UI components like JList.
     * @return A string representing the feedback.
     */
    @Override
    public String toString() {
        return "Feedback ID: " + id + ", Comment: \"" + comment + "\"";
    }
}