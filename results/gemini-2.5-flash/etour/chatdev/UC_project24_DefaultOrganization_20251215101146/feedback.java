/*
 * Represents a single piece of feedback for a site, including its details.
 */
package com.chatdev.model;
import java.time.LocalDate;
public class Feedback {
    private String siteId;
    private String author;
    private int rating; // e.g., 1-5 stars
    private String comment;
    private LocalDate feedbackDate;
    /**
     * Constructs a new Feedback object.
     * @param siteId The ID of the site this feedback belongs to.
     * @param author The author of the feedback.
     * @param rating The rating given (e.g., 1 to 5).
     * @param comment The textual comment.
     * @param feedbackDate The date the feedback was submitted.
     */
    public Feedback(String siteId, String author, int rating, String comment, LocalDate feedbackDate) {
        this.siteId = siteId;
        this.author = author;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    }
    /**
     * Returns the site ID associated with this feedback.
     * @return The site ID.
     */
    public String getSiteId() {
        return siteId;
    }
    /**
     * Sets the site ID for this feedback.
     * @param siteId The new site ID.
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    /**
     * Returns the author of the feedback.
     * @return The feedback author.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Sets the author of the feedback.
     * @param author The new author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Returns the rating given in the feedback.
     * @return The rating.
     */
    public int getRating() {
        return rating;
    }
    /**
     * Sets the rating for the feedback.
     * @param rating The new rating.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Returns the comment provided in the feedback.
     * @return The feedback comment.
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets the comment for the feedback.
     * @param comment The new comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Returns the date the feedback was submitted.
     * @return The feedback date.
     */
    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }
    /**
     * Sets the date for the feedback.
     * @param feedbackDate The new feedback date.
     */
    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
    /**
     * Provides a formatted string representation of the Feedback object.
     * The final newline character is omitted to allow the GUI to add appropriate line separators.
     * @return A formatted string with feedback details.
     */
    @Override
    public String toString() {
        return String.format("Date: %s\nAuthor: %s\nRating: %d/5\nComment: \"%s\"\n--------------------",
                feedbackDate, author, rating, comment);
    }
}