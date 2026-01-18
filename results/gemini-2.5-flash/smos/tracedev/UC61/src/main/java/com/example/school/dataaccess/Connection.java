package com.example.school.dataaccess;

/**
 * Represents an established connection to the SMOS server.
 * This is a simple placeholder class to satisfy the type requirement in SmosClient.
 */
public class Connection {
    private String connectionId;
    private long timestamp;

    /**
     * Constructs a new Connection instance.
     * @param connectionId A unique identifier for the connection.
     */
    public Connection(String connectionId) {
        this.connectionId = connectionId;
        this.timestamp = System.currentTimeMillis();
    }

    public String getConnectionId() {
        return connectionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Connection{" +
               "connectionId='" + connectionId + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}