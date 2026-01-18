package com.example.dto;

import java.util.Date;
import java.util.List;

/**
 * Request object for registering a delay.
 */
public class RegisterDelayRequest {
    private List<StudentDelayData> students;
    private Date entryDate;
    
    // Getters and setters
    public List<StudentDelayData> getStudents() {
        return students;
    }
    
    public void setStudents(List<StudentDelayData> students) {
        this.students = students;
    }
    
    public Date getEntryDate() {
        return entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}