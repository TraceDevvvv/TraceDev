package com.example.attendancesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In-memory implementation of AttendanceRepository for demonstration purposes.
 * Simulates data storage and retrieval without a real database.
 */
public class InMemoryAttendanceRepository implements AttendanceRepository {
    // In-memory list to store attendance records
    private List<AttendanceRecord> records = new ArrayList<>();

    /**
     * Saves a list of new attendance records to the in-memory store.
     * @param newRecords The list of AttendanceRecord objects to save.
     */
    @Override
    public void save(List<AttendanceRecord> newRecords) {
        System.out.println("[AttendanceRepository] Saving " + newRecords.size() + " attendance records.");
        // Simulate a small delay for realism
        try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        this.records.addAll(newRecords); // Add all new records
        newRecords.forEach(r -> System.out.println("    Saved: " + r));
    }

    /**
     * Retrieves an unmodifiable list of all attendance records stored.
     * @return A list of all stored attendance records.
     */
    @Override
    public List<AttendanceRecord> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(records));
    }
}