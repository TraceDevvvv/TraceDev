package com.example.app.infrastructure;

import com.example.app.Infrastructure;
import com.example.app.domain.AttendanceRecord;
import java.util.List;

/**
 * Interface for repository operations related to AttendanceRecord.
 */
public interface IAttendanceRepository extends Infrastructure {
    /**
     * Finds a list of attendance records for a specific register and date.
     * @param registerId The ID of the register.
     * @param date The date (as a formatted string, e.g., "YYYY-MM-DD") for which to retrieve attendance.
     * @return A list of AttendanceRecord objects.
     */
    List<AttendanceRecord> findByRegisterIdAndDate(String registerId, String date);
}