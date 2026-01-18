package com.example.school.application;

import com.example.school.dataaccess.IStudentRepository;
import com.example.school.domain.Student;
import com.example.school.domain.StudentAcademicRecord;
import com.example.school.dto.AcademicRecordItemDTO;
import com.example.school.dto.ChildAcademicSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ParentService acts as the application layer for parent-related functionalities.
 * It orchestrates calls to the data access layer and transforms domain objects into DTOs.
 */
public class ParentService {
    private IStudentRepository studentRepository;

    /**
     * Constructs a new ParentService.
     * @param studentRepository The repository for accessing student data.
     */
    public ParentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves the academic summary for a specific child of a parent.
     *
     * @param parentId The ID of the parent requesting the summary.
     * @param childId The ID of the child whose academic summary is requested.
     * @return A ChildAcademicSummaryDTO containing the child's academic details.
     * @throws IllegalArgumentException If the child does not belong to the specified parent or if student/child is not found.
     */
    public ChildAcademicSummaryDTO getChildAcademicSummary(String parentId, String childId) {
        System.out.println("ParentService: Getting academic summary for childId: " + childId + " under parentId: " + parentId);

        // 1. Fetch student details
        Student student = studentRepository.getStudentById(childId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + childId + " not found.");
        }

        // 2. Validate if the child belongs to the parent (crucial for security/authorization)
        if (!student.getParentId().equals(parentId)) {
            // This is a critical security check to prevent a parent from viewing another parent's child's records
            throw new IllegalArgumentException("Child with ID " + childId + " does not belong to parent with ID " + parentId);
        }

        // 3. Fetch academic records for the student
        List<StudentAcademicRecord> academicRecords = studentRepository.getStudentAcademicRecords(childId);

        // 4. Create and return the DTO
        return createChildAcademicSummaryDTO(student, academicRecords);
    }

    /**
     * Converts Student and StudentAcademicRecord domain objects into a ChildAcademicSummaryDTO.
     *
     * @param student The Student domain object.
     * @param academicRecords A list of StudentAcademicRecord domain objects.
     * @return A ChildAcademicSummaryDTO.
     */
    protected ChildAcademicSummaryDTO createChildAcademicSummaryDTO(Student student, List<StudentAcademicRecord> academicRecords) {
        System.out.println("ParentService: Creating ChildAcademicSummaryDTO.");

        List<AcademicRecordItemDTO> academicRecordDTOs = academicRecords.stream()
                .map(record -> new AcademicRecordItemDTO(
                        record.getRecordDate(),
                        record.getAbsences(),
                        record.getDisciplinaryNotes(),
                        record.getDelayCount(),
                        record.getJustification()
                ))
                .collect(Collectors.toList());

        return new ChildAcademicSummaryDTO(
                student.getStudentId(),
                student.getName(),
                academicRecordDTOs
        );
    }
}