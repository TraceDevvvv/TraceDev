package com.example.attendancetracker.repository;

import com.example.attendancetracker.model.AttendanceRecord;
import java.util.Date;
import java.util.List;

/**
 * Interface for Data Access operations related to AttendanceRecord.
 * <<Repository>> pattern.
 */
public interface AttendanceRecordRepository {
    /**
     * Saves a new attendance record or updates an existing one.
     * @param record The attendance record to save.
     * @return The saved/updated attendance record, potentially with an assigned ID.
     */
    AttendanceRecord save(AttendanceRecord record);

    /**
     * Finds all attendance records for a specific date.
     * @param date The date to query for.
     * @return A list of attendance records for the given date.
     */
    List<AttendanceRecord> findByDate(Date date);

    /**
     * Updates the notification status for a specific attendance record.
     * @param studentId The ID of the student.
     * @param date The date of the attendance record.
     * @param isNotified The new notification status.
     */
    void updateNotificationStatus(String studentId, Date date, boolean isNotified);
}