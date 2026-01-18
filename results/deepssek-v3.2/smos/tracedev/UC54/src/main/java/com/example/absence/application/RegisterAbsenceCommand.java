package com.example.absence.application;

import com.example.absence.domain.AbsenceEntry;
import java.util.Date;
import java.util.List;

/**
 * Command object containing data for registering absences.
 */
public class RegisterAbsenceCommand {
    private String classId;
    private Date date;
    private List<AbsenceEntry> absenceEntries;

    public RegisterAbsenceCommand(String classId, Date date, List<AbsenceEntry> absenceEntries) {
        this.classId = classId;
        this.date = date;
        this.absenceEntries = absenceEntries;
    }

    public String getClassId() {
        return classId;
    }

    public Date getDate() {
        return date;
    }

    public List<AbsenceEntry> getAbsenceEntries() {
        return absenceEntries;
    }
}