/**
 * StudentDelay.java
 *
 * This class represents a student delay record with attributes for student ID, name,
 * delay reason, and date. It serves as a data model for the application.
 */
public class StudentDelay {
    private String studentId;
    private String studentName;
    private String delayReason;
    private String date;
    // Constructor to initialize all fields
    public StudentDelay(String studentId, String studentName, String delayReason, String date) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.delayReason = delayReason;
        this.date = date;
    }
    // Getters for accessing fields
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDelayReason() { return delayReason; }
    public String getDate() { return date; }
    @Override
    public String toString() {
        return "StudentDelay [studentId=" + studentId + ", studentName=" + studentName +
               ", delayReason=" + delayReason + ", date=" + date + "]";
    }
}