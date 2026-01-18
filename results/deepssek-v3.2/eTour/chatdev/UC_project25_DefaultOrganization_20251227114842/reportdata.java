/**
 * Report Data Transfer Object
 * Holds statistical data and feedback for a location to be displayed in UI
 */
import java.util.List;
import java.util.Map;
public class ReportData {
    private String locationName;
    private Map<String, Object> statistics;
    private List<SiteFeedback> feedbackData;
    public ReportData(String locationName, Map<String, Object> statistics, List<SiteFeedback> feedbackData) {
        if (locationName == null || locationName.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty");
        }
        if (statistics == null) {
            throw new IllegalArgumentException("Statistics cannot be null");
        }
        if (feedbackData == null) {
            throw new IllegalArgumentException("Feedback data cannot be null");
        }
        this.locationName = locationName;
        this.statistics = statistics;
        this.feedbackData = feedbackData;
    }
    // Getters
    public String getLocationName() {
        return locationName;
    }
    public Map<String, Object> getStatistics() {
        return statistics;
    }
    public List<SiteFeedback> getFeedbackData() {
        return feedbackData;
    }
}