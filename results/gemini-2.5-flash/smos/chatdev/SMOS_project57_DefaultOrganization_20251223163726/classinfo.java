'''
Data model class representing information about a specific class taught by the professor.
'''
import java.util.Objects;
public class ClassInfo {
    private String classId;     // Unique identifier for the class.
    private String className;   // Human-readable name of the class (e.g., "Software Engineering I").
    private String subject;     // Subject of the class (e.g., "Computer Science").
    private String semester;    // Semester in which the class is taught (e.g., "Fall 2023").
    /**
     * Constructs a new ClassInfo object.
     * @param classId A unique identifier for the class.
     * @param className The name of the class.
     * @param subject The subject of the class.
     * @param semester The semester the class is offered.
     */
    public ClassInfo(String classId, String className, String subject, String semester) {
        this.classId = classId;
        this.className = className;
        this.subject = subject;
        this.semester = semester;
    }
    // --- Getters for all fields ---
    public String getClassId() {
        return classId;
    }
    public String getClassName() {
        return className;
    }
    public String getSubject() {
        return subject;
    }
    public String getSemester() {
        return semester;
    }
    @Override
    public String toString() {
        return className + " (" + subject + ", " + semester + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return Objects.equals(classId, classInfo.classId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}