package com.example.exception;

/**
 * Dummy class representing a database connection.
 * This is referenced by DatabaseTagRepository.
 */
public class DatabaseConnection {
    // In a real implementation, this would hold connection details.
    private String url;
    private String user;

    public DatabaseConnection(String url, String user) {
        this.url = url;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }
}