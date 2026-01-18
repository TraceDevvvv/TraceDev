package com.example;

/**
 * Represents a student entity with absence and note counts.
 */
public class Student {
    public String studentId;
    public String name;
    public String enrolledYear;
    private int absenceCount;
    private int noteCount;

    public Student(String studentId, String name, String enrolledYear, int absenceCount, int noteCount) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledYear = enrolledYear;
        this.absenceCount = absenceCount;
        this.noteCount = noteCount;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(String enrolledYear) {
        this.enrolledYear = enrolledYear;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }
}