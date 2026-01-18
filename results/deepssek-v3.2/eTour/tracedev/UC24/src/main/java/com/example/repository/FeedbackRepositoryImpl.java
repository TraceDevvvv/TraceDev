package com.example.repository;

import com.example.datasource.DataSource;
import com.example.exception.ConnectionException;
import com.example.model.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of FeedbackRepository.
 * Uses a DataSource to connect to the database.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private DataSource dataSource;

    public FeedbackRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Feedback> findBySiteId(String siteId) throws ConnectionException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM feedback WHERE site_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, siteId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback(
                        resultSet.getString("id"),
                        resultSet.getString("site_id"),
                        resultSet.getString("content"),
                        resultSet.getInt("rating"),
                        new Date(resultSet.getTimestamp("timestamp").getTime())
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            // Convert SQLException to our custom ConnectionException
            throw new ConnectionException(1001, "Database connection error: " + e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) dataSource.closeConnection(connection);
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return feedbackList;
    }
}