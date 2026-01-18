package com.system;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

/**
 * SystemState class representing a state of the system.
 * Attributes: stateId, timestamp, data.
 * Methods: restore.
 */
public class SystemState {
    private String stateId;
    private LocalDateTime timestamp;
    private Map<String, Object> data;

    public SystemState(String stateId) {
        this.stateId = stateId;
        this.timestamp = LocalDateTime.now();
        this.data = new HashMap<>();
    }

    public void addData(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getStateId() {
        return stateId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void restore() {
        // Logic to restore system to this state
        System.out.println("Restoring system state: " + stateId);
    }
}