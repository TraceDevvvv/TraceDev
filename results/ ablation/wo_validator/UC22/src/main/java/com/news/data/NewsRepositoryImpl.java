package com.news.data;

import com.news.domain.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of NewsRepository.
 * Uses a DataSource to interact with a relational database.
 */
public class NewsRepositoryImpl implements NewsRepository {
    private DataSource dataSource;

    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setPublicationDate(rs.getDate("publicationDate"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            // Wrap and rethrow as a runtime exception for simplicity.
            // In a real app, use a custom checked exception.
            throw new RuntimeException("Database error while fetching all news", e);
        }
        return newsList;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM news WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Simulating connection interruption scenario from sequence diagram.
            // If connection is lost, SQLException is thrown.
            throw new RuntimeException("Database error while deleting news with id: " + id, e);
        }
    }
}