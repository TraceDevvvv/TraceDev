package com.atastaff.absencesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for student attendance status.
 * This object is used within the AbsenceRequest to indicate whether a specific student
 * is marked as absent or present.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class StudentAttendanceDTO {

    /**
     * The unique identifier of the student.
     */
    private String studentId;

    /**
     * A boolean flag indicating if the student is absent (true) or present (false).
     */
    private boolean isAbsent;
}