import java.util.Objects;

/**
 * Data model class representing a student.
 */
public class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final String className;
    private final AcademicYear academicYear;

    /**
     * Constructor for Student.
     * @param studentId Unique identifier for the student.
     * @param firstName First name of the student.
     * @param lastName Last name of the student.
     * @param className Name of the class the student belongs to.
     * @param academicYear Academic year the student is enrolled in.
     */
    public Student(String studentId, String firstName, String lastName, String className, AcademicYear academicYear) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
        this.className = Objects.requireNonNull(className, "Class name cannot be null");
        this.academicYear = Objects.requireNonNull(academicYear, "Academic year cannot be null");
    }

    /**
     * Gets the student's unique ID.
     * @return Student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's first name.
     * @return First name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the student's last name.
     * @return Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the student's full name (first name + last name).
     * @return Full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Gets the name of the class the student belongs to.
     * @return Class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the academic year the student is enrolled in.
     * @return AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns a string representation of the student.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", className='" + className + '\'' +
                ", academicYear=" + academicYear.getDisplayName() +
                '}';
    }

    /**
     * Checks equality based on studentId.
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Hash code based on studentId.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}