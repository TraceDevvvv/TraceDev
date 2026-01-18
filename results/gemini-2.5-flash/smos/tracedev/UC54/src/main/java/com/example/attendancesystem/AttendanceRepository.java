package com.example.attendancesystem;

import java.util.List;

/**
 * Interface for managing AttendanceRecord data.
 * Defined in the Infrastructure layer of the Class Diagram.
 */
public interface AttendanceRepository {
    /**
     * Saves a list of attendance records.
     * @param records The list of AttendanceRecord objects to save.
     */
    void save(List<AttendanceRecord> records);

    /**
     * Retrieves all attendance records.
     * (Added for demonstration/verification purposes in the Main class.)
     * @return A list of all stored attendance records.
     */
    List<AttendanceRecord> findAll();
}