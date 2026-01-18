/**
 * Represents a student's registration request.
 * This is a simple POJO (Plain Old Java Object) to hold student registration details.
 */
public class StudentRegistration {
    private String id;
    private String studentName;
    private String status; // e.g., PENDING, APPROVED, REJECTED
    // Constructor to initialize a new registration request, defaulting to PENDING status
    public StudentRegistration(String id, String studentName) {
        this.id = id;
        this.studentName = studentName;
        this.status = "PENDING"; // New requests are always pending by default
    }
    // Getter for the student's ID
    public String getId() {
        return id;
    }
    // Setter for the student's ID (though typically IDs are immutable)
    public void setId(String id) {
        this.id = id;
    }
    // Getter for the student's name
    public String getStudentName() {
        return studentName;
    }
    // Setter for the student's name
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    // Getter for the registration status
    public String getStatus() {
        return status;
    }
    // Setter for the registration status
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    // Provides a string representation of the StudentRegistration object
    public String toString() {
        return "StudentRegistration{" +
               "id='" + id + '\'' +
               ", studentName='" + studentName + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}