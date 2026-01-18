/**
 * Represents a student in the system.
 * Contains student details and parent email for notifications.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentEmail;
    public Student(String studentId, String name, String parentEmail) {
        this.studentId = studentId;
        this.name = name;
        this.parentEmail = parentEmail;
    }
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getParentEmail() { return parentEmail; }
}