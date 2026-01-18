import java.util.Date;

public class Feedback {
    private String feedbackId;
    private String siteId;
    private String comment;
    private int rating;
    private Date timestamp;
    
    public Feedback(String feedbackId, String siteId, String comment, int rating, Date timestamp) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
        this.timestamp = timestamp;
    }
    
    public String getFeedbackId() {
        return feedbackId;
    }
    
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
    
    public String getSiteId() {
        return siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}