package com.example.model;

import java.time.LocalDateTime;

/**
 * Class representing a registration request.
 */
public class RegistrationRequest {
    private String requestId;
    private String studentName;
    private RequestStatus status;
    private LocalDateTime createdAt;

    public RegistrationRequest(String requestId, String studentName, LocalDateTime createdAt) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.status = RequestStatus.PENDING;
        this.createdAt = createdAt;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStudentName() {
        return studentName;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus newStatus) {
        this.status = newStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}