package com.example.repository;

import com.example.datasource.DataSource;
import com.example.exception.ConnectionException;
import com.example.model.Tourist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of TouristRepository.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private final DataSource dataSource;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tourist> findAll(String searchCriteria) throws ConnectionException {
        List<Tourist> tourists = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            // Simplified query: search by name or email containing the criteria
            String sql = "SELECT * FROM tourist WHERE name LIKE ? OR email LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String likePattern = "%" + searchCriteria + "%";
            stmt.setString(1, likePattern);
            stmt.setString(2, likePattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tourist t = new Tourist();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setEmail(rs.getString("email"));
                t.setPhoneNumber(rs.getString("phone_number"));
                t.setNationality(rs.getString("nationality"));
                t.setDateOfBirth(new Date(rs.getDate("date_of_birth").getTime()));
                tourists.add(t);
            }
        } catch (SQLException e) {
            // Wrap SQLException as ConnectionException to satisfy the diagram
            throw new ConnectionException("Database connection error: " + e.getMessage(), 1001);
        } finally {
            if (conn != null) {
                dataSource.closeConnection(conn);
            }
        }
        return tourists;
    }

    @Override
    public Optional<Tourist> findById(int id) throws ConnectionException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM tourist WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tourist t = new Tourist();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setEmail(rs.getString("email"));
                t.setPhoneNumber(rs.getString("phone_number"));
                t.setNationality(rs.getString("nationality"));
                t.setDateOfBirth(new Date(rs.getDate("date_of_birth").getTime()));
                return Optional.of(t);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new ConnectionException("Database connection error: " + e.getMessage(), 1002);
        } finally {
            if (conn != null) {
                dataSource.closeConnection(conn);
            }
        }
    }
}