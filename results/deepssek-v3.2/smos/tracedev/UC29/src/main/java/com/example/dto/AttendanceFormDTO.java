package com.example.dto;

import com.example.entity.AttendanceRecord;
import com.example.entity.Student;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Data Transfer Object for attendance form data.
 * Contains date and lists of absent and present students.
 */
public class AttendanceFormDTO {
    public Date date;
    public List<StudentDTO> absentStudents;
    public List<StudentDTO> presentStudents;

    public AttendanceFormDTO() {
        this.absentStudents = new ArrayList<>();
        this.presentStudents = new ArrayList<>();
    }

    public AttendanceFormDTO(Date date, List<StudentDTO> absentStudents, List<StudentDTO> presentStudents) {
        this.date = date;
        this.absentStudents = absentStudents != null ? absentStudents : new ArrayList<>();
        this.presentStudents = presentStudents != null ? presentStudents : new ArrayList<>();
    }

    /**
     * Converts this DTO to an AttendanceRecord entity.
     * Assumes conversion logic from StudentDTO to Student/AbsentStudent/PresentStudent.
     */
    public AttendanceRecord toEntity() {
        // This is a simplified conversion. In a real application, you would map
        // StudentDTO to Student entities and create AbsentStudent/PresentStudent objects.
        AttendanceRecord record = new AttendanceRecord();
        record.setDate(this.date);
        record.setTimestamp(new Date()); // current timestamp
        // Note: actual conversion of StudentDTO lists to AbsentStudent/PresentStudent lists
        // would require additional logic and dependencies (e.g., StudentRepository).
        // For simplicity, we leave them empty or null.
        return record;
    }
}