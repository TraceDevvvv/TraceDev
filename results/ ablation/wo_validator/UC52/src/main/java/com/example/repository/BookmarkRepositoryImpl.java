package com.example.repository;

import com.example.model.Bookmark;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

/**
 * Implementation of BookmarkRepository using JDBC.
 */
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private DataSource dataSource;

    public BookmarkRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Bookmark findBookmarkByUserAndSite(String userId, String siteId) {
        String sql = "SELECT * FROM bookmarks WHERE user_id = ? AND site_id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, userId);
            stmt.setString(2, siteId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String bookmarkId = rs.getString("bookmark_id");
                    String fetchedUserId = rs.getString("user_id");
                    String fetchedSiteId = rs.getString("site_id");
                    Date createdAt = rs.getTimestamp("created_at");
                    
                    return new Bookmark(bookmarkId, fetchedUserId, fetchedSiteId, createdAt);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding bookmark", e);
        }
        
        return null;
    }

    @Override
    public void deleteBookmark(Bookmark bookmark) {
        String sql = "DELETE FROM bookmarks WHERE bookmark_id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, bookmark.getBookmarkId());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting bookmark", e);
        }
    }

    @Override
    public void saveBookmark(Bookmark bookmark) {
        // Implementation for saving a new bookmark
        // This method is included to satisfy the interface contract
        String sql = "INSERT INTO bookmarks (bookmark_id, user_id, site_id, created_at) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, bookmark.getBookmarkId());
            stmt.setString(2, bookmark.getUserId());
            stmt.setString(3, bookmark.getSiteId());
            stmt.setTimestamp(4, new Timestamp(bookmark.getCreatedAt().getTime()));
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error saving bookmark", e);
        }
    }
}