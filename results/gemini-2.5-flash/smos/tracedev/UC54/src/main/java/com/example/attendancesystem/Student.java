package com.example.attendancesystem;

/**
 * Represents a student in the system.
 * Defined in the Domain layer of the Class Diagram.
 * Attributes are private with public getters for encapsulation.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentEmail;
    private String classId;

    /**
     * Constructs a new Student instance.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     * @param parentEmail The email address of the student's parent/guardian.
     * @param classId The ID of the class the student belongs to.
     */
    public Student(String studentId, String name, String parentEmail, String classId) {
        this.studentId = studentId;
        this.name = name;
        this.parentEmail = parentEmail;
        this.classId = classId;
    }

    /**
     * Gets the unique identifier of the student.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the name of the student.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parent's email address.
     * @return The parent's email.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    /**
     * Gets the ID of the class the student belongs to.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", classId='" + classId + '\'' +
               '}';
    }
}