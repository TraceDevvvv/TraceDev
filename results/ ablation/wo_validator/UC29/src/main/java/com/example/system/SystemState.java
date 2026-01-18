package com.example.system;

import java.time.LocalDateTime;

/**
 * Represents a snapshot of system state.
 */
public class SystemState {
    private int id;
    private String snapshotData;
    private LocalDateTime timestamp;

    public SystemState(int id, String snapshotData) {
        this.id = id;
        this.snapshotData = snapshotData;
        this.timestamp = LocalDateTime.now();
    }

    public String getSnapshotData() {
        return snapshotData;
    }

    public void setSnapshotData(String snapshotData) {
        this.snapshotData = snapshotData;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}