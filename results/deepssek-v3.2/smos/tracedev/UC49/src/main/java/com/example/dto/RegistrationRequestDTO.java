package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for RegistrationRequest.
 * Transfers data between service and presentation layers.
 */
public class RegistrationRequestDTO {
    private String studentId;
    private String studentName;
    private Date requestDate;
    private String status;

    public RegistrationRequestDTO() {}

    public RegistrationRequestDTO(String studentId, String studentName, Date requestDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.requestDate = requestDate;
        this.status = status;
    }

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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}