package com.example;

/**
 * Simulates a data source (e.g., database).
 * Created to support UserRepositoryImpl.
 */
public class DataSource {
    public User getUser(String id) {
        // Dummy implementation
        return new User(id, "Dummy Name", "dummy@example.com", java.util.Collections.emptyMap());
    }

    public void saveUser(User user) {
        // Dummy implementation: just log
        System.out.println("Saving user: " + user.getId());
    }
}