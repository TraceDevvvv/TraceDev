'''
Data model class representing a single digital register entry.
This class holds information about a specific register, including its ID,
the academic year it belongs to, the class, subject, date, and content.
'''
public class Register {
    private int id;
    private int academicYear;
    private String className;
    private String subject;
    private String date; // Example: "2023-10-26"
    private String content; // Example: "Attendance for Math. John Doe absent."
    /**
     * Constructs a new Register object.
     *
     * @param id The unique identifier for the register.
     * @param academicYear The academic year this register pertains to.
     * @param className The name of the class this register belongs to.
     * @param subject The subject of the register (e.g., Math, Science).
     * @param date The date the register entry was made.
     * @param content The actual content or notes of the register entry.
     */
    public Register(int id, int academicYear, String className, String subject, String date, String content) {
        this.id = id;
        this.academicYear = academicYear;
        this.className = className;
        this.subject = subject;
        this.date = date;
        this.content = content;
    }
    // --- Getters for all properties ---
    public int getId() {
        return id;
    }
    public int getAcademicYear() {
        return academicYear;
    }
    public String getClassName() {
        return className;
    }
    public String getSubject() {
        return subject;
    }
    public String getDate() {
        return date;
    }
    public String getContent() {
        return content;
    }
    /**
     * Returns a string representation of the Register object.
     *
     * @return A string containing all register details.
     */
    @Override
    public String toString() {
        return "Register{" +
               "id=" + id +
               ", academicYear=" + academicYear +
               ", className='" + className + '\'' +
               ", subject='" + subject + '\'' +
               ", date='" + date + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}