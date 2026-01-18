package com.example.validator;

import com.example.dto.ReportCardDTO;

/**
 * Validator for report cards to satisfy quality requirement REQ-QUAL-001 (accuracy).
 */
public class ReportValidator {
    public boolean validateReport(ReportCardDTO report) {
        if (report == null) {
            return false;
        }
        // Basic validation: ensure required fields are present
        boolean validStudent = report.getStudentId() != null && !report.getStudentId().isEmpty() &&
                               report.getStudentName() != null && !report.getStudentName().isEmpty();
        boolean validMonths = report.getMonths() != null && !report.getMonths().isEmpty();
        // Check grade data for any invalid scores (assuming score between 0 and 100)
        boolean validGrades = true;
        if (report.getGradeData() != null) {
            for (var gradeDTO : report.getGradeData().values()) {
                if (gradeDTO.getScore() < 0 || gradeDTO.getScore() > 100) {
                    validGrades = false;
                    break;
                }
            }
        }
        // Check attendance data for any invalid percentages (between 0 and 100)
        boolean validAttendance = true;
        if (report.getAttendanceData() != null) {
            for (Double attendance : report.getAttendanceData().values()) {
                if (attendance < 0 || attendance > 100) {
                    validAttendance = false;
                    break;
                }
            }
        }
        return validStudent && validMonths && validGrades && validAttendance;
    }
}