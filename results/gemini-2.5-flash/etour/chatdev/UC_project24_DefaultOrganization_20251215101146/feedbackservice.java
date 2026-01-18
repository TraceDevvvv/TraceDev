/*
 * Provides simulated data access for feedback.
 * In a real application, this would interact with a database or external API.
 */
package com.chatdev.service;
import com.chatdev.model.Feedback;
import com.chatdev.exception.ConnectionException; // Import common ConnectionException
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class FeedbackService {
    // Simulated database/data store for feedback
    private static List<Feedback> allFeedback;
    /**
     * Static initializer to populate some dummy feedback data.
     */
    static {
        allFeedback = new ArrayList<>(Arrays.asList(
                new Feedback("SITE001", "John Doe", 5, "Excellent facilities and friendly staff.", LocalDate.of(2023, 10, 26)),
                new Feedback("SITE001", "Jane Smith", 4, "Good location, but parking can be tricky.", LocalDate.of(2023, 11, 15)),
                new Feedback("SITE001", "Peter Jones", 5, "Always a pleasure to work here. Very productive environment.", LocalDate.of(2024, 1, 3)),
                new Feedback("SITE002", "Alice Brown", 3, "The internet connection was a bit unstable at times.", LocalDate.of(2023, 9, 10)),
                new Feedback("SITE002", "Bob White", 4, "Quiet and comfortable, great for focused work.", LocalDate.of(2023, 12, 1)),
                new Feedback("SITE003", "Charlie Green", 5, "Training materials were top-notch and instructors knowledgeable.", LocalDate.of(2024, 2, 20)),
                new Feedback("SITE003", "Diana Prince", 4, "Could use more modern equipment for practical sessions.", LocalDate.of(2024, 2, 25))
                // No feedback for SITE004 deliberately to test "no feedback" case
        ));
    }
    /**
     * Retrieves all feedback for a specified site ID.
     * This simulates fetching feedback after a site has been selected.
     * @param siteId The ID of the site for which to retrieve feedback.
     * @return A list of Feedback objects pertaining to the site.
     * @throws ConnectionException If a simulated server connection fails.
     */
    public List<Feedback> getFeedbackForSite(String siteId) throws ConnectionException {
        // Simulate potential connection issues with a small chance
        if (Math.random() < 0.15) { // 15% chance of failure
            throw new ConnectionException("Simulated connection interruption during feedback retrieval.");
        }
        // Filter feedback by site ID
        return allFeedback.stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }
}