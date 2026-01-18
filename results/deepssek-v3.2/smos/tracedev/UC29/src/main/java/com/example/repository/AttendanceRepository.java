package com.example.repository;

import com.example.entity.AttendanceRecord;
import com.example.entity.AttendanceLog;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Repository for attendance records.
 * In a real application, this would be a Spring Data JPA repository or similar.
 */
public class AttendanceRepository {
    // Simulating in‑memory storage for demonstration.
    private List<AttendanceRecord> records = new ArrayList<>();
    private List<AttendanceLog> logs = new ArrayList<>();

    public AttendanceRecord save(AttendanceRecord record) {
        if (record.getId() == null) {
            record.setId("REC-" + System.currentTimeMillis());
        }
        records.add(record);
        // Also create a log entry
        AttendanceLog log = new AttendanceLog("LOG-" + System.currentTimeMillis(), record, "Record saved");
        logs.add(log);
        return record;
    }

    public List<AttendanceRecord> findByDate(Date date) {
        List<AttendanceRecord> result = new ArrayList<>();
        for (AttendanceRecord r : records) {
            if (r.getDate() != null && r.getDate().equals(date)) {
                result.add(r);
            }
        }
        return result;
    }

    public List<AttendanceLog> getRecentLogs() {
        // Return last 10 logs for simplicity
        int size = logs.size();
        return logs.subList(Math.max(0, size - 10), size);
    }

    /**
     * Marks notifications as pending (e.g., when connection is lost).
     */
    public void markNotificationPending() {
        // In a real implementation, you would update a flag in the database.
        System.out.println("AttendanceRepository: Notifications marked as pending.");
    }
}