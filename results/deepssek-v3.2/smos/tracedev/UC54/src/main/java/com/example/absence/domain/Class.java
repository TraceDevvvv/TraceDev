package com.example.absence.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Class in the domain layer.
 * Contains class information and a list of enrolled students.
 */
public class Class {
    private String classId;
    private String className;
    private String teacherId;
    private List<Student> students;

    public Class(String classId, String className, String teacherId) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.students = new ArrayList<>();
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Loads a class by its ID. In a real implementation, this would fetch from a database.
     * For simplicity, we return a mock class with sample students.
     * @param classId the ID of the class to load
     * @return the loaded Class object
     */
    public static Class loadClass(String classId) {
        // Mock implementation: create a class with two sample students
        Class clazz = new Class(classId, "Mathematics", "T001");
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "John", "Doe", "john.doe@example.com"));
        students.add(new Student("S002", "Jane", "Smith", "jane.smith@example.com"));
        clazz.setStudents(students);
        return clazz;
    }
}