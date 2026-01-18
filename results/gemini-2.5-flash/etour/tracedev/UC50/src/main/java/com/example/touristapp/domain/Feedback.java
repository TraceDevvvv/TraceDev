package com.example.touristapp.domain;

/**
 * Represents feedback given by a tourist for a specific site in the domain layer.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String touristId;
    private String comment;
    private int rating; // Assuming rating is an integer, e.g., 1-5

    /**
     * Constructs a new Feedback instance.
     *
     * @param id The unique identifier of the feedback entry.
     * @param siteId The ID of the site to which this feedback belongs.
     * @param touristId The ID of the tourist who provided this feedback.
     * @param comment The textual comment provided.
     * @param rating The rating given by the tourist (e.g., 1 to 5).
     */
    public Feedback(String id, String siteId, String touristId, String comment, int rating) {
        this.id = id;
        this.siteId = siteId;
        this.touristId = touristId;
        this.comment = comment;
        this.rating = rating;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    // Setters (if mutable)
    public void setId(String id) {
        this.id = id;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" +
               "id='" + id + '\'' +
               ", siteId='" + siteId + '\'' +
               ", touristId='" + touristId + '\'' +
               ", comment='" + comment + '\'' +
               ", rating=" + rating +
               '}';
    }
}