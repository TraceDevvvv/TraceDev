package domain;

import java.util.Date;

/**
 * Represents a registration request in the domain layer.
 */
public class RegistrationRequest {
    private String requestId;
    private String studentName;
    private String status; // e.g., "pending", "approved", "rejected"
    private Date submissionDate;

    /**
     * Constructs a new RegistrationRequest.
     * @param requestId The unique identifier for the request.
     * @param studentName The name of the student.
     * @param status The current status of the request.
     * @param submissionDate The date the request was submitted.
     */
    public RegistrationRequest(String requestId, String studentName, String status, Date submissionDate) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    /**
     * Gets the request ID.
     * @return The request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Gets the student name.
     * @return The student name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the status of the request.
     * @return The status (e.g., "pending").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the submission date.
     * @return The submission date.
     */
    public Date getSubmissionDate() {
        return submissionDate;
    }
}