package com.example.justification;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Implementation of JustificationRepository.
 * Assumption: Uses JDBC DataSource for database operations.
 */
public class JustificationRepositoryImpl implements JustificationRepository {
    private DataSource dataSource;

    // Constructor for dependency injection
    public JustificationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Justification findById(int justificationId) {
        String sql = "SELECT id, reason, created_date, created_by FROM justification WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, justificationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Justification justification = new Justification();
                    justification.setId(rs.getInt("id"));
                    justification.setReason(rs.getString("reason"));
                    Timestamp ts = rs.getTimestamp("created_date");
                    if (ts != null) {
                        justification.setCreatedDate(new Date(ts.getTime()));
                    }
                    justification.setCreatedBy(rs.getInt("created_by"));
                    return justification;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            // According to sequence diagram, throws DatabaseException on connection issues.
            // Here we wrap in a runtime exception for simplicity.
            throw new RuntimeException("Database error while finding justification", e);
        }
    }

    @Override
    public void delete(Justification justification) {
        String sql = "DELETE FROM justification WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, justification.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting justification", e);
        }
    }

    @Override
    public void cancelTransaction(int justificationId) {
        // In a real implementation, this would rollback a transaction.
        // Since we are using auto-commit or separate transactions, we simulate rollback.
        // For demonstration, we do nothing - but would typically involve connection rollback.
        // Assumption: Transaction is managed externally (e.g., Spring @Transactional).
        // This method would be called to trigger rollback in case of interruption.
    }

    /**
     * Executes SELECT * FROM justification WHERE id = justificationId.
     * This method corresponds to sequence diagram messages m9, m26, m41.
     * @param justificationId the ID of the justification
     * @return Justification data as a ResultSet (simplified as Justification entity)
     */
    public Justification selectJustificationById(int justificationId) {
        // Delegate to existing findById method.
        return findById(justificationId);
    }

    /**
     * Executes DELETE FROM justification WHERE id = justificationId.
     * This method corresponds to sequence diagram message m13.
     * @param justificationId the ID of the justification
     * @return true if deletion succeeded, false otherwise
     */
    public boolean deleteJustificationById(int justificationId) {
        String sql = "DELETE FROM justification WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, justificationId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting justification by ID", e);
        }
    }

    /**
     * Executes ROLLBACK.
     * This method corresponds to sequence diagram message m36.
     * @param justificationId the ID of the justification (for logging)
     */
    public void rollback(int justificationId) {
        // In a real implementation, this would rollback the current transaction.
        // For simplicity, we simulate rollback by printing.
        System.out.println("[Repository] Rolling back transaction for justification ID: " + justificationId);
    }
}