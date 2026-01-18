package com.example.bookmark;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concrete implementation of the BookmarkRepository interface.
 * Simulates database interaction for bookmark storage and retrieval.
 * Implements REQ-001 by throwing ConnectionException.
 *
 * Relationship: BookmarkRepository <|.. BookmarkRepositoryImpl (implements)
 * Relationship: BookmarkRepositoryImpl ..> ConnectionException (throws)
 */
public class BookmarkRepositoryImpl implements BookmarkRepository {

    // Simulates a database connection, though its type is generic Object here.
    private Object databaseConnection;

    // A simple in-memory store to simulate the database
    // Key: bookmarkId (which for simplicity we assume is derived from siteId in the DTO for initial lookup), Value: Bookmark object
    private static final Map<String, Bookmark> bookmarkStore = new ConcurrentHashMap<>();

    // Static initializer block to populate some initial data
    static {
        // Initializing with sample bookmarks for demonstration
        bookmarkStore.put("s123", new Bookmark("s123", "siteA", "tourist1", new Date()));
        bookmarkStore.put("s456", new Bookmark("s456", "siteB", "tourist1", new Date()));
        bookmarkStore.put("s789", new Bookmark("s789", "siteC", "tourist2", new Date()));
    }

    /**
     * Constructs a BookmarkRepositoryImpl.
     * In a real application, databaseConnection would be a proper connection object (e.g., JDBC Connection).
     */
    public BookmarkRepositoryImpl() {
        // Initialize dummy database connection
        this.databaseConnection = new Object();
        System.out.println("[DB] Initialized BookmarkRepositoryImpl with dummy database connection.");
    }

    /**
     * Finds a bookmark by its unique identifier (bookmarkId).
     *
     * @param bookmarkId The unique ID of the bookmark.
     * @return An Optional containing the Bookmark if found, or an empty Optional otherwise.
     */
    @Override
    public Optional<Bookmark> findById(String bookmarkId) {
        System.out.println("[DB] selectBookmark(" + bookmarkId + ")"); // Simulates database interaction
        return Optional.ofNullable(bookmarkStore.get(bookmarkId));
    }

    /**
     * Removes a bookmark identified by its unique ID.
     * Simulates database delete operation and potential connection errors.
     *
     * @param bookmarkId The unique ID of the bookmark to remove.
     * @throws ConnectionException If a database connection error occurs.
     */
    @Override
    public void remove(String bookmarkId) throws ConnectionException {
        // Simulate a connection error randomly or based on a condition
        if (Math.random() < 0.2) { // 20% chance of connection error
            System.err.println("[DB] connectionError: Simulating ConnectionException for " + bookmarkId);
            throw new ConnectionException("Failed to connect to the database to remove bookmark: " + bookmarkId);
        }

        System.out.println("[DB] deleteBookmark(" + bookmarkId + ")"); // Simulates database interaction
        Bookmark removedBookmark = bookmarkStore.remove(bookmarkId);
        if (removedBookmark == null) {
            System.out.println("[DB] Bookmark with ID " + bookmarkId + " not found for removal.");
            // In a real scenario, you might throw an EntityNotFoundException here
        } else {
            System.out.println("[DB] Bookmark " + bookmarkId + " successfully removed.");
        }
    }

    // For testing and demonstration, allow adding a bookmark
    public void addBookmark(Bookmark bookmark) {
        bookmarkStore.put(bookmark.getId(), bookmark);
    }
}