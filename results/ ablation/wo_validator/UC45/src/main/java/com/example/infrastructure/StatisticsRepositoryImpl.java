package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;
import com.example.domain.Transaction;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of StatisticsRepository using a DataSource (e.g., SQL database).
 */
public class StatisticsRepositoryImpl implements StatisticsRepository {
    private DataSource dataSource;

    public StatisticsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Transaction> findTransactionsByPointId(String pointId, Date startDate, Date endDate) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE point_id = ? AND timestamp BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pointId);
            stmt.setTimestamp(2, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(3, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(
                    rs.getString("transaction_id"),
                    rs.getString("point_id"),
                    rs.getDouble("amount"),
                    new Date(rs.getTimestamp("timestamp").getTime())
                );
                // Assume items are stored as comma-separated string in column "items"
                String itemsStr = rs.getString("items");
                if (itemsStr != null && !itemsStr.isEmpty()) {
                    t.setItems(java.util.Arrays.asList(itemsStr.split(",")));
                }
                transactions.add(t);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error fetching transactions", e);
        }
        return transactions;
    }

    @Override
    public RefreshmentPoint findRefreshmentPointById(String pointId) {
        String sql = "SELECT * FROM refreshment_points WHERE point_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pointId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RefreshmentPoint(
                    rs.getString("point_id"),
                    rs.getString("name"),
                    rs.getString("owner_id")
                );
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error fetching refreshment point", e);
        }
        return null;
    }

    /**
     * Custom exception for repository errors.
     */
    public static class RepositoryException extends RuntimeException {
        public RepositoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}