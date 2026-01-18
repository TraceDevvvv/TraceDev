package com.etour.repository;

import com.etour.dto.BookmarkDTO;
import com.etour.exception.ConnectionException;
import com.etour.server.ETourServer;
import javax.sql.DataSource;
import java.util.List;

/**
 * Concrete implementation of BookmarkRepository that connects to an ETourServer.
 */
public class DatabaseBookmarkRepository implements BookmarkRepository {
    private DataSource dataSource;
    private ETourServer eTourServer;

    // Constructor with DataSource and ETourServer dependency
    public DatabaseBookmarkRepository(DataSource dataSource, ETourServer eTourServer) {
        this.dataSource = dataSource;
        this.eTourServer = eTourServer;
    }

    /**
     * Finds all bookmarks for a user by delegating to ETourServer.
     * Simulates connection check: if connection is OK, returns data; otherwise throws ConnectionException.
     */
    @Override
    public List<BookmarkDTO> findAllByUserId(int userId) throws ConnectionException {
        // Simulate connection check (in real scenario, check dataSource connection)
        // For simplicity, assume we directly call eTourServer.
        
        // Check if connection to eTour server is available
        if (!isConnectionAvailable()) {
            throw new ConnectionException("Connection to eTour server not available");
        }
        
        // Get bookmark data from eTour server (simulate possible connection interruption)
        try {
            return eTourServer.getBookmarkData(userId);
        } catch (Exception e) {
            // If any exception occurs (simulating connection interruption), throw ConnectionException
            throw new ConnectionException("Connection interrupted while fetching bookmarks");
        }
    }
    
    // Helper method to simulate connection availability check
    private boolean isConnectionAvailable() {
        // In a real implementation, this would check the DataSource connection.
        // For simulation, we assume a simple check: always true for normal flow.
        // However, the sequence diagram allows for "connection not available" alternative.
        // We'll rely on an external condition (maybe a system property) to simulate failure.
        String connectionAvailable = System.getProperty("connection.available", "true");
        return Boolean.parseBoolean(connectionAvailable);
    }
}