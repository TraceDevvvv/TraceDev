package com.example.exception;

/**
 * Database implementation of TagRepository.
 */
public class DatabaseTagRepository implements TagRepository {
    private DatabaseConnection dbConnection;

    public DatabaseTagRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean exists(String searchTags) {
        System.out.println("DatabaseTagRepository checking existence for: " + searchTags);
        // Simulate database check: assume tag exists if it contains "error"
        return searchTags != null && searchTags.contains("error");
    }

    @Override
    public State findPreviousState(String searchTags) {
        System.out.println("DatabaseTagRepository finding previous state for: " + searchTags);
        // Simulate fetching from database.
        State state = new State();
        state.setData(java.util.Map.of("previousTag", searchTags, "dbTimestamp", System.currentTimeMillis()));
        return state;
    }
}