package com.example.dto;

/**
 * Data transfer object for student delay data.
 * Part of RegisterDelayRequest.
 */
public class StudentDelayData {
    private String studentId;
    private String studentName;
    private int delayDuration;
    private String parentEmail;
    private String parentPhone;
    
    // Getters and setters
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public int getDelayDuration() {
        return delayDuration;
    }
    
    public void setDelayDuration(int delayDuration) {
        this.delayDuration = delayDuration;
    }
    
    public String getParentEmail() {
        return parentEmail;
    }
    
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
    
    public String getParentPhone() {
        return parentPhone;
    }
    
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
}