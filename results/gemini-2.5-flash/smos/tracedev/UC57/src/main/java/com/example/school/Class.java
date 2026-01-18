package com.example.school;

/**
 * Represents a Class entity in the system.
 */
public class Class {
    private String classId;
    private String name;
    private String courseCode;
    private String professorId;

    /**
     * Constructs a new Class with the specified details.
     * @param classId The unique identifier for the class.
     * @param name The name of the class (e.g., "Mathematics I").
     * @param courseCode The course code (e.g., "MATH101").
     * @param professorId The ID of the professor teaching this class.
     */
    public Class(String classId, String name, String courseCode, String professorId) {
        this.classId = classId;
        this.name = name;
        this.courseCode = courseCode;
        this.professorId = professorId;
    }

    /**
     * Gets the unique identifier of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the course code of the class.
     * @return The course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets the ID of the professor teaching this class.
     * @return The professor ID.
     */
    public String getProfessorId() {
        return professorId;
    }
}