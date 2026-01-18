package com.example.school.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for a child's academic summary.
 * Used to transfer aggregated academic data for a child from the application layer to the presentation layer.
 */
public class ChildAcademicSummaryDTO {
    private String studentId;
    private String studentName;
    private List<AcademicRecordItemDTO> academicRecords;

    /**
     * Constructs a new ChildAcademicSummaryDTO.
     *
     * @param studentId The unique identifier of the student.
     * @param studentName The name of the student.
     * @param academicRecords A list of academic record items for the student.
     */
    public ChildAcademicSummaryDTO(String studentId, String studentName, List<AcademicRecordItemDTO> academicRecords) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicRecords = academicRecords;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<AcademicRecordItemDTO> getAcademicRecords() {
        return academicRecords;
    }
}