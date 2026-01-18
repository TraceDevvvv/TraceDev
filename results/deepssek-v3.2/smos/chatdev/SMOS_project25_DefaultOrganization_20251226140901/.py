/**
 * Represents a Teaching entity with teacher, subject, and hours.
 * This class follows JavaBean conventions for getters and setters.
 */
public class Teaching {
    private String teacher;
    private String subject;
    private int hours;
    public Teaching(String teacher, String subject, int hours) {
        this.teacher = teacher;
        this.subject = subject;
        this.hours = hours;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
}