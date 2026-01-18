package com.example.disciplinarynote.presentation;

import com.example.disciplinarynote.dto.DisciplinaryNoteDetailsDto;
import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;

import java.time.LocalDate;

/**
 * Model for the Note Edit Form, holding the data displayed and edited by the user.
 */
public class NoteEditFormModel {
    private String noteId;
    private String studentId;
    private String description;
    private String teacherId;
    private LocalDate date;

    /**
     * Constructs an empty NoteEditFormModel.
     */
    public NoteEditFormModel() {
        // Default constructor
    }

    /**
     * Sets the form fields based on provided DisciplinaryNoteDetailsDto.
     * This is used when loading existing note details into the form for editing.
     *
     * @param details The DTO containing the note's details.
     */
    public void setNoteDetails(DisciplinaryNoteDetailsDto details) {
        this.noteId = details.getNoteId();
        // Assuming studentName/teacherName can be mapped back to ID for command creation.
        // In a real app, we'd likely also carry studentId/teacherId in the DTO or fetch them.
        // For simplicity, we use placeholder IDs if not directly available from DTO names.
        this.studentId = "S001"; // Placeholder, normally fetched or part of DTO
        this.teacherId = "T001"; // Placeholder, normally fetched or part of DTO
        this.description = details.getDescription();
        this.date = details.getDate();

        System.out.println("[Presentation] NoteEditFormModel: Details loaded into form for noteId: " + this.noteId);
    }

    /**
     * Converts the current form model data into an UpdateDisciplinaryNoteCommand.
     *
     * @return An UpdateDisciplinaryNoteCommand containing the edited data.
     * @throws IllegalStateException if noteId is not set (i.e., form was not initialized with an existing note).
     */
    public UpdateDisciplinaryNoteCommand toCommand() {
        if (this.noteId == null) {
            throw new IllegalStateException("Cannot create command: Note ID is not set in the form model.");
        }
        System.out.println("[Presentation] NoteEditFormModel: Converting form data to UpdateDisciplinaryNoteCommand.");
        return new UpdateDisciplinaryNoteCommand(noteId, studentId, description, teacherId, date);
    }

    // --- Getters and Setters for form binding ---
    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}