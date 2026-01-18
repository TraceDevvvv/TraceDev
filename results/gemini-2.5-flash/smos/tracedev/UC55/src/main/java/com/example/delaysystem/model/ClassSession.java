package com.example.delaysystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a class session, containing its details and enrolled students.
 */
public class ClassSession {
    private String classId;
    private String name;
    private List<Student> students; // Aggregation: ClassSession contains Students

    /**
     * Constructs a new ClassSession object.
     *
     * @param classId The unique identifier for the class.
     * @param name The name or title of the class.
     */
    public ClassSession(String classId, String name) {
        this.classId = classId;
        this.name = name;
        this.students = new ArrayList<>();
    }

    /**
     * Gets the unique identifier of the class.
     *
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the class.
     *
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a student to this class session.
     *
     * @param student The Student object to add.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Gets an unmodifiable list of students in this class session.
     *
     * @return An unmodifiable list of Student objects.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public String toString() {
        return "ClassSession{" +
               "classId='" + classId + '\'' +
               ", name='" + name + '\'' +
               ", students=" + students.size() +
               '}';
    }
}