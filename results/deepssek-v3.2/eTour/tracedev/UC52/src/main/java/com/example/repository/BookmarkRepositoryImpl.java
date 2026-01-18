package com.example.repository;

import com.example.model.Bookmark;
import com.example.exception.ConnectionException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of BookmarkRepository.
 * Simulates a data source and connection checks.
 */
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private Map<String, Bookmark> bookmarkStore; // Simulated storage
    private boolean connectionAlive = true; // Simulate connection state
    private DataSource dataSource;

    public BookmarkRepositoryImpl() {
        bookmarkStore = new HashMap<>();
        // Pre-populate with some data for simulation
        bookmarkStore.put("bookmark1", new Bookmark("bookmark1", "tourist1", "site1"));
        bookmarkStore.put("bookmark2", new Bookmark("bookmark2", "tourist2", "site2"));
    }

    @Override
    public Bookmark findBookmark(String touristId, String siteId) {
        if (!checkConnection()) {
            throw new ConnectionException("CONN_001", "Connection lost while finding bookmark");
        }
        // Search for a bookmark matching touristId and siteId
        for (Bookmark b : bookmarkStore.values()) {
            if (b.getTouristId().equals(touristId) && b.getSiteId().equals(siteId)) {
                return b;
            }
        }
        return null; // Not found
    }

    @Override
    public boolean deleteBookmark(String bookmarkId) {
        if (!checkConnection()) {
            throw new ConnectionException("CONN_002", "Connection lost while deleting bookmark");
        }
        if (bookmarkStore.containsKey(bookmarkId)) {
            bookmarkStore.remove(bookmarkId);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkConnection() {
        // Simulate connection check
        return connectionAlive;
    }

    // For testing: simulate connection interruption
    public void setConnectionAlive(boolean alive) {
        this.connectionAlive = alive;
    }

    // For testing: add a bookmark
    public void addBookmark(Bookmark bookmark) {
        bookmarkStore.put(bookmark.getBookmarkId(), bookmark);
    }
}