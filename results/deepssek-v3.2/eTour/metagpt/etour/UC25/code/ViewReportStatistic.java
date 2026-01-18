import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * ViewReportStatistic - A program to view statistical reports for selected locations.
 * This program simulates the use case where an agency operator views statistical reports
 * for locations in the system.
 * 
 * Key components:
 * 1. Location management and selection
 * 2. Site feedback retrieval (simulating SearchSite use case)
 * 3. Statistical report generation
 * 4. Error handling for server connection interruptions
 */
public class ViewReportStatistic {
    
    /**
     * Represents a location in the system with ID and name
     */
    static class Location {
        private final String id;
        private final String name;
        
        public Location(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        
        @Override
        public String toString() {
            return name + " (ID: " + id + ")";
        }
    }
    
    /**
     * Represents site feedback for a location
     */
    static class SiteFeedback {
        private final String locationId;
        private final int rating; // 1-5 scale
        private final String comment;
        private final Date timestamp;
        
        public SiteFeedback(String locationId, int rating, String comment) {
            this.locationId = locationId;
            this.rating = Math.max(1, Math.min(5, rating)); // Ensure rating is within 1-5
            this.comment = comment;
            this.timestamp = new Date();
        }
        
        public String getLocationId() { return locationId; }
        public int getRating() { return rating; }
        public String getComment() { return comment; }
        public Date getTimestamp() { return timestamp; }
    }
    
    /**
     * Represents a statistical report for a location
     */
    static class StatisticalReport {
        private final String locationId;
        private final String locationName;
        private final int totalFeedbacks;
        private final double averageRating;
        private final int maxRating;
        private final int minRating;
        private final List<String> sampleComments;
        
        public StatisticalReport(String locationId, String locationName, 
                                int totalFeedbacks, double averageRating,
                                int maxRating, int minRating, List<String> sampleComments) {
            this.locationId = locationId;
            this.locationName = locationName;
            this.totalFeedbacks = totalFeedbacks;
            this.averageRating = averageRating;
            this.maxRating = maxRating;
            this.minRating = minRating;
            this.sampleComments = sampleComments;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n=== Statistical Report ===\n");
            sb.append("Location: ").append(locationName).append(" (ID: ").append(locationId).append(")\n");
            sb.append("Total Feedbacks: ").append(totalFeedbacks).append("\n");
            sb.append("Average Rating: ").append(String.format("%.2f", averageRating)).append("/5.0\n");
            sb.append("Maximum Rating: ").append(maxRating).append("/5\n");
            sb.append("Minimum Rating: ").append(minRating).append("/5\n");
            sb.append("Sample Comments (").append(sampleComments.size()).append("):\n");
            for (int i = 0; i < sampleComments.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(sampleComments.get(i)).append("\n");
            }
            sb.append("==========================\n");
            return sb.toString();
        }
    }
    
    /**
     * Simulates a database of locations in the system
     */
    static class LocationDatabase {
        private static final List<Location> locations = Arrays.asList(
            new Location("L001", "Downtown Plaza"),
            new Location("L002", "Riverside Park"),
            new Location("L003", "City Mall"),
            new Location("L004", "Central Library"),
            new Location("L005", "Sports Complex")
        );
        
        public static List<Location> getAllLocations() {
            return new ArrayList<>(locations);
        }
        
        public static Location getLocationById(String id) {
            return locations.stream()
                .filter(loc -> loc.getId().equals(id))
                .findFirst()
                .orElse(null);
        }
    }
    
    /**
     * Simulates the SearchSite use case to retrieve site feedback for a location
     * This includes simulated server connection handling
     */
    static class SiteFeedbackService {
        private static final Random random = new Random();
        
