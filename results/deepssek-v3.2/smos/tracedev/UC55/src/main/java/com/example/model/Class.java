package com.example.model;

import java.util.List;

/**
 * Represents a class entity with students.
 * Mapped from Class in UML class diagram.
 */
public class Class {
    private String classId;
    private String className;
    private List<Student> students;

    public Class(String classId, String className, List<Student> students) {
        this.classId = classId;
        this.className = className;
        this.students = students;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}