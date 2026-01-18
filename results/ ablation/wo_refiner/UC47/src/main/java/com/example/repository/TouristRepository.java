package com.example.repository;

import com.example.entity.TouristEntity;
import com.example.exception.ConnectionException;
import javax.sql.DataSource;
import java.util.Date;

/**
 * Repository implementation with transactional update and caching (REQ-015).
 */
public class TouristRepository implements ITouristRepository {
    private DataSource dataSource;
    private CacheManager cacheManager;

    public TouristRepository(DataSource dataSource, CacheManager cacheManager) {
        this.dataSource = dataSource;
        this.cacheManager = cacheManager;
    }

    @Override
    public TouristEntity findById(String userId) {
        // Simulate database lookup
        // In a real implementation, use dataSource to query the database.
        // For demo, return a mock entity.
        if ("testUser".equals(userId)) {
            return new TouristEntity(1L, userId, "John Doe", "john@example.com", "1234567890", "123 Main St", new java.util.Date(), new java.util.Date());
        }
        return null;
    }

    @Override
    public boolean update(TouristEntity tourist) throws ConnectionException {
        // Simulate transactional update within 500ms (REQ-015)
        long startTime = System.currentTimeMillis();
        try {
            // Simulate database update
            // If connection fails, throw ConnectionException (REQ-014)
            boolean connectionOk = simulateConnection();
            if (!connectionOk) {
                throw new ConnectionException("Server ETOUR interruption", new Date());
            }
            // Update logic here
            tourist.setUpdatedAt(new java.util.Date());
            // Invalidate cache if needed
            cacheManager.invalidate(tourist.getUserId());
            return true;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            if (duration > 500) {
                // Log warning: transaction took longer than 500ms
                System.out.println("Warning: Update transaction took " + duration + "ms");
            }
        }
    }

    private boolean simulateConnection() {
        // For demo, 90% chance of success
        return Math.random() > 0.1;
    }
}