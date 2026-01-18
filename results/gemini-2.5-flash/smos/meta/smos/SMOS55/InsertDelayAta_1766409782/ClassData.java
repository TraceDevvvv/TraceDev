import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a list of students for a specific class.
 * This class is responsible for holding the class's name and its roster of students.
 */
public class ClassData {
    private String className;
    private List<Student> students;

    /**
     * Constructs a new ClassData object with the specified class name.
     * Initializes an empty list of students for this class.
     *
     * @param className The name of the class.
     */
    public ClassData(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.className = className;
        this.students = new ArrayList<>();
    }

    /**
     * Returns the name of the class.
     *
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Adds a student to this class.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if the student is null or a student with the same ID already exists.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null student.");
        }
        // Check for duplicate student IDs to ensure uniqueness within the class
        for (Student existingStudent : students) {
            if (existingStudent.getStudentId().equals(student.getStudentId())) {
                throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists in this class.");
            }
        }
        this.students.add(student);
    }

    /**
     * Returns an unmodifiable list of students in this class.
     * This prevents external modification of the class's student roster directly.
     *
     * @return An unmodifiable List of Student objects.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Provides a string representation of the ClassData object.
     *
     * @return A string containing the class name and the number of students.
     */
    @Override
    public String toString() {
        return "ClassData{" +
               "className='" + className + '\'' +
               ", numberOfStudents=" + students.size() +
               '}';
    }
}