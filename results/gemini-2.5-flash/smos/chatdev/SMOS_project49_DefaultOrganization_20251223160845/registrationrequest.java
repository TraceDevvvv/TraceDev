/*
RegistrationRequest.java - A simple Plain Old Java Object (POJO) representing a single
registration request from a student.
*/
public class RegistrationRequest {
    private String studentId;
    private String studentName;
    private String requestDate;
    private boolean activated; // Indicates if the request has been activated
    /**
     * Constructor for RegistrationRequest.
     *
     * @param studentId The unique identifier for the student.
     * @param studentName The full name of the student.
     * @param requestDate The date when the registration request was made.
     */
    public RegistrationRequest(String studentId, String studentName, String requestDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.requestDate = requestDate;
        this.activated = false; // By default, a new request is not activated
    }
    // --- Getters for the properties ---
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public boolean isActivated() {
        return activated;
    }
    // --- Setters (if modification is needed, though not directly in this use case) ---
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    /**
     * Provides a string representation of the RegistrationRequest object,
     * formatted for display.
     *
     * @return A formatted string containing the student ID, name, and request date.
     */
    @Override
    public String toString() {
        return String.format("  Student ID: %-5s | Name: %-20s | Request Date: %s", studentId, studentName, requestDate);
    }
}