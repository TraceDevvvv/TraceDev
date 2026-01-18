'''
Manages the collection of news items, simulating a database or backend service.
It provides methods to retrieve all news and delete a specific news item.
Includes a simulated server connection error for robustness testing.
'''
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom; // For simulating random events like connection errors
class NewsService {
    /**
     * Enumerates the possible outcomes of a news item deletion operation.
     * This provides more specific feedback than a simple boolean.
     */
    public enum DeletionResult {
        SUCCESS,                // News item was successfully deleted.
        NEWS_NOT_FOUND,         // News item with the given ID was not found.
        CONNECTION_INTERRUPTED  // Simulated server connection interruption (ETOUR).
    }
    // Changed to Map for O(1) average time complexity for lookups and deletions by ID.
    private Map<String, News> newsDatabase; // Internal storage simulating a database
    private static final double CONNECTION_ERROR_CHANCE = 0.25; // 25% chance of simulating a connection error
    /**
     * Constructs a new NewsService and initializes it with some mock news data.
     */
    public NewsService() {
        // Initialize as HashMap
        newsDatabase = new HashMap<>(); 
        // Populate the database with initial sample news items.
        // News items are stored using their ID as the key for efficient retrieval.
        News news1 = new News("Breaking: Quantum Computing Breakthrough", "Scientists at CERN have announced a significant leap forward in quantum computing, potentially paving the way for unbreakable encryption and ultra-fast data processing.");
        newsDatabase.put(news1.getId(), news1);
        News news2 = new News("Local Park Renovation Project Completed", "After months of work, the city's beloved Central Park has reopened with new playgrounds, walking trails, and enhanced landscaping. Celebrations are planned for the upcoming weekend.");
        newsDatabase.put(news2.getId(), news2);
        News news3 = new News("Tech Giant Acquires AI Startup for Billions", "In a move shaking the tech world, 'Global Innovations Inc.' has purchased cutting-edge AI startup 'NeuralDynamics' for an undisclosed sum, reportedly in the multi-billion dollar range.");
        newsDatabase.put(news3.getId(), news3);
        News news4 = new News("Global Food Pr Stabilize After Volatile Period", "Reports from the World Agricultural Organization indicate a period of stability in global food pr, bringing relief after months of sharp fluctuations caused by supply chain issues.");
        newsDatabase.put(news4.getId(), news4);
        News news5 = new News("New Art Exhibition Opens Downtown", "The 'Modern Expression' gallery is hosting a new exhibition featuring contemporary artists from across the globe. The show opens tonight and runs for three weeks.");
        newsDatabase.put(news5.getId(), news5);
        News news6 = new News("Exclusive Interview with Bestselling Author", "We sat down with celebrated author Elara Vance to discuss her latest novel, 'Whispers of the Stars', which has already topped bestseller lists worldwide.");
        newsDatabase.put(news6.getId(), news6);
    }
    /**
     * Retrieves all available news items from the simulated database.
     *
     * @return A new ArrayList containing all News objects currently in the system.
     *         A new list is returned to prevent external modification of the internal database state.
     */
    public List<News> getAllNews() {
        // Retrieve all News objects (values) from the HashMap and return them as a new ArrayList.
        return new ArrayList<>(newsDatabase.values());
    }
    /**
     * Deletes a news item from the simulated database based on its unique ID.
     * This method also simulates potential server connection interruptions.
     *
     * @param newsId The unique ID of the news item to be deleted.
     * @return A {@code DeletionResult} enum indicating the outcome of the deletion:
     *         {@code SUCCESS}, {@code NEWS_NOT_FOUND}, or {@code CONNECTION_INTERRUPTED}.
     */
    public DeletionResult deleteNews(String newsId) {
        // Simulate ETOUR server connection interruption with a defined probability
        if (ThreadLocalRandom.current().nextDouble() < CONNECTION_ERROR_CHANCE) {
            System.err.println("Simulated server connection interruption (ETOUR) during delete operation.");
            return DeletionResult.CONNECTION_INTERRUPTED; // Return specific ETOUR result
        }
        // Attempt to remove the news item directly from the HashMap using its ID.
        // remove() returns the value previously associated with the key, or null if the key was not present.
        News removedNews = newsDatabase.remove(newsId);
        if (removedNews != null) {
            // News was found and successfully removed.
            return DeletionResult.SUCCESS; // Return success
        } else {
            // News with the given ID was not found in the database.
            return DeletionResult.NEWS_NOT_FOUND; // Return not found
        }
    }
}