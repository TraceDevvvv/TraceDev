package com.system.school.note;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a disciplinary note issued to a student in the school management system.
 * This class stores details about the note, including the student involved, the reason,
 * and the date it was issued.
 */
public class DisciplinaryNote {
    private String noteId;
    private String studentId; // Reference to the student who received the note
    private String reason;
    private LocalDate dateIssued;
    private String issuedByAdminId; // Reference to the administrator who issued the note

    /**
     * Constructs a new DisciplinaryNote instance.
     *
     * @param noteId The unique identifier for this disciplinary note.
     * @param studentId The ID of the student to whom this note pertains.
     * @param reason A description of the disciplinary action or reason for the note.
     * @param dateIssued The date when the note was issued.
     * @param issuedByAdminId The ID of the administrator who issued this note.
     * @throws IllegalArgumentException if any required parameter is null or empty.
     */
    public DisciplinaryNote(String noteId, String studentId, String reason,
                           LocalDate dateIssued, String issuedByAdminId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Reason for note cannot be null or empty.");
        }
        if (dateIssued == null) {
            throw new IllegalArgumentException("Date issued cannot be null.");
        }
        if (issuedByAdminId == null || issuedByAdminId.trim().isEmpty()) {
            throw new IllegalArgumentException("Issued by Admin ID cannot be null or empty.");
        }

        this.noteId = noteId;
        this.studentId = studentId;
        this.reason = reason;
        this.dateIssued = dateIssued;
        this.issuedByAdminId = issuedByAdminId;
    }

    /**
     * Returns the unique identifier of the disciplinary note.
     *
     * @return The note's ID.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Returns the ID of the student associated with this note.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the reason for the disciplinary note.
     *
     * @return The reason description.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for the disciplinary note.
     *
     * @param reason The new reason description.
     * @throws IllegalArgumentException if the reason is null or empty.
     */
    public void setReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Reason for note cannot be null or empty.");
        }
        this.reason = reason;
    }

    /**
     * Returns the date when the note was issued.
     *
     * @return The date of issue.
     */
    public LocalDate getDateIssued() {
        return dateIssued;
    }

    /**
     * Sets the date when the note was issued.
     *
     * @param dateIssued The new date of issue.
     * @throws IllegalArgumentException if the date issued is null.
     */
    public void setDateIssued(LocalDate dateIssued) {
        if (dateIssued == null) {
            throw new IllegalArgumentException("Date issued cannot be null.");
        }
        this.dateIssued = dateIssued;
    }

    /**
     * Returns the ID of the administrator who issued this note.
     *
     * @return The administrator's ID.
     */
    public String getIssuedByAdminId() {
        return issuedByAdminId;
    }

    /**
     * Provides a string representation of the DisciplinaryNote object.
     *
     * @return A string containing the note's ID, student ID, reason, date issued, and issuer.
     */
    @Override
    public String toString() {
        return "DisciplinaryNote{" +
               "noteId='" + noteId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", reason='" + reason + '\'' +
               ", dateIssued=" + dateIssued +
               ", issuedByAdminId='" + issuedByAdminId + '\'' +
               '}';
    }

    /**
     * Compares this DisciplinaryNote object to the specified object. The result is true if and only if
     * the argument is not null and is a DisciplinaryNote object that has the same noteId as this object.
     *
     * @param o The object to compare this DisciplinaryNote against.
     * @return true if the given object represents a DisciplinaryNote equivalent to this note, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaryNote that = (DisciplinaryNote) o;
        return Objects.equals(noteId, that.noteId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(noteId);
    }
}