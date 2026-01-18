package com.example.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a registration request made by a student.
 * This is a domain object responsible for its own state changes, e.g., marking itself as accepted.
 */
public class RegistrationRequest {
    private String id;
    private String studentName;
    private String studentEmail;
    private Date requestDate;
    private RegistrationStatus status;

    /**
     * Constructor for creating a new RegistrationRequest.
     * @param studentName The name of the student.
     * @param studentEmail The email of the student.
     */
    public RegistrationRequest(String studentName, String studentEmail) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the request
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.requestDate = new Date(); // Set current date as request date
        this.status = RegistrationStatus.PENDING; // Initially, all requests are pending
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    /**
     * Marks the registration request as accepted.
     */
    public void markAsAccepted() {
        // Assume business logic allows transitioning from PENDING to ACCEPTED
        if (this.status == RegistrationStatus.PENDING) {
            this.status = RegistrationStatus.ACCEPTED;
            System.out.println("RegistrationRequest " + id + " marked as ACCEPTED.");
        } else {
            System.out.println("Warning: RegistrationRequest " + id + " is not PENDING. Status remains " + this.status);
        }
    }

    // For debugging or logging purposes
    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "id='" + id + '\'' +
               ", studentName='" + studentName + '\'' +
               ", studentEmail='" + studentEmail + '\'' +
               ", requestDate=" + requestDate +
               ", status=" + status +
               '}';
    }
}