package com.example.viewstudentdata;

import java.time.LocalDate;

/**
 * Represents a record of a student's academic and behavioral data for a specific date.
 * This includes information such as absences, disciplinary notes, delays, and justifications.
 */
public class StudentRecord {
    private String recordId;
    private String studentId;
    private LocalDate date;
    private int absences;
    private String disciplinaryNotes;
    private int delays;
    private String justification;

    /**
     * Constructs a new StudentRecord instance.
     *
     * @param recordId          The unique identifier for this record.
     * @param studentId         The ID of the student this record belongs to.
     * @param date              The date to which this record pertains.
     * @param absences          The number of absences recorded for the student on this date.
     * @param disciplinaryNotes Any disciplinary notes for the student on this date.
     * @param delays            The number of delays (tardiness) recorded for the student on this date.
     * @param justification     Any justification provided for absences or delays.
     */
    public StudentRecord(String recordId, String studentId, LocalDate date, int absences,
                         String disciplinaryNotes, int delays, String justification) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }

    /**
     * Returns the unique identifier of the student record.
     *
     * @return The record's ID.
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * Returns the ID of the student associated with this record.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the date to which this record pertains.
     *
     * @return The record date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the number of absences recorded for the student on this date.
     *
     * @return The count of absences.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * Returns any disciplinary notes for the student on this date.
     *
     * @return The disciplinary notes string.
     */
    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    /**
     * Returns the number of delays (tardiness) recorded for the student on this date.
     *
     * @return The count of delays.
     */
    public int getDelays() {
        return delays;
    }

    /**
     * Returns any justification provided for absences or delays.
     *
     * @return The justification string.
     */
    public String getJustification() {
        return justification;
    }
}