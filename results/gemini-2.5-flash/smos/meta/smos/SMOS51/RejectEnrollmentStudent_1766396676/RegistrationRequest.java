package com.example.smos.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a student's registration request to the system.
 * Each request is associated with a specific student and has a unique identifier.
 */
public class RegistrationRequest {

    /**
     * Enum to define the possible statuses of a registration request.
     */
    public enum RequestStatus {
        PENDING,    // The request is awaiting review.
        APPROVED,   // The request has been approved.
        REJECTED    // The request has been rejected.
    }

    private final String requestId;
    private final Student student;
    private RequestStatus status;
    private long requestTimestamp; // Timestamp when the request was created

    /**
     * Constructs a new RegistrationRequest with a generated UUID and PENDING status.
     *
     * @param student The student associated with this registration request.
     * @throws IllegalArgumentException if the student is null.
     */
    public RegistrationRequest(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for a registration request.");
        }
        this.requestId = UUID.randomUUID().toString(); // Generate a unique ID for the request
        this.student = student;
        this.status = RequestStatus.PENDING; // Default status is PENDING
        this.requestTimestamp = System.currentTimeMillis(); // Record creation time
    }

    /**
     * Returns the unique identifier of this registration request.
     *
     * @return The request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Returns the student associated with this registration request.
     *
     * @return The Student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the current status of this registration request.
     *
     * @return The RequestStatus (PENDING, APPROVED, or REJECTED).
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of this registration request.
     *
     * @param status The new status for the request.
     * @throws IllegalArgumentException if the status is null.
     */
    public void setStatus(RequestStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Request status cannot be null.");
        }
        this.status = status;
    }

    /**
     * Returns the timestamp when this request was created.
     *
     * @return The creation timestamp in milliseconds.
     */
    public long getRequestTimestamp() {
        return requestTimestamp;
    }

    /**
     * Compares this RegistrationRequest object to the specified object. The result is true if and only if
     * the argument is not null and is a RegistrationRequest object that represents the same request ID.
     *
     * @param o The object to compare this RegistrationRequest against.
     * @return true if the given object represents a RegistrationRequest equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return requestId.equals(that.requestId);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }

    /**
     * Provides a string representation of the RegistrationRequest object.
     *
     * @return A string containing the request ID, student details, and current status.
     */
    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "requestId='" + requestId + '\'' +
               ", student=" + student.getName() + " (" + student.getStudentId() + ')' +
               ", status=" + status +
               ", requestTimestamp=" + requestTimestamp +
               '}';
    }
}