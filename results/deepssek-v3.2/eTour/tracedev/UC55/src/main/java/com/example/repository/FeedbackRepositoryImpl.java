package com.example.repository;

import com.example.model.Feedback;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

/**
 * Implementation of FeedbackRepository using a DataSource.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private DataSource datasource;

    public FeedbackRepositoryImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Optional<Feedback> findBySiteAndUser(String siteId, String userId) {
        // Simulate database query.
        String sql = "SELECT * FROM feedback WHERE site_id = ? AND user_id = ?";
        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, siteId);
            stmt.setString(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getString("id"),
                    rs.getString("site_id"),
                    rs.getString("user_id"),
                    rs.getString("content"),
                    rs.getDate("created_at")
                );
                return Optional.of(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Feedback> findLatestByUser(String userId) {
        // Simplified: return the first found for the user.
        // In a real implementation, we would order by createdAt desc and limit 1.
        String sql = "SELECT * FROM feedback WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";
        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getString("id"),
                    rs.getString("site_id"),
                    rs.getString("user_id"),
                    rs.getString("content"),
                    rs.getDate("created_at")
                );
                return Optional.of(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Feedback> queryExistingFeedback(String siteId, String userId) {
        return findBySiteAndUser(siteId, userId);
    }

    /**
     * Return feedback record.
     * Corresponds to sequence diagram message m6.
     */
    public Optional<Feedback> returnFeedbackRecord(String siteId, String userId) {
        return findBySiteAndUser(siteId, userId);
    }
}