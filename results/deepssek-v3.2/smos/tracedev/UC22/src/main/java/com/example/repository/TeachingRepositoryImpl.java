package com.example.repository;

import com.example.model.Teaching;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TeachingRepository.
 * Handles data persistence and database operations.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private DataSource dataSource;

    public TeachingRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Teaching> findAll() {
        List<Teaching> teachings = new ArrayList<>();
        // Assumption: The query retrieves all columns corresponding to Teaching attributes.
        String query = "SELECT id, title, description, status FROM teachings";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = executeQuery(stmt)) {
            while (rs.next()) {
                Teaching teaching = new Teaching(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status")
                );
                teachings.add(teaching);
            }
        } catch (SQLException e) {
            // In a real application, handle exception appropriately (log, throw custom exception, etc.)
            e.printStackTrace();
        }
        return teachings;
    }

    @Override
    public Teaching findById(Long id) {
        // Implementation not required for the sequence diagram, but provided for interface completeness.
        // For simplicity, returns null.
        return null;
    }

    /**
     * Executes a query and returns the ResultSet.
     * This method simulates the database interaction.
     * @param stmt The prepared statement.
     * @return ResultSet from the query execution.
     */
    private ResultSet executeQuery(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }
}