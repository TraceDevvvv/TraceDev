/**
 * Report Service Class
 * Handles business logic for generating statistical reports
 * Implements Step 4: Upload site feedback obtained by activating the SearchSite use case
 */
import java.util.*;
import java.util.stream.Collectors;
public class ReportService {
    /**
     * Retrieves all available locations from the system
     * Step 2: Upload the list of places in the system
     */
    public List<Location> getAllLocations() throws Exception {
        try {
            // Simulate network connection
            Thread.sleep(500); // Simulate network delay
            MockDataService dataService = new MockDataService();
            return dataService.getAllLocations();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Connection interrupted while fetching locations");
        } catch (Exception e) {
            throw new Exception("Failed to retrieve locations: " + e.getMessage());
        }
    }
    /**
     * Generates a comprehensive statistical report for the selected location
     * Step 4: Upload site feedback obtained by activating the SearchSite use case for the location selected
     */
    public ReportData generateStatisticalReport(String locationName) throws Exception {
        List<SiteFeedback> feedbackData = null;
        try {
            // Step 4: Activate the SearchSite use case
            SearchSite searchSite = new SearchSite();
            feedbackData = searchSite.searchForSiteFeedback(locationName);
            if (feedbackData == null || feedbackData.isEmpty()) {
                throw new Exception("No feedback data available for location: " + locationName);
            }
            // Prepare statistical calculations from the search results
            Map<String, Object> statistics = calculateStatistics(feedbackData, locationName);
            return new ReportData(locationName, statistics, feedbackData);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("Connection to server interrupted")) {
                throw new Exception("Connection interrupted while generating report: " + e.getMessage());
            }
            throw new Exception("Report generation failed: " + e.getMessage());
        }
    }
    /**
     * Calculates various statistics from the feedback data
     */
    private Map<String, Object> calculateStatistics(List<SiteFeedback> feedbackData, String locationName) {
        Map<String, Object> statistics = new LinkedHashMap<>();
        // Basic counts
        int totalEntries = feedbackData.size();
        statistics.put("Total Feedback Entries", totalEntries);
        // Rating statistics
        double averageRating = feedbackData.stream()
                .mapToDouble(SiteFeedback::getRating)
                .average()
                .orElse(0.0);
        statistics.put("Average Rating", String.format("%.2f / 5.0", averageRating));
        double minRating = feedbackData.stream()
                .mapToDouble(SiteFeedback::getRating)
                .min()
                .orElse(0.0);
        statistics.put("Minimum Rating", String.format("%.1f / 5.0", minRating));
        double maxRating = feedbackData.stream()
                .mapToDouble(SiteFeedback::getRating)
                .max()
                .orElse(0.0);
        statistics.put("Maximum Rating", String.format("%.1f / 5.0", maxRating));
        // Visitor statistics
        int totalVisitors = feedbackData.stream()
                .mapToInt(SiteFeedback::getVisitorCount)
                .sum();
        statistics.put("Total Visitors", totalVisitors);
        double averageVisitors = feedbackData.stream()
                .mapToInt(SiteFeedback::getVisitorCount)
                .average()
                .orElse(0.0);
        statistics.put("Average Daily Visitors", String.format("%.1f", averageVisitors));
        // Date range
        String oldestDate = feedbackData.stream()
                .map(SiteFeedback::getDate)
                .min(String::compareTo)
                .orElse("N/A");
        String newestDate = feedbackData.stream()
                .map(SiteFeedback::getDate)
                .max(String::compareTo)
                .orElse("N/A");
        statistics.put("Data Range", oldestDate + " to " + newestDate);
        // Rating distribution
        Map<Double, Long> ratingDistribution = feedbackData.stream()
                .collect(Collectors.groupingBy(
                    SiteFeedback::getRating,
                    Collectors.counting()
                ));
        StringBuilder distribution = new StringBuilder();
        ratingDistribution.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    distribution.append(entry.getKey())
                            .append(" stars: ")
                            .append(entry.getValue())
                            .append(" | ");
                });
        if (distribution.length() > 3) {
            distribution.setLength(distribution.length() - 3);
        }
        statistics.put("Rating Distribution", distribution.toString());
        return statistics;
    }
}