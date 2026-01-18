package com.etour.repository;

import com.etour.model.Bookmark;
import com.etour.datasource.DataSource;
import com.etour.server.ETOURServer;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of SiteRepository.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private DataSource dataSource;
    private ETOURServer etourServer;

    public SiteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.etourServer = new ETOURServer("etour://server");
    }

    /**
     * Saves a bookmark to the database.
     */
    @Override
    public void saveBookmark(Bookmark bookmark) {
        connectToServer();
        // In a real application, this would execute an INSERT query.
        System.out.println("Bookmark saved: " + bookmark.getBookmarkId());
        persistBookmark(bookmark);
    }

    /**
     * Finds bookmarks by tourist ID.
     */
    @Override
    public List<Bookmark> findBookmarksByTourist(String touristId) {
        connectToServer();
        // For demo, return an empty list.
        return new ArrayList<>();
    }

    /**
     * Connects to the ETOUR server.
     * Added to satisfy requirement Exit Conditions.
     */
    private void connectToServer() {
        boolean connectionEstablished = etourServer.establishConnection();
        if (connectionEstablished) {
            System.out.println("Connected to ETOUR server.");
        } else {
            System.out.println("Connection to ETOUR server failed.");
        }
    }

    /**
     * Persists the bookmark (as per sequence diagram message m12).
     * @param bookmark the bookmark to persist
     */
    private void persistBookmark(Bookmark bookmark) {
        // Simulate persistence logic
        System.out.println("Persisting bookmark: " + bookmark.getBookmarkId());
    }
}