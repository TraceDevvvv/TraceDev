package com.example.controller;

import com.example.dto.LoginDTO;
import com.example.dto.ClassDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.ReportRequestDTO;
import com.example.dto.StudentDTO;
import com.example.service.ReportService;
import com.example.validator.ReportValidator;
import java.util.List;

/**
 * Controller for report card operations.
 * Constraint: System must display report card accurately and promptly (response time < 2 seconds)
 */
public class ReportCardController {
    private ReportService reportService;
    private ReportValidator reportValidator;

    public ReportCardController(ReportService reportService, ReportValidator reportValidator) {
        this.reportService = reportService;
        this.reportValidator = reportValidator;
    }

    /**
     * Validates login credentials (simplified for this example).
     */
    public boolean validateCredentials(LoginDTO credentials) {
        // Simplified validation: assume any non-empty credentials are valid.
        return credentials != null &&
               credentials.getUsername() != null && !credentials.getUsername().isEmpty() &&
               credentials.getPassword() != null && !credentials.getPassword().isEmpty();
    }

    public List<ClassDTO> displayClassesByYear(String academicYear) {
        return reportService.getClassesByYear(academicYear);
    }

    public List<StudentDTO> displayStudentsByClass(String classId) {
        return reportService.getStudentsByClass(classId);
    }

    public ReportCardDTO generateReport(ReportRequestDTO request) {
        // Generate report via service
        ReportCardDTO reportCardDTO = reportService.generateStudentReport(request);
        // Validate report for accuracy
        if (reportCardDTO != null && reportValidator.validateReport(reportCardDTO)) {
            return reportCardDTO;
        } else {
            // If validation fails, return null or throw exception; here we return null.
            return null;
        }
    }

    public void logout() {
        // Perform logout operations, e.g., clear session
        System.out.println("Session terminated.");
    }
}