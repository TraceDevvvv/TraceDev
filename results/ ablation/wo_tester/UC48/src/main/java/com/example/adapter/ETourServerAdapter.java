package com.example.adapter;

/**
 * Adapter for connecting to ETour server.
 */
public class ETourServerAdapter {
    private boolean connected = false;
    
    public boolean connect() {
        System.out.println("Attempting to connect to ETour server...");
        // Simulate connection - 90% success rate
        connected = Math.random() > 0.1;
        if (!connected) {
            throw new ETourServerException("Connection failed", 500);
        }
        System.out.println("Connected to ETour server successfully.");
        return connected;
    }
    
    public void disconnect() {
        System.out.println("Disconnecting from ETour server...");
        connected = false;
    }
    
    public boolean isConnected() {
        return connected;
    }

    // Missing sequence diagram participant: DB (Database)
    // The DB participant is referenced as a database type, but no specific class exists
    // We'll add a simple representation for completeness
    public static class Database {
        public Object getPreferenceData(String touristId) {
            System.out.println("Database returning preference data for tourist: " + touristId);
            // Simulate returning preference data
            return new java.util.HashMap<String, Object>();
        }
        
        public void storePreferenceData(Object data) {
            System.out.println("Database storing preference data: " + data);
        }
    }
}