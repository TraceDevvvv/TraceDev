/**
 * Represents a tourist attraction site with details and feedback rating.
 */
class Site {
    private String name;
    private String location;
    private String visitDate;
    private int feedbackRating; // Rating from 1 to 5
    public Site(String name, String location, String visitDate, int feedbackRating) {
        this.name = name;
        this.location = location;
        this.visitDate = visitDate;
        this.feedbackRating = feedbackRating;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public String getVisitDate() {
        return visitDate;
    }
    public int getFeedbackRating() {
        return feedbackRating;
    }
    @Override
    public String toString() {
        return String.format("Site: %s, Location: %s, Visited on: %s, Feedback Rating: %d/5",
                name, location, visitDate, feedbackRating);
    }
}