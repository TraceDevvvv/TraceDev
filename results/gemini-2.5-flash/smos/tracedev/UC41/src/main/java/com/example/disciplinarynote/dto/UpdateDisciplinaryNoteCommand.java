package com.example.disciplinarynote.dto;

import java.time.LocalDate;

/**
 * Command object for updating a disciplinary note.
 * Receives data from the presentation layer to update a DisciplinaryNote.
 */
public class UpdateDisciplinaryNoteCommand {
    private String noteId;
    private String studentId; // Changed to String to align with domain model
    private String description;
    private String teacherId; // Changed to String to align with domain model
    private LocalDate date;

    /**
     * Constructs a new UpdateDisciplinaryNoteCommand.
     *
     * @param noteId The unique identifier of the note to be updated.
     * @param studentId The ID of the student.
     * @param description The new description for the note.
     * @param teacherId The ID of the teacher.
     * @param date The new date for the note.
     */
    public UpdateDisciplinaryNoteCommand(String noteId, String studentId, String description, String teacherId, LocalDate date) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.description = description;
        this.teacherId = teacherId;
        this.date = date;
    }

    /**
     * Gets the noteId.
     * Added to satisfy audit recommendation.
     * @return The note's ID.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Gets the studentId.
     * Added to satisfy audit recommendation.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the description.
     * Added to satisfy audit recommendation.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the teacherId.
     * Added to satisfy audit recommendation.
     * @return The teacher's ID.
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * Gets the date.
     * Added to satisfy audit recommendation.
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "UpdateDisciplinaryNoteCommand{" +
               "noteId='" + noteId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", description='" + description + '\'' +
               ", teacherId='" + teacherId + '\'' +
               ", date=" + date +
               '}';
    }
}