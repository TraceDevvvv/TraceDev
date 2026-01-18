package com.example.infrastructure;

import com.example.domain.DataSourceException;
import com.example.domain.Location;
import com.example.domain.SiteFeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * JDBC implementation of SiteFeedbackRepository.
 * Throws DataSourceException for database connection errors.
 */
public class JdbcSiteFeedbackRepository implements SiteFeedbackRepository {
    private DataSource dataSource;

    public JdbcSiteFeedbackRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SiteFeedback> findByLocation(Location location) {
        List<SiteFeedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE location_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location.getPlaceId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String locationId = rs.getString("location_id");
                    int rating = rs.getInt("rating");
                    String comment = rs.getString("comment");
                    feedbackList.add(new SiteFeedback(locationId, rating, comment));
                }
            }
        } catch (SQLException e) {
            // Throw DataSourceException as per requirement Alternate Flow.
            throw new DataSourceException("Database connection error", 500);
        }
        return feedbackList;
    }
}