/**
 * Comment class represents a comment entity with its properties.
 * This could be expanded with more fields like timestamp, rating, etc.
 */
public class Comment {
    private String touristId;
    private String siteId;
    private String text;
    private java.time.LocalDateTime timestamp;
    public Comment(String touristId, String siteId, String text) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.text = text;
        this.timestamp = java.time.LocalDateTime.now();
    }
    // Getters and setters
    public String getTouristId() { return touristId; }
    public String getSiteId() { return siteId; }
    public String getText() { return text; }
    public java.time.LocalDateTime getTimestamp() { return timestamp; }
    public void setText(String newText) {
        this.text = newText;
        this.timestamp = java.time.LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Comment{touristId='" + touristId + "', siteId='" + siteId + 
               "', text='" + text + "', timestamp=" + timestamp + "}";
    }
}