package com.example.data;

import com.example.domain.Feedback;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concrete repository that accesses Feedback data from a database.
 * Implements FeedbackRepository as per the Class Diagram.
 */
public class DatabaseFeedbackRepository implements FeedbackRepository {
    private DataSource dataSource;

    // Assumption: DataSource is injected via constructor.
    public DatabaseFeedbackRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Feedback> findBySiteId(int siteId) {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT feedback_id, site_id, comment, rating, date_submitted FROM feedback WHERE site_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, siteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int feedbackId = rs.getInt("feedback_id");
                    String comment = rs.getString("comment");
                    int rating = rs.getInt("rating");
                    Date dateSubmitted = rs.getDate("date_submitted");
                    feedbacks.add(new Feedback(feedbackId, siteId, comment, rating, dateSubmitted));
                }
            }
        } catch (SQLException e) {
            // Simulating connection interruption as per Sequence Diagram.
            throw new ConnectionException("Failed to fetch feedback for site: " + siteId, e);
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> findAll() {
        // Not used in the current sequence diagram, but required by interface.
        throw new UnsupportedOperationException("findAll not implemented for feedback");
    }
}