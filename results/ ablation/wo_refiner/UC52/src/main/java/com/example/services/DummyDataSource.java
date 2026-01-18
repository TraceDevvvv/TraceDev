package com.example.serv;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Dummy implementation for demonstration.
 */
public class DummyDataSource implements IDataSource {
    private boolean connected = true;

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        System.out.println("Executing query: " + query);
        // Return a dummy result set.
        // In real implementation, we would return actual ResultSet.
        // For simplicity, we throw unsupported.
        throw new UnsupportedOperationException("DummyDataSource does not provide real ResultSet.");
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}