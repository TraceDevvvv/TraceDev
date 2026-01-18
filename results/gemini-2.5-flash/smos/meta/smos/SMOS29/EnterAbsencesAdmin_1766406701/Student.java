package com.example.absencesystem;

/**
 * Represents a student in the absence management system.
 * Each student has a unique ID, a name, and an associated parent's email address
 * for notification purposes.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentEmail;

    /**
     * Constructs a new Student object.
     *
     * @param studentId   The unique identifier for the student.
     * @param name        The full name of the student.
     * @param parentEmail The email address of the student's parent, used for notifications.
     */
    public Student(String studentId, String name, String parentEmail) {
        // Validate inputs to ensure they are not null or empty,
        // although more robust validation (e.g., email format) might be needed in a real system.
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent email cannot be null or empty.");
        }

        this.studentId = studentId;
        this.name = name;
        this.parentEmail = parentEmail;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the student's parent.
     *
     * @return The parent's email address.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    /**
     * Provides a string representation of the Student object.
     *
     * @return A string containing the student's ID, name, and parent email.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               '}';
    }

    /**
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same studentId as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}