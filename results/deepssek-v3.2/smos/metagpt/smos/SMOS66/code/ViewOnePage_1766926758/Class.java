import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data model class representing a class (group of students).
 * Note: Class name "Class" conflicts with java.lang.Class, but this is acceptable
 * for this specific use case. Alternatively, could be renamed to "SchoolClass".
 */
public class Class {
    private final String classId;
    private final String name;
    private final AcademicYear academicYear;
    private final List<Student> students;

    /**
     * Constructor for Class.
     * @param classId Unique identifier for the class.
     * @param name Name of the class (e.g., "10A", "Math 101").
     * @param academicYear Academic year the class belongs to.
     */
    public Class(String classId, String name, AcademicYear academicYear) {
        this.classId = Objects.requireNonNull(classId, "Class ID cannot be null");
        this.name = Objects.requireNonNull(name, "Class name cannot be null");
        this.academicYear = Objects.requireNonNull(academicYear, "Academic year cannot be null");
        this.students = new ArrayList<>();
    }

    /**
     * Gets the class's unique ID.
     * @return Class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the class.
     * @return Class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the academic year the class belongs to.
     * @return AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Gets the list of students in the class.
     * @return List of Student objects (defensive copy).
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Adds a student to the class.
     * @param student Student to add.
     * @throws NullPointerException if student is null.
     */
    public void addStudent(Student student) {
        Objects.requireNonNull(student, "Student cannot be null");
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    /**
     * Removes a student from the class.
     * @param student Student to remove.
     * @return true if student was removed, false otherwise.
     */
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    /**
     * Gets the number of students in the class.
     * @return Student count.
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Returns a string representation of the class.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Class{" +
                "classId='" + classId + '\'' +
                ", name='" + name + '\'' +
                ", academicYear=" + academicYear.getDisplayName() +
                ", studentCount=" + students.size() +
                '}';
    }

    /**
     * Checks equality based on classId.
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return classId.equals(aClass.classId);
    }

    /**
     * Hash code based on classId.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}