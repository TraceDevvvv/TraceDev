package com.example.school.domain;

import java.time.LocalDate;

/**
 * Represents a single academic record for a student in the domain model.
 */
public class StudentAcademicRecord {
    private String recordId;
    private String studentId;
    private LocalDate recordDate;
    private int absences;
    private String disciplinaryNotes;
    private int delayCount;
    private String justification;

    /**
     * Constructs a new StudentAcademicRecord object.
     *
     * @param recordId A unique identifier for the record.
     * @param studentId The ID of the student this record belongs to.
     * @param recordDate The date of the record.
     * @param absences The number of absences.
     * @param disciplinaryNotes Any disciplinary notes.
     * @param delayCount The count of delays.
     * @param justification Justification for absences/delays.
     */
    public StudentAcademicRecord(String recordId, String studentId, LocalDate recordDate, int absences, String disciplinaryNotes, int delayCount, String justification) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delayCount = delayCount;
        this.justification = justification;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public int getAbsences() {
        return absences;
    }

    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    public int getDelayCount() {
        return delayCount;
    }

    public String getJustification() {
        return justification;
    }
}