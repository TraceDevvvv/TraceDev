package com.example.repository;

import com.example.entity.Menu;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

/**
 * Implementation of MenuRepository using JDBC DataSource.
 */
public class MenuRepositoryImpl implements MenuRepository {
    private DataSource dataSource;

    // Constructor
    public MenuRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Menu> findByRestaurantIdAndDayOfWeek(Long restaurantId, String dayOfWeek) {
        String sql = "SELECT * FROM menu WHERE restaurant_id = ? AND day_of_week = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, restaurantId);
            stmt.setString(2, dayOfWeek);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Menu menu = new Menu(restaurantId, dayOfWeek);
                menu.setId(rs.getLong("id"));
                // Assuming items are stored as a comma-separated string; adjust as needed.
                String itemsStr = rs.getString("items");
                if (itemsStr != null) {
                    String[] itemsArray = itemsStr.split(",");
                    for (String item : itemsArray) {
                        menu.addItem(item.trim());
                    }
                }
                menu.setLastUpdated(rs.getTimestamp("last_updated"));
                return Optional.of(menu);
            }
        } catch (SQLException e) {
            // Log exception (assumed logging is handled elsewhere)
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Menu save(Menu menu) {
        String sql;
        boolean exists = existsByRestaurantIdAndDayOfWeek(menu.getRestaurantId(), menu.getDayOfWeek());

        if (exists) {
            sql = "UPDATE menu SET items = ?, last_updated = ? WHERE restaurant_id = ? AND day_of_week = ?";
        } else {
            sql = "INSERT INTO menu (restaurant_id, day_of_week, items, last_updated) VALUES (?, ?, ?, ?)";
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Convert items list to comma-separated string
            StringBuilder itemsBuilder = new StringBuilder();
            for (String item : menu.getItems()) {
                itemsBuilder.append(item).append(",");
            }
            String itemsStr = itemsBuilder.length() > 0 ? itemsBuilder.substring(0, itemsBuilder.length() - 1) : "";

            if (exists) {
                stmt.setString(1, itemsStr);
                stmt.setTimestamp(2, new Timestamp(menu.getLastUpdated().getTime()));
                stmt.setLong(3, menu.getRestaurantId());
                stmt.setString(4, menu.getDayOfWeek());
                stmt.executeUpdate();
                // No need to fetch generated key for update
            } else {
                stmt.setLong(1, menu.getRestaurantId());
                stmt.setString(2, menu.getDayOfWeek());
                stmt.setString(3, itemsStr);
                stmt.setTimestamp(4, new Timestamp(menu.getLastUpdated().getTime()));
                stmt.executeUpdate();
                // Retrieve generated key for new menu
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    menu.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            // Rethrow as runtime exception to simulate connection error (as per sequence diagram)
            throw new RuntimeException("Database connection error", e);
        }
        return menu;
    }

    @Override
    public boolean existsByRestaurantIdAndDayOfWeek(Long restaurantId, String dayOfWeek) {
        String sql = "SELECT COUNT(*) FROM menu WHERE restaurant_id = ? AND day_of_week = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, restaurantId);
            stmt.setString(2, dayOfWeek);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}