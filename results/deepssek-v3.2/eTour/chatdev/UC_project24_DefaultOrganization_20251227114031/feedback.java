/**
 * Data class representing a Feedback entry.
 */
public class Feedback {
    private int siteId;
    private String comment;
    private int rating; // 1 to 5
    public Feedback(int siteId, String comment, int rating) {
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
    }
    public int getSiteId() {
        return siteId;
    }
    public String getComment() {
        return comment;
    }
    public int getRating() {
        return rating;
    }
}