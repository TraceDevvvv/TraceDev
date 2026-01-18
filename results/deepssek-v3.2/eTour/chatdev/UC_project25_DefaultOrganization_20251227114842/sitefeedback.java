/**
 * Site Feedback Entity Class
 * Represents feedback data collected from a specific site/location
 */
public class SiteFeedback {
    private String locationId;
    private String date;
    private double rating; // 1-5 scale
    private String comments;
    private int visitorCount;
    public SiteFeedback(String locationId, String date, double rating, String comments, int visitorCount) {
        this.locationId = locationId;
        this.date = date;
        this.rating = rating;
        this.comments = comments;
        this.visitorCount = visitorCount;
        // Validate data ranges
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        if (visitorCount < 0) {
            throw new IllegalArgumentException("Visitor count cannot be negative");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
    }
    // Getters and Setters
    public String getLocationId() { return locationId; }
    public void setLocationId(String locationId) { this.locationId = locationId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public double getRating() { return rating; }
    public void setRating(double rating) { 
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        this.rating = rating; 
    }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public int getVisitorCount() { return visitorCount; }
    public void setVisitorCount(int visitorCount) { 
        if (visitorCount < 0) {
            throw new IllegalArgumentException("Visitor count cannot be negative");
        }
        this.visitorCount = visitorCount; 
    }
    @Override
    public String toString() {
        return "Feedback{" +
                "date='" + date + '\'' +
                ", rating=" + rating +
                ", visitors=" + visitorCount +
                '}';
    }
}