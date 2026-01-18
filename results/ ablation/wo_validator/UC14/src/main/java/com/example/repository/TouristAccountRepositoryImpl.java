package com.example.repository;

import com.example.entity.TouristAccount;
import com.example.dto.TouristAccountSearchCriteriaDTO;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the tourist account repository.
 * Contains direct database access logic.
 */
public class TouristAccountRepositoryImpl implements ITouristAccountRepository {
    private Object dataSource; // Could be a DataSource, Connection, etc. For simplicity using Object.

    public TouristAccountRepositoryImpl(Object dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TouristAccount> findAll(TouristAccountSearchCriteriaDTO searchCriteria) {
        // Simulating database query as per sequence diagram.
        // In a real implementation, this would use the dataSource to get a connection.
        List<TouristAccount> results = new ArrayList<>();
        String sql = "SELECT * FROM tourist_accounts WHERE name LIKE ? AND email LIKE ? AND agency_id = ?";
        
        // Assume dataSource is a Connection for this example.
        // Since the diagram uses Object, we assume it can be cast appropriately.
        // For simplicity, we simulate the query with a mock result.
        if (dataSource instanceof Connection) {
            try (Connection conn = (Connection) dataSource;
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + (searchCriteria.getName() != null ? searchCriteria.getName() : "") + "%");
                stmt.setString(2, "%" + (searchCriteria.getEmail() != null ? searchCriteria.getEmail() : "") + "%");
                stmt.setString(3, searchCriteria.getAgencyId() != null ? searchCriteria.getAgencyId() : "");
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        TouristAccount account = new TouristAccount(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("agency_id")
                        );
                        results.add(account);
                    }
                }
            } catch (SQLException e) {
                // Simulate connection interruption as per sequence diagram's alt block.
                throw new RuntimeException("ConnectionException", e);
            }
        } else {
            // Mock data for demonstration when no real database is available.
            results.add(new TouristAccount("1", "John Doe", "john@example.com", "A001"));
            results.add(new TouristAccount("2", "Jane Smith", "jane@example.com", "A001"));
        }
        return results;
    }
}