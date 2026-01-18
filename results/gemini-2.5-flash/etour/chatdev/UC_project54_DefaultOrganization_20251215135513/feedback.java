/**
 * A simple POJO (Plain Old Java Object) for representing a Feedback entry
 * given by a tourist for a specific site.
 */
public class Feedback {
    private int id;
    private int siteId;
    private String touristName;
    private int rating; // e.g., 1-5 stars
    private String comment;
    /**
     * Constructor for Feedback.
     * @param id Unique identifier for the feedback.
     * @param siteId ID of the site this feedback is for.
     * @param touristName Name of the tourist who issued the feedback.
     * @param rating Rating given (e.g., 1 to 5).
     * @param comment The textual comment.
     */
    public Feedback(int id, int siteId, String touristName, int rating, String comment) {
        this.id = id;
        this.siteId = siteId;
        this.touristName = touristName;
        this.rating = rating;
        this.comment = comment;
    }
    // --- Getters ---
    public int getId() {
        return id;
    }
    public int getSiteId() {
        return siteId;
    }
    public String getTouristName() {
        return touristName;
    }
    public int getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }
    // --- Setters (for updating comment) ---
    public void setId(int id) {
        this.id = id;
    }
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Allows setting a new comment, crucial for the "MODIFICACOMMENTO" use case.
     * @param comment The new comment string.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Feedback{" +
               "id=" + id +
               ", siteId=" + siteId +
               ", touristName='" + touristName + '\'' +
               ", rating=" + rating +
               ", comment='" + comment + '\'' +
               '}';
    }
}