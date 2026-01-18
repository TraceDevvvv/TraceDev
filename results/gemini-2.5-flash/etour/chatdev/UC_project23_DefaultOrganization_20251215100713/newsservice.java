/**
 * A service class to manage News data.
 * This class simulates a data store (in-memory ArrayList) for news items.
 * It provides methods to retrieve, update, and manage news data.
 * In a real application, this would interact with a database or a remote API.
 */
package com.chatdev.newsapp.serv;
import com.chatdev.newsapp.models.News;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
public class NewsService {
    /**
     * Custom exception to indicate a simulated server connection error.
     */
    public static class ServerConnectionException extends RuntimeException {
        public ServerConnectionException(String message) { super(message); }
    }
    /**
     * Custom exception to indicate that a news item was not found.
     */
    public static class NewsNotFoundException extends RuntimeException {
        public NewsNotFoundException(String message) { super(message); }
    }
    // Using a HashMap for efficient lookup by ID, backing it with an ArrayList for order if needed.
    private static Map<String, News> newsData = new HashMap<>();
    // AtomicInteger to generate unique IDs for new news items (though not used in this 'modify' use case)
    private static AtomicInteger idCounter = new AtomicInteger(0);
    // Flag to simulate server connection failure
    private static boolean simulateServerFailure = false; // Changed to private for better encapsulation
    /**
     * Checks if server connection failure simulation is currently active.
     * @return true if simulation is active, false otherwise.
     */
    public static boolean isServerFailureSimulated() {
        return simulateServerFailure;
    }
    /**
     * Setter for the simulateServerFailure flag.
     * @param value true to simulate server failure, false otherwise.
     */
    private static void setSimulateServerFailure(boolean value) {
        simulateServerFailure = value;
    }
    /**
     * Static initializer block to pre-populate some sample news data.
     * This runs once when the class is loaded.
     */
    static {
        // Pre-populate with some dummy news
        addInitialNews(new News(generateUniqueId(), "Breaking News: Market Soars", "The stock market experienced an unprecedented surge today.", "John Doe", LocalDate.now().minusDays(2).toString()));
        addInitialNews(new News(generateUniqueId(), "Local Elections Update", "Counting of votes is underway in the local elections, with results expected soon.", "Jane Smith", LocalDate.now().minusDays(1).toString()));
        addInitialNews(new News(generateUniqueId(), "Tech Innovation Expo", "The annual tech innovation expo showcases cutting-edge advancements.", "Alice Brown", LocalDate.now().toString()));
        addInitialNews(new News(generateUniqueId(), "Sports: Championship Final", "Team A clinched the championship title in a thrilling match.", "Bob White", LocalDate.now().minusDays(3).toString()));
    }
    /**
     * Helper method to add news to the static map during initialization.
     * @param news The News object to add.
     */
    private static void addInitialNews(News news) {
        newsData.put(news.getId(), news);
    }
    /**
     * Generates a unique ID for a news item.
     * @return A unique string ID.
     */
    private static String generateUniqueId() {
        return "NEWS-" + idCounter.incrementAndGet();
    }
    /**
     * Retrieves all news items currently in the system.
     * @return A list of all News objects.
     */
    public List<News> getAllNews() {
        return new ArrayList<>(newsData.values());
    }
    /**
     * Retrieves a specific news item by its unique ID.
     * @param id The ID of the news item to retrieve.
     * @return The News object if found, otherwise null.
     */
    public News getNewsById(String id) {
        return newsData.get(id);
    }
    /**
     * Updates an existing news item.
     * Requires the ID of the news item to match an existing one.
     * @param updatedNews The News object with updated data.
     * @throws NewsService.ServerConnectionException if a simulated server interruption occurs.
     * @throws NewsService.NewsNotFoundException if the news item with the given ID is not found.
     * @throws IllegalArgumentException if updatedNews or its ID is null.
     */
    public void updateNews(News updatedNews) throws ServerConnectionException, NewsNotFoundException, IllegalArgumentException {
        // Simulate server connection interruption
        if (simulateServerFailure) {
            throw new ServerConnectionException("ETOUR: Simulated server connection interrupted while saving.");
        }
        if (updatedNews == null || updatedNews.getId() == null) {
            throw new IllegalArgumentException("Updated news object or its ID cannot be null.");
        }
        if (!newsData.containsKey(updatedNews.getId())) {
            throw new NewsNotFoundException("News with ID " + updatedNews.getId() + " not found for update.");
        }
        // Replace the old news object with the updated one
        newsData.put(updatedNews.getId(), updatedNews);
        System.out.println("News updated: " + updatedNews.getTitle()); // For console logging
    }
    /**
     * Simulates a "server connection interruption" scenario by setting a flag.
     * Subsequent calls to updateNews will throw a ServerConnectionException.
     */
    public void activateServerInterruption() { // Renamed from simulateServerInterruption as per thought process
        setSimulateServerFailure(true); // Set the flag to true to cause future updates to fail
        System.out.println("ETOUR: Server connection simulation activated. Subsequent saves will fail.");
    }
    /**
     * Deactivates the simulation of a "server connection interruption".
     * Subsequent calls to updateNews will no longer throw a ServerConnectionException unless re-activated.
     */
    public void deactivateServerInterruption() {
        setSimulateServerFailure(false);
        System.out.println("ETOUR: Server connection simulation deactivated. Subsequent saves should proceed normally.");
    }
}