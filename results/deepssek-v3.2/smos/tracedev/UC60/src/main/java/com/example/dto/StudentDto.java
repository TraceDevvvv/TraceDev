package com.example.dto;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for student school data.
 * Constraints:
 * - All fields must be complete
 * - Data must be accurate and validated
 * - Mapping from ExternalStudentData must be thorough
 */
public class StudentDto {
    private String studentId;
    private String className;
    private Date date;
    private List<AbsenceRecord> absences;
    private List<DisciplinaryNote> disciplinaryNotes;
    private List<DelayRecord> delays;
    private String justification;

    public StudentDto() {
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<AbsenceRecord> getAbsences() {
        return absences;
    }

    public void setAbsences(List<AbsenceRecord> absences) {
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

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}