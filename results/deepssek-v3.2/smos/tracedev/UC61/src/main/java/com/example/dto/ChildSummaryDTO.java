package com.example.dto;

import java.util.Date;
import java.util.List;
import com.example.model.AttendanceRecord;
import com.example.model.DisciplinaryNote;
import com.example.model.DelayRecord;
import com.example.model.Justification;

/**
 * Data Transfer Object for child summary information.
 * Contains all relevant data for a parent viewing child information.
 */
public class ChildSummaryDTO {
    private String childName;
    private int childId;
    private Date summaryDate;
    private List<AttendanceRecord> absences;
    private List<DisciplinaryNote> disciplinaryNotes;
    private List<DelayRecord> delays;
    private List<Justification> justifications;

    public ChildSummaryDTO(String childName, int childId, Date summaryDate) {
        this.childName = childName;
        this.childId = childId;
        this.summaryDate = summaryDate;
    }

    public String getChildName() {
        return childName;
    }

    public int getChildId() {
        return childId;
    }

    public Date getSummaryDate() {
        return summaryDate;
    }

    public List<AttendanceRecord> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AttendanceRecord> absences) {
        this.absences = absences;
    }

    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    public void setDisciplinaryNotes(List<DisciplinaryNote> disciplinaryNotes) {
        this.disciplinaryNotes = disciplinaryNotes;
    }

    public List<DelayRecord> getDelays() {
        return delays;
    }

    public void setDelays(List<DelayRecord> delays) {
        this.delays = delays;
    }

    public List<Justification> getJustifications() {
        return justifications;
    }

    public void setJustifications(List<Justification> justifications) {
        this.justifications = justifications;
    }
}