        /**
         * Simulates retrieving site feedback with potential server connection issues
         * @param locationId The location ID to search for
         * @return List of site feedback for the location
         * @throws IOException Simulates server connection interruption
         */
        public static List<SiteFeedback> getFeedbackForLocation(String locationId) throws IOException {
            // Simulate potential server connection interruption (10% chance)
            if (random.nextDouble() < 0.1) {
                throw new IOException("Connection to server interrupted. Please try again.");
            }
            
            // Simulate network delay
            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Generate simulated feedback data
            List<SiteFeedback> feedbacks = new ArrayList<>();
            int numFeedbacks = 10 + random.nextInt(20); // 10-30 feedbacks
            
            String[] sampleComments = {
                "Great place, well maintained",
                "Needs improvement in cleanliness",
                "Excellent facilities",
                "Too crowded on weekends",
                "Good for families",
                "Parking is limited",
                "Beautiful scenery",
                "Needs more seating areas",
                "Very accessible location",
                "Could use better signage"
            };
            
            for (int i = 0; i < numFeedbacks; i++) {
                int rating = 1 + random.nextInt(5);
                String comment = sampleComments[random.nextInt(sampleComments.length)];
                feedbacks.add(new SiteFeedback(locationId, rating, comment));
            }
            
            return feedbacks;
        }
    }
    
    /**
     * Generates statistical report from site feedback data
     */
    static class ReportGenerator {
        public static StatisticalReport generateReport(String locationId, List<SiteFeedback> feedbacks) {
            if (feedbacks.isEmpty()) {
                return new StatisticalReport(locationId, 
                    LocationDatabase.getLocationById(locationId).getName(),
                    0, 0.0, 0, 0, Arrays.asList("No feedback available"));
            }
            
            int total = feedbacks.size();
            double sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            List<String> comments = new ArrayList<>();
            
            for (SiteFeedback feedback : feedbacks) {
                int rating = feedback.getRating();
                sum += rating;
                max = Math.max(max, rating);
                min = Math.min(min, rating);
                
                // Collect up to 5 sample comments
                if (comments.size() < 5 && !feedback.getComment().isEmpty()) {
                    comments.add(feedback.getComment());
                }
            }
            
            double average = sum / total;
            
            return new StatisticalReport(locationId,
                LocationDatabase.getLocationById(locationId).getName(),
                total, average, max, min, comments);
        }
    }
    
    /**
     * Main program flow simulating the use case steps
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== View Report Statistic System ===\n");
        
        // Step 1: Activate the feature on the statistical report
        System.out.println("1. Statistical report feature activated.");
        
        // Step 2: Upload the list of places and display them in a form
        System.out.println("\n2. Available locations:");
        List<Location> locations = LocationDatabase.getAllLocations();
        
        for (int i = 0; i < locations.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + locations.get(i));
        }
        
        // Step 3: Select a location and submit the form
        System.out.print("\n3. Select a location (1-" + locations.size() + "): ");
        
        Location selectedLocation = null;
        while (selectedLocation == null) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= locations.size()) {
                    selectedLocation = locations.get(choice - 1);
                    System.out.println("   Selected: " + selectedLocation.getName());
                } else {
                    System.out.print("   Invalid choice. Please enter 1-" + locations.size() + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("   Please enter a valid number: ");
            }
        }
        
        // Step 4: Upload midsize site feedback and prepare statistical report
        System.out.println("\n4. Retrieving site feedback for " + selectedLocation.getName() + "...");
        
        StatisticalReport report = null;
        int retryCount = 0;
        final int MAX_RETRIES = 3;
        
        while (report == null && retryCount < MAX_RETRIES) {
            try {
                List<SiteFeedback> feedbacks = SiteFeedbackService.getFeedbackForLocation(selectedLocation.getId());
                report = ReportGenerator.generateReport(selectedLocation.getId(), feedbacks);
                
            } catch (IOException e) {
                retryCount++;
                System.out.println("   Error: " + e.getMessage());
                
                if (retryCount < MAX_RETRIES) {
                    System.out.println("   Retrying... (Attempt " + (retryCount + 1) + " of " + MAX_RETRIES + ")");
                    try {
                        Thread.sleep(1000); // Wait before retry
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("   Failed to retrieve data after " + MAX_RETRIES + " attempts.");
                    // Create an error report
                    report = new StatisticalReport(selectedLocation.getId(),
                        selectedLocation.getName(),
                        0, 0.0, 0, 0,
                        Arrays.asList("Unable to retrieve data due to server connection issues"));
                }
            }
        }
        
        // Exit condition: Display the statistical report
        if (report != null) {
            System.out.println("\n" + report);
        }
        
        System.out.println("\n=== Report generation complete ===");
        scanner.close();
    }
}