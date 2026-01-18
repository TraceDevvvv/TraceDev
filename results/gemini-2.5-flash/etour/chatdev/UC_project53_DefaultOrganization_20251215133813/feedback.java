'''
Represents a piece of feedback given by a tourist for a specific site.
This class stores the vote, comment, and details of who gave the feedback and for which site.
'''
public class Feedback {
    private String touristId;
    private String siteId;
    private int vote;
    private String comment;
    private long timestamp; // Timestamp when the feedback was created
    /**
     * Constructs a new Feedback object.
     * @param touristId The unique identifier of the tourist who gave the feedback.
     * @param siteId The unique identifier of the site for which the feedback was given.
     * @param vote The numerical vote (e.g., 1-5 stars).
     * @param comment The textual comment provided by the tourist.
     */
    public Feedback(String touristId, String siteId, int vote, String comment) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
        this.timestamp = System.currentTimeMillis(); // Record creation time
    }
    // --- Getters ---
    /**
     * @return The ID of the tourist who issued this feedback.
     */
    public String getTouristId() {
        return touristId;
    }
    /**
     * @return The ID of the site receiving this feedback.
     */
    public String getSiteId() {
        return siteId;
    }
    /**
     * @return The vote given (e.g., 1-5).
     */
    public int getVote() {
        return vote;
    }
    /**
     * @return The comment text.
     */
    public String getComment() {
        return comment;
    }
    /**
     * @return The timestamp when the feedback was created.
     */
    public long getTimestamp() {
        return timestamp;
    }
    /**
     * Provides a string representation of the Feedback object.
     * @return A formatted string with feedback details.
     */
    @Override
    public String toString() {
        return "Feedback by " + touristId + " for " + siteId + ": Vote=" + vote + ", Comment='" + comment + "'";
    }
}