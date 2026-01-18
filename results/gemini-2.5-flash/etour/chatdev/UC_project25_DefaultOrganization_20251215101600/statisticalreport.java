/**
 * Represents a statistical report for a specific location.
 * This class holds aggregated data from feedback or site searches.
 */
public class StatisticalReport {
    private int locationId;
    private String locationName;
    private double averageRating;
    private int totalFeedbackCount;
    private String mostCommonFeedbackType; // e.g., "Positive", "Negative", "Neutral"
    /**
     * Constructs a new StatisticalReport object.
     * @param locationId The ID of the location for which the report is generated.
     * @param locationName The name of the location.
     * @param averageRating The average rating for the location.
     * @param totalFeedbackCount The total number of feedback entries.
     * @param mostCommonFeedbackType The most frequent type of feedback received.
     */
    public StatisticalReport(int locationId, String locationName, double averageRating,
                             int totalFeedbackCount, String mostCommonFeedbackType) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.totalFeedbackCount = totalFeedbackCount;
        this.mostCommonFeedbackType = mostCommonFeedbackType;
    }
    /**
     * Gets the ID of the reported location.
     * @return The location ID.
     */
    public int getLocationId() {
        return locationId;
    }
    /**
     * Gets the name of the reported location.
     * @return The location name.
     */
    public String getLocationName() {
        return locationName;
    }
    /**
     * Gets the average rating of the location.
     * @return The average rating.
     */
    public double getAverageRating() {
        return averageRating;
    }
    /**
     * Gets the total count of feedback entries.
     * @return The total feedback count.
     */
    public int getTotalFeedbackCount() {
        return totalFeedbackCount;
    }
    /**
     * Gets the most common feedback type.
     * @return The most common feedback type string.
     */
    public String getMostCommonFeedbackType() {
        return mostCommonFeedbackType;
    }
    /**
     * Provides a formatted string representation of the statistical report.
     * @return A string containing report details.
     */
    @Override
    public String toString() {
        return String.format(
            "--- Statistical Report for %s (ID: %d) ---\n" +
            "Average Rating: %.2f / 5.0\n" +
            "Total Feedback Entries: %d\n" +
            "Most Common Feedback Type: %s\n" +
            "-------------------------------------------",
            locationName, locationId, averageRating, totalFeedbackCount, mostCommonFeedbackType
        );
    }
}