package com.example.repository;

import com.example.domain.Tourist;
import com.example.enums.AccountStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Concrete implementation of TouristRepository using a DataSource.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DataSource dataSource;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tourist> findAll(String criteria) {
        List<Tourist> tourists = new ArrayList<>();
        String sql = "SELECT id, name, email, status FROM tourists WHERE name LIKE ? OR email LIKE ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String likeCriteria = "%" + criteria + "%";
            stmt.setString(1, likeCriteria);
            stmt.setString(2, likeCriteria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tourist tourist = new Tourist(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"));
                tourist.setAccountStatus(AccountStatus.valueOf(rs.getString("status")));
                tourists.add(tourist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tourists;
    }

    @Override
    public Tourist findById(String id) {
        String sql = "SELECT id, name, email, status FROM tourists WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tourist tourist = new Tourist(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"));
                tourist.setAccountStatus(AccountStatus.valueOf(rs.getString("status")));
                return tourist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Tourist tourist) {
        tourist.setAccountStatus(AccountStatus.DELETED);
        updateStatus(tourist.getId(), AccountStatus.DELETED);
    }

    @Override
    public void permanentDelete(String id) {
        // Quality requirement: permanent deletion (status set to DELETED and row removed)
        // First update status to DELETED
        updateStatus(id, AccountStatus.DELETED);
        // Then physically delete from the database
        String sql = "DELETE FROM tourists WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // In case of failure, rollback is handled by the controller (opt block in sequence)
            throw new RuntimeException("Permanent deletion failed", e);
        }
    }

    private void updateStatus(String id, AccountStatus status) {
        String sql = "UPDATE tourists SET status = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rollback transaction in case of connection interruption (from sequence diagram).
     * This method is invoked when the optional "Connection to server ETOUR interrupted" occurs.
     * Assumption: We are using a transaction that can be rolled back.
     */
    public void rollbackTransaction() {
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.getAutoCommit()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}