package com.example.entities;

import java.util.Date;
import java.util.List;

/**
 * Represents a daily registry entry.
 */
public class RegistryEntry {
    private Date date;
    private List<StudentEntry> studentEntries;

    public RegistryEntry(Date date, List<StudentEntry> studentEntries) {
        this.date = date;
        this.studentEntries = studentEntries;
    }

    public Date getDate() {
        return date;
    }

    public List<StudentEntry> getStudentEntries() {
        return studentEntries;
    }

    public void addStudentEntry(StudentEntry studentEntry) {
        studentEntries.add(studentEntry);
    }

    public void updateStudentEntry(StudentEntry studentEntry) {
        // Simplified update
        studentEntries.removeIf(s -> s.getStudentId().equals(studentEntry.getStudentId()));
        studentEntries.add(studentEntry);
    }
}