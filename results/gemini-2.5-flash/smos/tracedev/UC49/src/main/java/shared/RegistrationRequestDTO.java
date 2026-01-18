package shared;

import java.util.Date;

/**
 * Data Transfer Object for RegistrationRequest, used to transfer data between layers,
 * typically from Application/Domain to Presentation.
 */
public class RegistrationRequestDTO {
    private String requestId;
    private String studentName;
    private Date submissionDate;

    /**
     * Constructs a new RegistrationRequestDTO.
     * @param requestId The unique identifier for the registration request.
     * @param studentName The name of the student making the request.
     * @param submissionDate The date the request was submitted.
     */
    public RegistrationRequestDTO(String requestId, String studentName, Date submissionDate) {
        this.requestId = requestId;
        this.studentName = studentName;
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
     * Gets the submission date.
     * @return The submission date.
     */
    public Date getSubmissionDate() {
        return submissionDate;
    }

    @Override
    public String toString() {
        return "Request ID: " + requestId +
               ", Student: " + studentName +
               ", Submitted: " + submissionDate;
    }
}