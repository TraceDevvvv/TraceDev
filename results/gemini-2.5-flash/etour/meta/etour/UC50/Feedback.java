// Feedback.java
package com.etour.visitedsites;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a feedback entry given by a tourist for a specific site.
 * This class stores information about the feedback, including the site it refers to,
 * the tourist who gave it, the rating, and the comment.
 */
public class Feedback {
    private String feedbackId;
    private Site site;
    private String touristId; // Assuming touristId is sufficient to identify the tourist
    private int rating; // Rating from 1 to 5
    private String comment;
    private LocalDateTime feedbackDate;

    /**
     * Constructs a new Feedback object.
     *
     * @param feedbackId The unique identifier for the feedback.
     * @param site The site for which the feedback is given.
     * @param touristId The ID of the tourist who provided the feedback.
     * @param rating The rating given (e.g., 1-5 stars).
     * @param comment The textual comment provided by the tourist.
     * @throws IllegalArgumentException if any required parameter is null, empty, or invalid.
     */
    public Feedback(String feedbackId, Site site, String touristId, int rating, String comment) {
        // Validate inputs to ensure they are not null or empty and rating is within range
        if (feedbackId == null || feedbackId.trim().isEmpty()) {
            throw new IllegalArgumentException("Feedback ID cannot be null or empty.");
        }
        if (site == null) {
            throw new IllegalArgumentException("Site cannot be null.");
        }
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        // Comment can be empty, but not null
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null. Use an empty string if no comment.");
        }

        this.feedbackId = feedbackId;
        this.site = site;
        this.touristId = touristId;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = LocalDateTime.now(); // Automatically set feedback date to current time
    }

    /**
     * Returns the unique identifier of the feedback.
     *
     * @return The feedback ID.
     */
    public String getFeedbackId() {
        return feedbackId;
    }

    /**
     * Returns the site associated with this feedback.
     *
     * @return The Site object.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Returns the ID of the tourist who provided this feedback.
     *
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the rating given in the feedback.
     *
     * @return The rating (1-5).
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the comment provided in the feedback.
     *
     * @return The comment string.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the date and time when the feedback was created.
     *
     * @return The LocalDateTime object representing the feedback date.
     */
    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * Provides a string representation of the Feedback object.
     *
     * @return A string containing feedback details.
     */
    @Override
    public String toString() {
        return "Feedback ID: " + feedbackId +
               ", Site: [" + site.getName() + "]" +
               ", Tourist ID: " + touristId +
               ", Rating: " + rating +
               ", Comment: '" + comment + "'" +
               ", Date: " + feedbackDate;
    }

    /**
     * Compares this Feedback object to the specified object. The result is true if and only if
     * the argument is not null and is a Feedback object that represents the same feedback ID as this object.
     *
     * @param o The object to compare this Feedback against.
     * @return true if the given object represents a Feedback equivalent to this feedback, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedbackId.equals(feedback.feedbackId);
    }

    /**
     * Returns a hash code for this Feedback object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackId);
    }
}