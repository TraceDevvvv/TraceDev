

package com.example.repository;

import com.example.dto.Feedback;
import com.example.service.FeedbackService;
import com.example.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

/**
 * Repository implementation for Feedback persistence.
 */
public class FeedbackRepository implements FeedbackService {
    private DatabaseConnection databaseConnection;

    public FeedbackRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public boolean saveFeedback(Feedback feedback) {
        // Simulate saving to database
        try (Connection conn = databaseConnection.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO feedback (feedback_id, site_id, tourist_id, rating, comment, submission_date) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, feedback.getFeedbackId());
            stmt.setString(2, feedback.getSiteId());
            stmt.setString(3, feedback.getTouristId());
            stmt.setInt(4, feedback.getRating());
            stmt.setString(5, feedback.getComment());
            stmt.setTimestamp(6, Timestamp.valueOf(feedback.getSubmissionDate()));
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Feedback> findExistingFeedback(String touristId, String siteId) {
        // Simulate querying existing feedback
        try (Connection conn = databaseConnection.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM feedback WHERE tourist_id = ? AND site_id = ?");
            stmt.setString(1, touristId);
            stmt.setString(2, siteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getString("feedback_id"));
                feedback.setTouristId(rs.getString("tourist_id"));
                feedback.setSiteId(rs.getString("site_id"));
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setSubmissionDate(rs.getTimestamp("submission_date").toLocalDateTime());
                return Optional.of(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Also implement the repository-specific method (as per class diagram)
    public boolean save(Feedback feedback) {
        return saveFeedback(feedback);
    }

    public Optional<Feedback> findByTouristAndSite(String touristId, String siteId) {
        return findExistingFeedback(touristId, siteId);
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}

