package com.example.registration;

/**
 * Represents a single registration request with details about the student
 * and its current status.
 */
public class RegistrationRequest {
    public String id; // Unique identifier for the registration request
    public String studentName; // Name of the student
    public String status; // Current status of the request (e.g., "PENDING", "REJECTED", "APPROVED")
    public String email; // Student's email address

    /**
     * Constructor to create a new RegistrationRequest instance.
     *
     * @param id The unique ID for this request.
     * @param studentName The name of the student.
     * @param email The email of the student.
     * @param status The initial status of the request.
     */
    public RegistrationRequest(String id, String studentName, String email, String status) {
        this.id = id;
        this.studentName = studentName;
        this.status = status;
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "id='" + id + '\'' +
               ", studentName='" + studentName + '\'' +
               ", status='" + status + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}