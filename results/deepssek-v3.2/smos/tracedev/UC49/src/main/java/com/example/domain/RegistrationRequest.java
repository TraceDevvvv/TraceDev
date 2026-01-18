package com.example.domain;

import java.util.Date;

/**
 * Domain entity representing a registration request.
 */
public class RegistrationRequest {
    private Long id;
    private String studentId;
    private String studentName;
    private Date requestDate;
    private String status;

    public RegistrationRequest() {}

    public RegistrationRequest(Long id, String studentId, String studentName, Date requestDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.requestDate = requestDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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