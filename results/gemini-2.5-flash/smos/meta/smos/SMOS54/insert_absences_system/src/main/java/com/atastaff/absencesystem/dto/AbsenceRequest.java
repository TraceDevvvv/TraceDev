package com.atastaff.absencesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for absence submission requests.
 * This object is used to encapsulate the data sent from the UI to the backend
 * when an ATA staff member saves absence information for a class.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class AbsenceRequest {

    /**
     * The unique identifier of the class for which absences are being recorded.
     */
    private String classId;

    /**
     * A list of StudentAttendanceDTO objects, each representing a student's attendance status
     * (present or absent) for the given class.
     */
    private List<StudentAttendanceDTO> attendanceList;
}