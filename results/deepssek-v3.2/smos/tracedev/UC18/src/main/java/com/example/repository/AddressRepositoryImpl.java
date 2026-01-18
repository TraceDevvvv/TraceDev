package com.example.repository;

import com.example.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Implementation of AddressRepository that interacts with the SMOS database.
 */
public class AddressRepositoryImpl implements AddressRepository {
    private DataSource dataSource;

    // Constructor with dependency injection
    public AddressRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Address save(Address address) {
        // Simulate possible connection failure as per sequence diagram
        if (dataSource == null) {
            throw new ConnectionErrorException("SMOS server connection failed");
        }

        String sql = "INSERT INTO addresses (name) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, address.getName());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        address.setId(rs.getLong(1));
                    }
                }
            }
            return address;
        } catch (SQLException e) {
            throw new ConnectionErrorException("SMOS server connection failed", e);
        }
    }

    /**
     * INSERT address - Sequence diagram message implementation
     */
    public Address INSERTAddress(Address address) {
        return save(address);
    }

    /**
     * Confirm insertion - Sequence diagram message implementation
     */
    public boolean confirmInsertion() {
        // In real implementation, this would verify the insertion
        return true;
    }
}