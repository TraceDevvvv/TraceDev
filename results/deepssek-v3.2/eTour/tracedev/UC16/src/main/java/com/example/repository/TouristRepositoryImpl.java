package com.example.repository;

import com.example.domain.Tourist;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TouristRepository using a DataSource.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DataSource dataSource;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tourist> findAll() {
        List<Tourist> tourists = new ArrayList<>();
        String sql = "SELECT id, name, email FROM tourists";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tourist tourist = new Tourist(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                tourists.add(tourist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tourists;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM tourists WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            // Exit Condition: close connection is handled by try-with-resources
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}