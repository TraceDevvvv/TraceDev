package com.smos.enrollment;

import java.io.Serializable;
import java.util.Date;

/**
 * RegistrationRequest class representing a student registration request in the system.
 * Contains request details, associated student, and status information.
 * Implements Serializable to support potential serialization needs.
 */
public class RegistrationRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String requestId;
    private Student student;
    private String status; // PENDING, APPROVED, REJECTED, CANCELLED
    private Date requestDate;
    private Date processedDate;
    private String processedBy; // Administrator ID who processed the request
    
    /**
     * Default constructor
     */
    public RegistrationRequest() {
        this.status = "PENDING";
        this.requestDate = new Date();
    }
    
    /**
     * Parameterized constructor for creating a new registration request
     * @param requestId Unique identifier for the request
     * @param student Student object associated with this request
     */
    public RegistrationRequest(String requestId, Student student) {
        this.requestId = requestId;
        this.student = student;
        this.status = "PENDING";
        this.requestDate = new Date();
    }
    
    /**
     * Full parameterized constructor for registration request
     * @param requestId Unique identifier for the request
     * @param student Student object associated with this request
     * @param requestDate Date when the request was made
     */
    public RegistrationRequest(String requestId, Student student, Date requestDate) {
        this.requestId = requestId;
        this.student = student;
        this.status = "PENDING";
        this.requestDate = requestDate != null ? requestDate : new Date();
    }
    
    // Getters and Setters
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getRequestDate() {
        return requestDate;
    }
    
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    public Date getProcessedDate() {
        return processedDate;
    }
    
    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }
    
    public String getProcessedBy() {
        return processedBy;
    }
    
    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }
    
    /**
     * Checks if the request is pending approval
     * @return true if status is PENDING, false otherwise
     */
    public boolean isPending() {
        return "PENDING".equals(status);
    }
    
    /**
     * Checks if the request has been approved
     * @return true if status is APPROVED, false otherwise
     */
    public boolean isApproved() {
        return "APPROVED".equals(status);
    }
    
    /**
     * Checks if the request has been rejected
     * @return true if status is REJECTED, false otherwise
     */
    public boolean isRejected() {
        return "REJECTED".equals(status);
    }
    
    /**
     * Approves the registration request
     * Updates status to APPROVED, sets processed date and administrator ID
     * Activates the associated student account
     * @param adminId ID of the administrator approving the request
     * @throws IllegalStateException if request is not in PENDING status
     */
    public void approveRequest(String adminId) {
        if (!isPending()) {
            throw new IllegalStateException("Cannot approve a request that is not in PENDING status. Current status: " + status);
        }
        
        this.status = "APPROVED";
        this.processedDate = new Date();
        this.processedBy = adminId;
        
        // Activate the student account
        if (student != null) {
            student.activateStudent();
        }
    }
    
    /**
     * Rejects the registration request
     * Updates status to REJECTED, sets processed date and administrator ID
     * Rejects the associated student enrollment
     * @param adminId ID of the administrator rejecting the request
     * @throws IllegalStateException if request is not in PENDING status
     */
    public void rejectRequest(String adminId) {
        if (!isPending()) {
            throw new IllegalStateException("Cannot reject a request that is not in PENDING status. Current status: " + status);
        }
        
        this.status = "REJECTED";
        this.processedDate = new Date();
        this.processedBy = adminId;
        
        // Reject the student enrollment
        if (student != null) {
            student.rejectStudent();
        }
    }
    
    /**
     * Cancels the registration request
     * Only pending requests can be cancelled
     * @throws IllegalStateException if request is not in PENDING status
     */
    public void cancelRequest() {
        if (!isPending()) {
            throw new IllegalStateException("Cannot cancel a request that is not in PENDING status. Current status: " + status);
        }
        
        this.status = "CANCELLED";
        this.processedDate = new Date();
    }
    
    /**
     * Validates the registration request data
     * @return true if request has valid data, false otherwise
     */
    public boolean isValid() {
        return requestId != null && !requestId.trim().isEmpty() &&
               student != null &&
               requestDate != null &&
               status != null && !status.trim().isEmpty();
    }
    
    /**
     * Returns a formatted string representation of the request date
     * @return formatted date string
     */
    public String getFormattedRequestDate() {
        if (requestDate == null) {
            return "N/A";
        }
        return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM", requestDate);
    }
    
    /**
     * Returns a formatted string representation of the processed date
     * @return formatted date string or "N/A" if not processed
     */
    public String getFormattedProcessedDate() {
        if (processedDate == null) {
            return "N/A";
        }
        return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM", processedDate);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registration Request ID: ").append(requestId);
        
        if (student != null) {
            sb.append("\nStudent: ").append(student.getFullName())
              .append(" (ID: ").append(student.getStudentId()).append(")");
        } else {
            sb.append("\nStudent: Not specified");
        }
        
        sb.append("\nStatus: ").append(status)
          .append("\nRequest Date: ").append(getFormattedRequestDate())
          .append("\nProcessed Date: ").append(getFormattedProcessedDate());
        
        if (processedBy != null && !processedBy.isEmpty()) {
            sb.append("\nProcessed By: ").append(processedBy);
        }
        
        return sb.toString();
    }
    
    /**
     * Returns a summary of the registration request for display purposes
     * @return short summary string
     */
    public String getSummary() {
        String studentName = (student != null) ? student.getFullName() : "Unknown";
        return String.format("Request %s - %s - Status: %s", 
            requestId, studentName, status);
    }
}