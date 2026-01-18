package com.example.repository;

import com.example.domain.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of user repository with database persistence
 */
public class UserRepository implements IUserRepository {
    private DataSource dataSource;
    // Using AtomicInteger for thread-safe ID generation
    private static final AtomicInteger idCounter = new AtomicInteger(1000);

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean save(User user) {
        // In a real implementation, this would save to database
        // For this example, we'll simulate successful save
        try {
            // Simulate database save operation
            // In real code, this would be:
            // Connection conn = dataSource.getConnection();
            // PreparedStatement stmt = conn.prepareStatement("INSERT INTO users ...");
            // return stmt.executeUpdate() > 0;
            
            // For demo purposes, simulate 90% success rate
            return Math.random() > 0.1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findByUsername(String username) {
        // In real implementation, query database for username
        // For demo, return null to simulate not found (for new registration)
        return null;
    }

    @Override
    public User findByEmail(String email) {
        // In real implementation, query database for email
        // For demo, return null to simulate not found (for new registration)
        return null;
    }

    @Override
    public int generateUserId() {
        // Generate unique user ID
        // Using atomic increment for thread safety
        return idCounter.incrementAndGet();
    }
}