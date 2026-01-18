package com.example.justification.presentation;

import com.example.justification.application.StudentJustificationService;
import com.example.justification.application.dto.AbsenceDisplayDTO;

import java.time.Year;
import java.util.List;

/**
 * Presentation layer controller for handling student justification requests.
 * It orchestrates calls to the application service layer based on UI input.
 */
public class StudentJustificationController {
    private final StudentJustificationService studentJustificationService;

    /**
     * Constructs a StudentJustificationController with a given StudentJustificationService.
     * @param service The application service for student justifications.
     */
    public StudentJustificationController(StudentJustificationService service) {
        this.studentJustificationService = service;
    }

    /**
     * Handles the request to view student justifications for a specific student.
     * This method retrieves the current academic year and delegates to the service.
     *
     * @param studentId The ID of the student whose justifications are to be viewed.
     * @return A list of AbsenceDisplayDTOs representing the student's absences and their justification status.
     */
    public List<AbsenceDisplayDTO> viewStudentJustifications(String studentId) {
        System.out.println("[Controller] viewStudentJustifications called for studentId: " + studentId);

        // Retrieves current academic year for absence lookup as per sequence diagram note.
        int currentYear = Year.now().getValue();
        System.out.println("[Controller] Current year determined as: " + currentYear);

        // Controller -> Service: getAbsencesWithJustificationStatus
        List<AbsenceDisplayDTO> justifications = studentJustificationService.getAbsencesWithJustificationStatus(studentId, currentYear);
        System.out.println("[Controller] Received " + justifications.size() + " DTOs from service.");

        return justifications;
    }
}