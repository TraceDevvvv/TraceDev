import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

/**
 * NewsService class manages news storage, retrieval, update, and validation.
 * This class simulates a simple in-memory database for news articles.
 * In a real application, this would be replaced with a database connection.
 */
public class NewsService {
    // In-memory list to store news articles
    private List<News> newsList;
    private int nextId; // To generate unique IDs for new news

    /**
     * Constructor initializes the news list with some sample data.
     */
    public NewsService() {
        newsList = new ArrayList<>();
        nextId = 1;
        // Initialize with some sample news for testing
        initializeSampleNews();
    }

    /**
     * Adds sample news articles to the list for demonstration.
     */
    private void initializeSampleNews() {
        addNews(new News(nextId++, "Java 21 Released", "Java 21 brings new features...", "Oracle", new Date()));
        addNews(new News(nextId++, "AI Breakthrough", "New AI model achieves...", "TechNews", new Date()));
        addNews(new News(nextId++, "Climate Summit", "Global leaders meet to discuss...", "GreenWorld", new Date()));
    }

    /**
     * Retrieves all news articles.
     * 
     * @return List of all news articles
     */
    public List<News> getAllNews() {
        return new ArrayList<>(newsList); // Return a copy to prevent external modification
    }

    /**
     * Retrieves a specific news article by its ID.
     * 
     * @param id the ID of the news to retrieve
     * @return the News object if found, null otherwise
     */
    public News getNewsById(int id) {
        for (News news : newsList) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null; // News not found
    }

    /**
     * Adds a new news article to the list.
     * 
     * @param news the News object to add
     * @return true if added successfully, false if news is null or invalid
     */
    public boolean addNews(News news) {
        if (news == null || !news.isValid()) {
            return false;
        }
        newsList.add(news);
        return true;
    }

    /**
     * Updates an existing news article.
     * Validates the updated news data before saving.
     * 
     * @param id the ID of the news to update
     * @param updatedNews the News object with updated data
     * @return true if update successful, false if news not found or invalid data
     */
    public boolean updateNews(int id, News updatedNews) {
        News existingNews = getNewsById(id);
        if (existingNews == null) {
            return false; // News not found
        }
        
        // Validate the updated news data
        if (updatedNews == null || !updatedNews.isValid()) {
            return false; // Invalid data
        }
        
        // Update the existing news with new values
        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setAuthor(updatedNews.getAuthor());
        existingNews.setDate(updatedNews.getDate());
        
        return true;
    }

    /**
     * Deletes a news article by ID.
     * 
     * @param id the ID of the news to delete
     * @return true if deleted successfully, false if news not found
     */
    public boolean deleteNews(int id) {
        News newsToRemove = getNewsById(id);
        if (newsToRemove != null) {
            newsList.remove(newsToRemove);
            return true;
        }
        return false;
    }

    /**
     * Validates news data according to business rules.
     * This method provides additional validation beyond the News.isValid() method.
     * 
     * @param news the News object to validate
     * @return true if news is valid, false otherwise
     */
    public boolean validateNews(News news) {
        if (news == null) {
            return false;
        }
        
        // Check required fields
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            return false;
        }
        if (news.getContent() == null || news.getContent().trim().isEmpty()) {
            return false;
        }
        if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
            return false;
        }
        if (news.getDate() == null) {
            return false;
        }
        
        // Additional business rules could be added here
        // For example: title length, content length, etc.
        
        return true;
    }

    /**
     * Displays all news articles in a formatted list.
     * Used for the "View all news" functionality.
     */
    public void displayAllNews() {
        if (newsList.isEmpty()) {
            System.out.println("No news articles available.");
            return;
        }
        
        System.out.println("\n=== All News Articles ===");
        for (News news : newsList) {
            System.out.println(news.toString());
        }
        System.out.println("=========================\n");
    }

    /**
     * Simulates a connection check to the server ETOUR.
     * In a real application, this would check network connectivity.
     * 
     * @return true if connection is available, false otherwise
     */
    public boolean checkServerConnection() {
        // Simulate connection check - in reality, this would ping the server
        // For demonstration, we'll assume connection is always available
        // but add a small chance of failure to simulate interruption
        return Math.random() > 0.1; // 90% chance of success
    }

    /**
     * Simulates saving news data to persistent storage.
     * In a real application, this would save to a database.
     * 
     * @param news the News object to save
     * @return true if save successful, false otherwise
     */
    public boolean saveNewsToStorage(News news) {
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to server ETOUR interrupted.");
            return false;
        }
        
        // Simulate save operation with potential failure
        boolean saveSuccessful = Math.random() > 0.05; // 95% chance of success
        
        if (saveSuccessful) {
            System.out.println("News saved successfully to storage.");
        } else {
            System.out.println("Error: Failed to save news to storage.");
        }
        
        return saveSuccessful;
    }
}