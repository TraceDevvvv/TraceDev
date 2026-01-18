import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a class register for a specific date, containing a list of students
 * and their attendance, justifications, and disciplinary notes for that day.
 * This class aggregates student records for a particular class on a given date.
 */
public class Register {

    private LocalDate registerDate;
    // Map to store students, using studentId as key for quick access.
    private Map<String, Student> students;

    /**
     * Constructs a new Register object for a specific date.
     *
     * @param registerDate The date for which this register is created. Cannot be null.
     */
    public Register(LocalDate registerDate) {
        if (registerDate == null) {
            throw new IllegalArgumentException("Register date cannot be null.");
        }
        this.registerDate = registerDate;
        this.students = new HashMap<>();
    }

    /**
     * Gets the date of this register.
     *
     * @return The LocalDate representing the register date.
     */
    public LocalDate getRegisterDate() {
        return registerDate;
    }

    /**
     * Adds a student to this register. If a student with the same ID already exists,
     * the existing student's record will be updated with the new student object.
     *
     * @param student The Student object to add. Cannot be null.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        this.students.put(student.getStudentId(), student);
    }

    /**
     * Retrieves a student from the register by their student ID.
     *
     * @param studentId The unique identifier of the student to retrieve. Cannot be null or empty.
     * @return The Student object if found, or null if no student with the given ID exists.
     */
    public Student getStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        return this.students.get(studentId);
    }

    /**
     * Returns an unmodifiable map of all students in this register.
     * The keys are student IDs and values are Student objects.
     *
     * @return An unmodifiable map of students.
     */
    public Map<String, Student> getAllStudents() {
        return Collections.unmodifiableMap(students);
    }

    /**
     * Provides a string representation of the Register object.
     *
     * @return A formatted string containing the register date and the number of students.
     */
    @Override
    public String toString() {
        return "Register{" +
               "registerDate=" + registerDate +
               ", numberOfStudents=" + students.size() +
               '}';
    }

    /**
     * Compares this Register object to the specified object. The result is true if and only if
     * the argument is not null and is a Register object that has the same registerDate as this object.
     *
     * @param o The object to compare this Register against.
     * @return true if the given object represents a Register equivalent to this register, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return Objects.equals(registerDate, register.registerDate);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(registerDate);
    }
}