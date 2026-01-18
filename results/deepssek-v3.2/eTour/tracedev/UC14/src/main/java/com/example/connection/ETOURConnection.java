package com.example.connection;

import com.example.exception.ConnectionException;

/**
 * Simulates a connection to the ETOUR server.
 */
public class ETOURConnection {
    private boolean isConnected = false;
    private int failureCounter = 0; // for simulating intermittent failures

    public ETOURConnection() {
        // Simulate initial connection attempt
        connect();
    }

    /**
     * Simulates connecting to the server.
     */
    public void connect() {
        // In reality, this would establish a network connection.
        isConnected = true;
        System.out.println("ETOURConnection: Connected.");
    }

    /**
     * Simulates disconnecting from the server.
     */
    public void disconnect() {
        isConnected = false;
        System.out.println("ETOURConnection: Disconnected.");
    }

    /**
     * Checks if the connection is alive.
     * @return true if alive, false otherwise (may simulate timeout).
     */
    public boolean isAlive() {
        // Simulate occasional failure: every 3rd call fails
        failureCounter++;
        if (failureCounter % 3 == 0) {
            isConnected = false;
            System.out.println("ETOURConnection: isAlive() simulated failure.");
            return false;
        }
        return isConnected;
    }

    /**
     * Executes a query against the server.
     * @param queryString The query to execute.
     * @return A QueryResult object.
     * @throws ConnectionException If connection is lost during query.
     */
    public QueryResult query(String queryString) throws ConnectionException {
        if (!isConnected) {
            throw new ConnectionException("Not connected to ETOUR server.");
        }
        // Simulate query execution; occasionally throw ConnectionException
        if (Math.random() < 0.2) { // 20% chance of failure during query
            isConnected = false;
            throw new ConnectionException("Connection lost during query execution.");
        }
        System.out.println("ETOURConnection: Executing query: " + queryString);
        // Return a dummy QueryResult
        return new QueryResult() {};
    }
}