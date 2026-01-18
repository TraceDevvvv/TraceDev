package com.example.disciplinarynote.domain;

/**
 * Represents a Teacher in the domain.
 */
public class Teacher {
    private String teacherId;
    private String name;

    /**
     * Constructs a new Teacher.
     * @param teacherId The unique identifier for the teacher.
     * @param name The name of the teacher.
     */
    public Teacher(String teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
    }

    /**
     * Gets the teacher's ID.
     * @return The teacherId.
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * Gets the teacher's name.
     * @return The name of the teacher.
     */
    public String getName() {
        return name;
    }
}