'''
Represents a feedback entry with tourist, site, vote, and comment.
Includes validation methods to ensure data integrity.
'''
import java.util.Date;
public class Feedback {
    private int id;
    private int touristId;
    private int siteId;
    private int vote;
    private String comment;
    private Date timestamp;
    public Feedback(int id, int touristId, int siteId, int vote, String comment) {
        // Validate input parameters
        if (id <= 0) {
            throw new IllegalArgumentException("Feedback ID must be positive");
        }
        if (touristId <= 0) {
            throw new IllegalArgumentException("Tourist ID must be positive");
        }
        if (siteId <= 0) {
            throw new IllegalArgumentException("Site ID must be positive");
        }
        if (vote < 1 || vote > 5) {
            throw new IllegalArgumentException("Vote must be between 1 and 5");
        }
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be null or empty");
        }
        if (comment.length() < 10 || comment.length() > 500) {
            throw new IllegalArgumentException("Comment must be between 10 and 500 characters");
        }
        this.id = id;
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment.trim();
        this.timestamp = new Date(); // Current timestamp
    }
    // Getters
    public int getId() { return id; }
    public int getTouristId() { return touristId; }
    public int getSiteId() { return siteId; }
    public int getVote() { return vote; }
    public String getComment() { return comment; }
    public Date getTimestamp() { return timestamp; }
    /**
     * Returns a string representation of the feedback for debugging
     */
    @Override
    public String toString() {
        return String.format("Feedback{id=%d, touristId=%d, siteId=%d, vote=%d, comment='%s', timestamp=%s}",
            id, touristId, siteId, vote, comment, timestamp);
    }
    /**
     * Validates if this feedback meets all business rules
     */
    public boolean isValid() {
        return id > 0 && touristId > 0 && siteId > 0 && 
               vote >= 1 && vote <= 5 && 
               comment != null && !comment.trim().isEmpty() &&
               comment.length() >= 10 && comment.length() <= 500;
    }
}