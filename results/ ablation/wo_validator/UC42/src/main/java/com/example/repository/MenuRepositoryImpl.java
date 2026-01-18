
package com.example.repository;

import com.example.model.DailyMenu;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

/**
 * JDBC implementation of MenuRepository.
 * Follows sequence diagram interactions with database.
 */
public class MenuRepositoryImpl implements MenuRepository {
    private DataSource dataSource;

    public MenuRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<DailyMenu> findByDayOfWeek(String dayOfWeek) {
        String sql = "SELECT * FROM daily_menu WHERE day = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dayOfWeek);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DailyMenu menu = mapRowToDailyMenu(rs);
                return Optional.of(menu);
            }
        } catch (SQLTimeoutException e) {
            // Connection interruption (sequence diagram opt block)
            throw new RuntimeException("ETOUR server connection lost", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(DailyMenu menu) {
        String sql = "DELETE FROM daily_menu WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, menu.getId());
            stmt.executeUpdate();
        } catch (SQLTimeoutException e) {
            throw new RuntimeException("ETOUR server connection lost", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public DailyMenu save(DailyMenu menu) {
        // Implementation not required for delete sequence but included for interface completeness.
        // Assumed to be an insert or update.
        String sql = "INSERT INTO daily_menu (id, day, date) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, menu.getId());
            stmt.setString(2, menu.getDayOfWeek());
            stmt.setDate(3, new java.sql.Date(menu.getDate().getTime()));
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                menu.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return menu;
    }

    private DailyMenu mapRowToDailyMenu(ResultSet rs) throws SQLException {
        DailyMenu menu = new DailyMenu();
        menu.setId(rs.getLong("id"));
        menu.setDayOfWeek(rs.getString("day"));
        menu.setDate(rs.getDate("date"));
        // Note: menu items would be loaded separately in a real scenario.
        return menu;
    }
}
