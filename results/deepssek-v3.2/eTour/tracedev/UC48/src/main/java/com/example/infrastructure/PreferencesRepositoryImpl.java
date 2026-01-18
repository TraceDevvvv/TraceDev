package com.example.infrastructure;

import com.example.application.PreferencesRepository;
import com.example.domain.Preferences;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * Infrastructure implementation of PreferencesRepository.
 * Assumption: DataSource is a simple JDBC connection provider.
 */
public class PreferencesRepositoryImpl implements PreferencesRepository {
    // Simplified: using a Connection directly. In real scenario, DataSource would be used.
    private Connection dataSource;

    /**
     * Constructor.
     * @param dataSource the database connection
     */
    public PreferencesRepositoryImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Preferences findByUserId(String userId) {
        String sql = "SELECT language, currency, notification_enabled, dietary_restrictions FROM preferences WHERE user_id = ?";
        try (PreparedStatement stmt = dataSource.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPreferences(rs);
                } else {
                    // Return default preferences if not found (assumption)
                    return new Preferences("en", "USD", true, new ArrayList<>());
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Connection failed", e);
        }
    }

    @Override
    public void save(String userId, Preferences preferences) {
        String sql = "UPDATE preferences SET language = ?, currency = ?, notification_enabled = ?, dietary_restrictions = ? WHERE user_id = ?";
        try (PreparedStatement stmt = dataSource.prepareStatement(sql)) {
            stmt.setString(1, preferences.getLanguage());
            stmt.setString(2, preferences.getCurrency());
            stmt.setBoolean(3, preferences.isNotificationEnabled());
            // Assuming dietary restrictions stored as comma-separated string for simplicity
            String restrictions = String.join(",", preferences.getDietaryRestrictions());
            stmt.setString(4, restrictions);
            stmt.setString(5, userId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                // Insert if not present (assumption)
                String insertSql = "INSERT INTO preferences (user_id, language, currency, notification_enabled, dietary_restrictions) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = dataSource.prepareStatement(insertSql)) {
                    mapPreferencesToStatement(userId, preferences, insertStmt);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Connection failed", e);
        }
    }

    /**
     * Maps a ResultSet row to a Preferences object.
     * @param rs the ResultSet
     * @return a Preferences object
     * @throws SQLException if a database error occurs
     */
    private Preferences mapResultSetToPreferences(ResultSet rs) throws SQLException {
        String language = rs.getString("language");
        String currency = rs.getString("currency");
        boolean notificationEnabled = rs.getBoolean("notification_enabled");
        String restrictionsStr = rs.getString("dietary_restrictions");
        List<String> restrictions = new ArrayList<>();
        if (restrictionsStr != null && !restrictionsStr.isEmpty()) {
            for (String r : restrictionsStr.split(",")) {
                restrictions.add(r.trim());
            }
        }
        return new Preferences(language, currency, notificationEnabled, restrictions);
    }

    /**
     * Helper to map preferences to a PreparedStatement for insert.
     * @param userId the user id
     * @param preferences the preferences
     * @param stmt the PreparedStatement
     * @throws SQLException if a database error occurs
     */
    private void mapPreferencesToStatement(String userId, Preferences preferences, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, userId);
        stmt.setString(2, preferences.getLanguage());
        stmt.setString(3, preferences.getCurrency());
        stmt.setBoolean(4, preferences.isNotificationEnabled());
        String restrictions = String.join(",", preferences.getDietaryRestrictions());
        stmt.setString(5, restrictions);
    }

    /**
     * Custom exception for database errors.
     */
    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}