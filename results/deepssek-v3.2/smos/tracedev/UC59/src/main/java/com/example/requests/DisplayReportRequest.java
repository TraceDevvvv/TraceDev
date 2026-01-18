package com.example.requests;

/**
 * Request object for the Display Report use case.
 */
public class DisplayReportRequest {
    private String studentId;
    private String reportId;
    
    public DisplayReportRequest(String studentId, String reportId) {
        this.studentId = studentId;
        this.reportId = reportId;
    }
    
    public DisplayReportRequest(String studentId) {
        this(studentId, null);
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public String getReportId() {
        return reportId;
    }
}