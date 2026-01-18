package com.example.absence.domain;

/**
 * Represents an entry in the absence registration command.
 * Contains a student ID and whether the student is absent.
 */
public class AbsenceEntry {
    private String studentId;
    private boolean absent;

    public AbsenceEntry(String studentId, boolean absent) {
        this.studentId = studentId;
        this.absent = absent;
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean isAbsent() {
        return absent;
    }
}