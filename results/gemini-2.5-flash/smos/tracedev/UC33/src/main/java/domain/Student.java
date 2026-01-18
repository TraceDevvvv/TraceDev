package domain;

import java.util.Date;

/**
 * Represents a Student in the domain layer.
 * This class holds basic student information.
 */
public class Student {
    private String studentId;
    private String name;
    private Date registrationDate;

    /**
     * Constructs a new Student.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     * @param registrationDate The date when the student registered.
     */
    public Student(String studentId, String name, Date registrationDate) {
        this.studentId = studentId;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    // Getters for all attributes
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    // Setters are typically omitted for immutable domain objects or added as needed.
    // For this example, we'll keep it simple and assume data is set via constructor.

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", registrationDate=" + registrationDate +
               '}';
    }
}