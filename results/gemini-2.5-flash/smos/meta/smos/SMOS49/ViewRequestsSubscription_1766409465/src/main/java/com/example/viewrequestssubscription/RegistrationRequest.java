package com.example.viewrequestssubscription;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a registration request made by a student that is yet to be activated.
 * This POJO (Plain Old Java Object) holds all necessary information about a student's
 * request to join the system.
 */
public class RegistrationRequest {

    private String requestId;
    private String studentId;
    private String studentName;
    private String studentEmail;
    private LocalDateTime requestDate;
    private RequestStatus status;

    /**
     * Enum to represent the status of a registration request.
     * For this use case, we are primarily interested in PENDING requests.
     */
    public enum RequestStatus {
        PENDING,    // Request has been submitted but not yet activated/approved
        APPROVED,   // Request has been approved and student account activated
        REJECTED    // Request has been rejected
    }

    /**
     * Constructs a new RegistrationRequest.
     *
     * @param requestId A unique identifier for this registration request.
     * @param studentId The unique identifier of the student making the request.
     * @param studentName The full name of the student.
     * @param studentEmail The email address of the student.
     * @param requestDate The date and time when the request was made.
     * @param status The current status of the registration request.
     */
    public RegistrationRequest(String requestId, String studentId, String studentName,
                               String studentEmail, LocalDateTime requestDate, RequestStatus status) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.requestDate = requestDate;
        this.status = status;
    }

    /**
     * Gets the unique identifier for this registration request.
     * @return The request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the unique identifier for this registration request.
     * @param requestId The new request ID.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the unique identifier of the student making the request.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the unique identifier of the student making the request.
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the full name of the student.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the full name of the student.
     * @param studentName The new student name.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets the email address of the student.
     * @return The student's email.
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets the email address of the student.
     * @param studentEmail The new student email.
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Gets the date and time when the request was made.
     * @return The request date.
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the date and time when the request was made.
     * @param requestDate The new request date.
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * Gets the current status of the registration request.
     * @return The request status.
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the registration request.
     * @param status The new request status.
     */
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two RegistrationRequest objects are considered equal if their requestId is the same.
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return Objects.equals(requestId, that.requestId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the requestId.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }

    /**
     * Returns a string representation of the RegistrationRequest object.
     * @return A string containing the request's details.
     */
    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "requestId='" + requestId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", studentEmail='" + studentEmail + '\'' +
               ", requestDate=" + requestDate +
               ", status=" + status +
               '}';
    }
}