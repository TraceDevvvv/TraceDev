/**
 * Represents a student enrolled in a class.
 */
package com.chatdev.register.model;
public class Student {
    private String studentId;
    private String name;
    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name      The full name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
    /**
     * Returns the student ID.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Returns the student's name.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the student's name.
     *
     * @param name The new name for the student.
     */
    public void setName(String name) {
        this.name = name;
    }
}