/**
Represents a student registration request in the system.
This class serves as a data model with fields for student ID, name, email, and status.
It includes getter and setter methods for each field.
*/
public class RegistrationRequest {
    private String studentId;
    private String name;
    private String email;
    private String status; // e.g., "PENDING", "ACTIVATED", "REJECTED"
    /**
     * Constructor to initialize a registration request.
     * @param studentId Unique identifier for the student
     * @param name Full name of the student
     * @param email Email address of the student
     * @param status Current status of the request
     */
    public RegistrationRequest(String studentId, String name, String email, String status) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.status = status;
    }
    // Getters and setters for all fields
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}