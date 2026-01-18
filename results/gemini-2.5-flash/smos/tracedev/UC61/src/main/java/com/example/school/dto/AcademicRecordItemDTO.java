package com.example.school.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for a single academic record item.
 * Used to transfer simplified academic record data from the application layer to the presentation layer.
 */
public class AcademicRecordItemDTO {
    private LocalDate recordDate;
    private int absences;
    private String disciplinaryNotes;
    private int delayCount;
    private String justification;

    /**
     * Constructs a new AcademicRecordItemDTO.
     *
     * @param recordDate The date of the academic record.
     * @param absences The number of absences recorded.
     * @param disciplinaryNotes Any disciplinary notes.
     * @param delayCount The number of delays recorded.
     * @param justification The justification for absences or delays.
     */
    public AcademicRecordItemDTO(LocalDate recordDate, int absences, String disciplinaryNotes, int delayCount, String justification) {
        this.recordDate = recordDate;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delayCount = delayCount;
        this.justification = justification;
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