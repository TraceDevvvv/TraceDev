package com.example.viewstudentdata;

/**
 * Represents a Student in the system.
 * A student has a unique ID, a name, and the ID of their parent.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentId;

    /**
     * Constructs a new Student instance.
     *
     * @param studentId The unique identifier for the student.
     * @param name      The student's full name.
     * @param parentId  The ID of the parent associated with this student.
     */
    public Student(String studentId, String name, String parentId) {
        this.studentId = studentId;
        this.name = name;
        this.parentId = parentId;
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
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the parent associated with this student.
     *
     * @return The parent's ID.
     */
    public String getParentId() {
        return parentId;
    }
}