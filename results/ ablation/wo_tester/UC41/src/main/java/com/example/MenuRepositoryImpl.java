package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Concrete repository that uses a DataSource and checks ETOUR server connection.
 * Implements transactional save for reliable data persistence.
 */
public class MenuRepositoryImpl implements MenuRepository {
    private DataSource dataSource;
    private ETOURServer server;

    public MenuRepositoryImpl(DataSource dataSource, ETOURServer server) {
        this.dataSource = dataSource;
        this.server = server;
    }

    @Override
    public DailyMenu findByDay(String dayOfWeek) {
        if (!server.isConnectionActive()) {
            System.err.println("Connection to server is not active.");
            return null;
        }
        String sql = "SELECT menu FROM daily_menus WHERE day=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dayOfWeek);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Assuming menu is stored as a comma-separated list
                String menuStr = rs.getString("menu");
                List<String> items = parseMenuItems(menuStr);
                return new DailyMenu(dayOfWeek, items);
            }
        } catch (SQLException e) {
            System.err.println("Database error in findByDay: " + e.getMessage());
        }
        return null;
    }

    public DailyMenu mapToDailyMenu() {
        // This method is called in sequence diagram message m10
        // It should map data (likely from database) to a DailyMenu object
        // For now, we'll implement a simple version that returns null
        // In a real scenario, this would contain mapping logic
        return null;
    }

    @Override
    public void save(DailyMenu menu) {
        if (!server.isConnectionActive()) {
            throw new IllegalStateException("Cannot save: server connection inactive.");
        }
        // Transactional save: using a transaction for reliability
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String sql = "UPDATE daily_menus SET menu=? WHERE day=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                String menuStr = String.join(",", menu.getMenuItems());
                stmt.setString(1, menuStr);
                stmt.setString(2, menu.getDayOfWeek());
                int rows = stmt.executeUpdate();
                if (rows == 0) {
                    // No existing row, perform insert
                    String insertSql = "INSERT INTO daily_menus (day, menu) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, menu.getDayOfWeek());
                        insertStmt.setString(2, menuStr);
                        insertStmt.executeUpdate();
                    }
                }
                conn.commit();
                System.out.println("Menu saved successfully for day: " + menu.getDayOfWeek());
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Save failed: " + e.getMessage());
            throw new RuntimeException("Save failed", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Failed to close connection: " + closeEx.getMessage());
                }
            }
        }
    }

    private List<String> parseMenuItems(String menuStr) {
        List<String> items = new ArrayList<>();
        if (menuStr != null && !menuStr.trim().isEmpty()) {
            String[] parts = menuStr.split(",");
            for (String part : parts) {
                items.add(part.trim());
            }
        }
        return items;
    }
}