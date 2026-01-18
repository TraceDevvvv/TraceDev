/**
 * Represents a late entry (delay) record.
 * Immutable data object for late entry information.
 */
public class LateEntry {
    private int id;
    private String studentName;
    private String course;
    private String date; // format YYYY-MM-DD
    private String reason;
    public LateEntry(int id, String studentName, String course, String date, String reason) {
        this.id = id;
        this.studentName = studentName;
        this.course = course;
        this.date = date;
        this.reason = reason;
    }
    public int getId() {
        return id;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getCourse() {
        return course;
    }
    public String getDate() {
        return date;
    }
    public String getReason() {
        return reason;
    }
    @Override
    public String toString() {
        return String.format("LateEntry[id=%d, student=%s, course=%s, date=%s, reason=%s]",
                id, studentName, course, date, reason);
    }
}