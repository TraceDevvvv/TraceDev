/**
 * Data model class representing a student registration request.
 * This encapsulates all information about a registration request.
 */
import java.util.Date;
public class RegistrationRequest {
    private String requestId;
    private String studentName;
    private String studentId;
    private String email;
    private Date requestDate;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    public RegistrationRequest(String requestId, String studentName, 
                              String studentId, String email, Date requestDate) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.requestDate = requestDate;
        this.status = "PENDING";
    }
    // Getters and setters
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
        // Validate status value
        if (status == null || (!status.equals("PENDING") && !status.equals("APPROVED") && !status.equals("REJECTED"))) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
        this.status = status;
    }
    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "requestId='" + requestId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", email='" + email + '\'' +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}