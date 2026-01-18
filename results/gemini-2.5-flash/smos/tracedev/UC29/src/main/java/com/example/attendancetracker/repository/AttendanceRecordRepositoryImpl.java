package com.example.attendancetracker.repository;

import com.example.attendancetracker.model.AttendanceRecord;
import com.example.attendancetracker.model.AttendanceStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * In-memory implementation of AttendanceRecordRepository for demonstration.
 * Simulates database operations.
 */
public class AttendanceRecordRepositoryImpl implements AttendanceRecordRepository {
    // Mimics a database table where the key is a unique identifier (studentId + date)
    // For simplicity, using a String key composed of studentId_date_hash
    private final Map<String, AttendanceRecord> attendanceRecords = new HashMap<>();

    public AttendanceRecordRepositoryImpl() {
        // Pre-populate with some mock data if needed for testing `findByDate`
        // Note: For real applications, use a proper date utility for comparison
        // to handle time components if Date objects are not normalized.
        // Here, we assume dates are normalized for daily records.
    }

    /**
     * Helper to create a unique key for an attendance record, treating studentId + date as a composite key.
     * @param studentId Student's ID.
     * @param date Record date.
     * @return A unique string key.
     */
    private String generateCompositeKey(String studentId, Date date) {
        return studentId + "_" + date.getTime(); // Using getTime() for date comparison
    }

    @Override
    public AttendanceRecord save(AttendanceRecord record) {
        String key = generateCompositeKey(record.getStudentId(), record.recordDate);
        if (attendanceRecords.containsKey(key)) {
            // Update existing record
            AttendanceRecord existing = attendanceRecords.get(key);
            existing.status = record.status;
            existing.isNotified = record.isNotified;
            System.out.println("[Database] Updated attendance record: " + existing);
            return existing;
        } else {
            // Save new record
            attendanceRecords.put(key, record);
            System.out.println("[Database] Inserted new attendance record: " + record);
            return record;
        }
    }

    @Override
    public List<AttendanceRecord> findByDate(Date date) {
        System.out.println("[Database] SELECT * FROM AttendanceRecords WHERE date = " + date.toInstant().toString().substring(0,10));
        // Filter records by date, ignoring time component for daily records
        return attendanceRecords.values().stream()
                .filter(record -> Objects.equals(record.recordDate.toInstant().toEpochMilli() / 1000 / 60 / 60 / 24,
                                                 date.toInstant().toEpochMilli() / 1000 / 60 / 60 / 24))
                .collect(Collectors.toList());
    }

    @Override
    public void updateNotificationStatus(String studentId, Date date, boolean isNotified) {
        String key = generateCompositeKey(studentId, date);
        AttendanceRecord record = attendanceRecords.get(key);
        if (record != null) {
            record.isNotified = isNotified;
            System.out.println("[Database] UPDATE AttendanceRecords SET isNotified=" + isNotified + " WHERE studentId=" + studentId + " AND date=" + date.toInstant().toString().substring(0,10));
        } else {
            System.out.println("[Database] Record not found for studentId: " + studentId + " on date: " + date.toInstant().toString().substring(0,10));
        }
    }
}