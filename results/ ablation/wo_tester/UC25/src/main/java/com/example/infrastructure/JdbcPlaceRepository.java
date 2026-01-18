package com.example.infrastructure;

import com.example.domain.Place;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * JDBC implementation of PlaceRepository.
 */
public class JdbcPlaceRepository implements PlaceRepository {
    private DataSource dataSource;

    public JdbcPlaceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Place> findAll() {
        List<Place> places = new ArrayList<>();
        String sql = "SELECT * FROM places";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                places.add(new Place(id, name));
            }
        } catch (SQLException e) {
            // In a real application, we might throw a custom exception.
            e.printStackTrace();
        }
        return places;
    }
}