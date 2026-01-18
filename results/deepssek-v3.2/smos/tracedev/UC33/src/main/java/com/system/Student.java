package com.system;

/**
 * Represents a student with possible late entry.
 */
public class Student {
    private String studentId;
    private String name;
    private boolean hasLateEntry;

    public Student(String studentId, String name, boolean hasLateEntry) {
        this.studentId = studentId;
        this.name = name;
        this.hasLateEntry = hasLateEntry;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public boolean getHasLateEntry() {
        return hasLateEntry;
    }

    public void setHasLateEntry(boolean hasLateEntry) {
        this.hasLateEntry = hasLateEntry;
    }
}