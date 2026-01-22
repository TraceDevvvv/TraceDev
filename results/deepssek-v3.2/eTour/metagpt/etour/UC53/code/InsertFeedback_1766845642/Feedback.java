import java.time.LocalDateTime;

/**
 * Feedback   ，          
 *     、  、   、  ID   ID   
 */
public class Feedback {
    private String feedbackId;
    private String touristId;
    private String siteId;
    private int rating; //   ，  1-5
    private String comment;
    private LocalDateTime timestamp;
    
    /**
     *     
     * @param feedbackId   ID
     * @param touristId   ID
     * @param siteId   ID
     * @param rating   （1-5）
     * @param comment   
     */
    public Feedback(String feedbackId, String touristId, String siteId, int rating, String comment) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     *       
     */
    public Feedback() {
        this.timestamp = LocalDateTime.now();
    }
    
    // Getter Setter  
    
    public String getFeedbackId() {
        return feedbackId;
    }
    
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
    
    public String getTouristId() {
        return touristId;
    }
    
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    
    public String getSiteId() {
        return siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     *           
     * @return      1-5        ，   true
     */
    public boolean isValid() {
        return rating >= 1 && rating <= 5 && comment != null && !comment.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId='" + feedbackId + '\'' +
                ", touristId='" + touristId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}