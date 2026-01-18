package com.example.repository;

import com.example.model.RegisteredUser;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Simulated database user repository.
 */
public class DatabaseUserRepository implements UserRepository {
    // In-memory store for demo purposes (assumption: should be replaced with real DB)
    private Map<String, RegisteredUser> userStore = new HashMap<>();
    private DataSource dataSource;

    public DatabaseUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize with some dummy data
        userStore.put("user1", new RegisteredUser("alice", "alice@example.com", true));
        userStore.put("user2", new RegisteredUser("bob", "bob@example.com", true));
    }

    @Override
    public RegisteredUser findById(String userId) {
        return userStore.get(userId);
    }

    @Override
    public boolean update(RegisteredUser user) {
        // In a real implementation, this would update the database.
        // Here we assume the user is already in the map.
        String username = user.getUsername();
        for (Map.Entry<String, RegisteredUser> entry : userStore.entrySet()) {
            if (entry.getValue().getUsername().equals(username)) {
                userStore.put(entry.getKey(), user);
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to add a user (for testing/demo).
     * @param userId The user ID.
     * @param user The user object.
     */
    public void addUser(String userId, RegisteredUser user) {
        userStore.put(userId, user);
    }
}