package com.example.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Domain model for a single entry in a class registry, typically representing records for a specific date.
 */
public class RegistryEntry {
    private Date entryDate;
    private List<StudentStatus> studentStatuses;

    /**
     * Constructs a RegistryEntry object.
     * @param entryDate The date of this registry entry.
     * @param studentStatuses A list of student statuses recorded for this date.
     */
    public RegistryEntry(Date entryDate, List<StudentStatus> studentStatuses) {
        this.entryDate = entryDate;
        this.studentStatuses = studentStatuses != null ? studentStatuses : new ArrayList<>();
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public List<StudentStatus> getStudentStatuses() {
        return studentStatuses;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setStudentStatuses(List<StudentStatus> studentStatuses) {
        this.studentStatuses = studentStatuses;
    }
}