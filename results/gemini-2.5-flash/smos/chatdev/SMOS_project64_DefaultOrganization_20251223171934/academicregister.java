'''
This class represents a single academic register entry.
It's a simple Plain Old Java Object (POJO) to hold register details.
'''
public class AcademicRegister {
    private String id;
    private int academicYear;
    private String className;
    private String subject;
    private String teacherName;
    /**
     * Constructs a new AcademicRegister.
     *
     * @param id The unique identifier for the register.
     * @param academicYear The academic year this register belongs to.
     * @param className The name of the class (e.g., "10A", "11B").
     * @param subject The subject of the register (e.g., "Mathematics", "History").
     * @param teacherName The name of the teacher associated with this register.
     */
    public AcademicRegister(String id, int academicYear, String className, String subject, String teacherName) {
        this.id = id;
        this.academicYear = academicYear;
        this.className = className;
        this.subject = subject;
        this.teacherName = teacherName;
    }
    // --- Getters for all fields ---
    public String getId() {
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
    public String getTeacherName() {
        return teacherName;
    }
    @Override
    public String toString() {
        return "Register ID: " + id + ", Year: " + academicYear + ", Class: " + className +
               ", Subject: " + subject + ", Teacher: " + teacherName;
    }
}