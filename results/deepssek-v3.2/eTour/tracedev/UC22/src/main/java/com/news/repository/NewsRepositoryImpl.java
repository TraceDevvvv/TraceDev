
package com.news.repository;

import com.news.entity.News;
import com.news.exception.ConnectionException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of NewsRepository using a database.
 * Uses DataSource for connection and a Database helper for SQL operations.
 */
public class NewsRepositoryImpl implements NewsRepository {
    private DataSource dataSource;

    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        // Simplified query as per sequence diagram
        String sql = "SELECT * FROM news WHERE deleted = false";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                News news = mapResultSetToNews(rs);
                newsList.add(news);
            }
        } catch (SQLException e) {
            // In a real implementation, we might log or handle this differently
            System.err.println("Error fetching all news: " + e.getMessage());
        }
        return newsList;
    }

    @Override
    public Optional<News> findById(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                News news = mapResultSetToNews(rs);
                return Optional.of(news);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching news by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long newsId) throws ConnectionException {
        // Update query as per sequence diagram: soft delete
        String sql = "UPDATE news SET deleted = true, deletionDate = NOW() WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, newsId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // If SQLException occurs, treat as connection issue as per sequence diagram
            throw new ConnectionException("Connection to server ETOUR interrupted");
        }
    }

    private News mapResultSetToNews(ResultSet rs) throws SQLException {
        News news = new News();
        news.setId(rs.getLong("id"));
        news.setTitle(rs.getString("title"));
        news.setContent(rs.getString("content"));
        news.setPublicationDate(rs.getDate("publicationDate"));
        news.setDeleted(rs.getBoolean("deleted"));
        news.setDeletionDate(rs.getDate("deletionDate"));
        return news;
    }
}
