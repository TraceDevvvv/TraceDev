package com.example.presentation;

import java.util.Date;
import java.util.List;

/**
 * ViewModel for a single entry in the class registry, typically representing a day's record.
 */
public class RegistryEntryViewModel {
    public Date entryDate;
    public List<StudentStatusViewModel> studentStatuses;

    /**
     * Constructs a RegistryEntryViewModel.
     * @param entryDate The date of the registry entry.
     * @param studentStatuses A list of student statuses for that date.
     */
    public RegistryEntryViewModel(Date entryDate, List<StudentStatusViewModel> studentStatuses) {
        this.entryDate = entryDate;
        this.studentStatuses = studentStatuses;
    }

    // Getters for ViewModel properties
    public Date getEntryDate() {
        return entryDate;
    }

    public List<StudentStatusViewModel> getStudentStatuses() {
        return studentStatuses;
    }
}