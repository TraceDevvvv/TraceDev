package com.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the SMOS server archive.
 * REQ: Exit Conditions #2 - Connection status handling.
 */
public class Archive {
    private String connectionStatus; // "CONNECTED" or "INTERRUPTED"

    public Archive() {
        // Assume connected by default
        this.connectionStatus = "CONNECTED";
    }

    /**
     * Checks if the archive is connected.
     * Sequence diagram: Repository -> DB : isConnected()
     */
    public boolean isConnected() {
        return "CONNECTED".equals(connectionStatus);
    }

    /**
     * Searches for users in the archive.
     * Sequence diagram: Repository -> DB : search for users
     */
    public List<UserData> searchForUsers() {
        if (!isConnected()) {
            return new ArrayList<>();
        }
        // Simulate data retrieval
        List<UserData> data = new ArrayList<>();
        data.add(new UserData("U001", "Alice", "alice@example.com"));
        data.add(new UserData("U002", "Bob", "bob@example.com"));
        data.add(new UserData("U003", "Charlie", "charlie@example.com"));
        return data;
    }

    public void setConnectionStatus(String status) {
        this.connectionStatus = status;
    }
}