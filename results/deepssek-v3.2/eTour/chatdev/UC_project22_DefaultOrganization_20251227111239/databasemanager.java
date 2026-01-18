'''
Simulates database operations for the news system
In a real application, this would connect to an actual database
'''
package com.chatdev.newsapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DatabaseManager {
    private Map<Integer, News> newsDatabase;
    private int nextId;
    public DatabaseManager() {
        newsDatabase = new HashMap<>();
        nextId = 1;
    }
    /**
     * Initializes the database with sample news articles for demonstration
     */
    public void initializeDatabase() {
        addNews(new News(nextId++, "System Maintenance", "Scheduled maintenance this weekend.", "Admin", "2024-01-10"));
        addNews(new News(nextId++, "New Features Released", "Version 2.0 includes dashboard improvements.", "Dev Team", "2024-01-15"));
        addNews(new News(nextId++, "Security Update", "Important security patches applied.", "Security Team", "2024-01-20"));
        addNews(new News(nextId++, "Holiday Schedule", "Office closed for Thanksgiving.", "HR", "2024-01-25"));
        addNews(new News(nextId++, "Training Session", "Mandatory training next Monday.", "Training Dept", "2024-01-30"));
    }
    /**
     * Retrieves all news from the database
     * @return List of all news articles
     */
    public List<News> getAllNews() {
        return new ArrayList<>(newsDatabase.values());
    }
    /**
     * Adds a news article to the database
     * @param news The news article to add
     */
    public void addNews(News news) {
        newsDatabase.put(news.getId(), news);
    }
    /**
     * Deletes a news article by ID
     * @param id The ID of the news to delete
     * @return true if deletion was successful, false otherwise
     * @throws ServerConnectionException if connection to ETOUR server is interrupted
     */
    public boolean deleteNews(int id) throws ServerConnectionException {
        // Simulate connection interruption (10% chance for demonstration)
        if (Math.random() < 0.1) {
            throw new ServerConnectionException("Connection to server ETOUR interrupted");
        }
        return newsDatabase.remove(id) != null;
    }
    /**
     * Finds a news article by ID
     * @param id The ID to search for
     * @return The news article or null if not found
     */
    public News getNewsById(int id) {
        return newsDatabase.get(id);
    }
    /**
     * Checks if a news ID exists in the database
     * @param id The ID to check
     * @return true if exists, false otherwise
     */
    public boolean newsExists(int id) {
        return newsDatabase.containsKey(id);
    }
    /**
     * Gets the current database size
     * @return Number of news articles
     */
    public int getNewsCount() {
        return newsDatabase.size();
    }
}