'''
Business logic layer for news operations
Handles operations between GUI and database
'''
package com.chatdev.newsapp;
import java.util.List;
public class NewsService {
    private DatabaseManager dbManager;
    public NewsService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
    /**
     * Retrieves all news for display
     * @return List of all news articles
     */
    public List<News> getAllNews() {
        return dbManager.getAllNews();
    }
    /**
     * Deletes a news article with confirmation and error handling
     * @param newsId The ID of the news to delete
     * @return Result message
     * @throws SecurityException if agency operator is not logged in
     */
    public String deleteNews(int newsId) throws SecurityException {
        // Check authentication - agency must be logged in
        SessionManager session = SessionManager.getInstance();
        session.validateSession();
        // Check if news exists
        if (!dbManager.newsExists(newsId)) {
            return "Error: News with ID " + newsId + " does not exist.";
        }
        News newsToDelete = dbManager.getNewsById(newsId);
        String newsTitle = newsToDelete.getTitle();
        try {
            // Attempt to delete the news
            boolean success = dbManager.deleteNews(newsId);
            if (success) {
                return "Success: News '" + newsTitle + "' (ID: " + newsId + ") has been successfully deleted.";
            } else {
                return "Error: Failed to delete news with ID " + newsId + ".";
            }
        } catch (ServerConnectionException e) {
            // Handle connection interruption specifically
            return "Error: " + e.getMessage() + ". Deletion cancelled.\nPlease check your connection to ETOUR server and try again.";
        }
    }
    /**
     * Gets news details by ID
     * @param newsId The news ID
     * @return News object or null
     */
    public News getNewsDetails(int newsId) {
        return dbManager.getNewsById(newsId);
    }
    /**
     * Validates user session before any operation
     * @throws SecurityException if not authenticated
     */
    public void validateUserSession() throws SecurityException {
        SessionManager session = SessionManager.getInstance();
        session.validateSession();
    }
}