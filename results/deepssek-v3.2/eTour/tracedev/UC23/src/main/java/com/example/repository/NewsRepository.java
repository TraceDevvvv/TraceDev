package com.example.repository;

import com.example.database.Database;
import com.example.connection.ETOURServerConnection;
import com.example.models.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repository for News entities.
 * Depends on Database and ETOURServerConnection.
 */
public class NewsRepository {
    private Database database;
    private ETOURServerConnection connection;

    public NewsRepository(Database database, ETOURServerConnection connection) {
        this.database = database;
        this.connection = connection;
    }

    /**
     * Finds a news by its ID.
     */
    public Optional<News> findById(Long id) {
        // Simulated database query.
        // In reality, this would use the Database object to execute a SQL query.
        System.out.println("Finding news by id: " + id);
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        // Create a dummy news for demonstration.
        News news = new News();
        news.setId(id);
        news.setTitle("Sample News Title");
        news.setContent("Sample news content.");
        news.setLastModified(new java.util.Date());
        return Optional.of(news);
    }

    /**
     * Retrieves all news.
     */
    public List<News> findAll() {
        // Simulated retrieval of all news.
        System.out.println("Finding all news...");
        List<News> newsList = new ArrayList<>();
        // Add dummy entries.
        for (long i = 1; i <= 3; i++) {
            News news = new News();
            news.setId(i);
            news.setTitle("News Title " + i);
            news.setContent("Content for news " + i);
            news.setLastModified(new java.util.Date());
            newsList.add(news);
        }
        return newsList;
    }

    /**
     * Saves a new news entity.
     */
    public News save(News news) {
        // Simulated save operation.
        System.out.println("Saving news with id: " + news.getId());
        // In a real app, this would perform an INSERT.
        return news;
    }

    /**
     * Updates an existing news entity.
     */
    public News update(News news) {
        // Simulated update operation.
        // Check connection before updating.
        if (!connection.checkConnection()) {
            throw new RuntimeException("Server connection lost");
        }
        System.out.println("Updating news with id: " + news.getId());
        // In a real app, this would perform an UPDATE.
        return news;
    }

    /**
     * Simulates SELECT * FROM news query.
     * Returns a ResultSet (simulated).
     */
    public ResultSet selectAllNews() throws SQLException {
        return database.query("SELECT * FROM news");
    }

    /**
     * Simulates SELECT * FROM news WHERE id = ? query.
     * Returns a ResultSet (simulated).
     */
    public ResultSet selectNewsById(Long id) throws SQLException {
        return database.query("SELECT * FROM news WHERE id = " + id);
    }

    /**
     * Simulates converting a ResultSet to News objects.
     * For demonstration, returns a list of dummy news.
     */
    public List<News> convertToNewsObjects(ResultSet rs) {
        // In a real implementation, iterate through ResultSet and create News objects.
        // For now, return dummy list.
        return findAll();
    }

    /**
     * Simulates creating a News object from a ResultSet.
     * For demonstration, returns a dummy news.
     */
    public News createNewsObject(ResultSet rs) {
        News news = new News();
        news.setId(1L);
        news.setTitle("Dummy Title");
        news.setContent("Dummy Content");
        news.setLastModified(new java.util.Date());
        return news;
    }

    /**
     * Simulates UPDATE news SET title=?, content=? WHERE id=?.
     */
    public int updateNews(Long id, String title, String content) throws SQLException {
        String sql = String.format("UPDATE news SET title='%s', content='%s' WHERE id=%d", title, content, id);
        return database.update(sql);
    }

    /**
     * Simulates UPDATE news (generic update).
     */
    public int updateNews(String sql) throws SQLException {
        return database.update(sql);
    }
}