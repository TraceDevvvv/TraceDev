package com.example.domain;

/**
 * Domain model for a Disciplinary Note.
 */
public class DisciplinaryNote {
    private String id;
    private String note;
    private String severity; // e.g., "Mild", "Moderate", "Severe"

    /**
     * Constructs a DisciplinaryNote object.
     * @param id The unique ID of the disciplinary note.
     * @param note The textual content of the note.
     * @param severity The severity level of the note.
     */
    public DisciplinaryNote(String id, String note, String severity) {
        this.id = id;
        this.note = note;
        this.severity = severity;
    }

    public String getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getSeverity() {
        return severity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}