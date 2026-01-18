// Data model class representing feedback information.
// Stores details about feedback submitted by users for specific sites.
package feedbacksystem;
import java.util.Date;
/**
 * Data model class representing feedback information.
 * Stores details about feedback submitted by users for specific sites.
 */
public class FeedbackData {
    private String siteName;
    private String userId;
    private String feedbackText;
    private Date submissionDate;
    private int rating;
    public FeedbackData(String siteName, String userId, String feedbackText, 
                       Date submissionDate, int rating) {
        this.siteName = siteName;
        this.userId = userId;
        this.feedbackText = feedbackText;
        this.submissionDate = submissionDate;
        this.rating = rating;
    }
    public String getSiteName() {
        return siteName;
    }
    public String getUserId() {
        return userId;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    public Date getSubmissionDate() {
        return submissionDate;
    }
    public int getRating() {
        return rating;
    }
    @Override
    public String toString() {
        return String.format("Feedback for %s from user %s (Rating: %d)", 
            siteName, userId, rating);
    }
}