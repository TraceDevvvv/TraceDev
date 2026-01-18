package com.example.disciplinarynote.domain;

import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;

import java.time.LocalDate;

/**
 * Represents a Disciplinary Note in the domain layer.
 * Contains core business logic and state.
 */
public class DisciplinaryNote {
    private String noteId; // Traceability: noteId from Class Diagram.
    private String studentId; // Holds ID of Student // Traceability: studentId from Class Diagram.
    private String description; // Traceability: description from Class Diagram.
    private String teacherId; // Holds ID of Teacher // Traceability: teacherId from Class Diagram.
    private LocalDate date; // Traceability: date from Class Diagram.

    /**
     * Constructs a new DisciplinaryNote.
     *
     * @param noteId The unique identifier for the note.
     * @param studentId The ID of the student involved.
     * @param description The description of the disciplinary action.
     * @param teacherId The ID of the teacher who issued the note.
     * @param date The date the note was issued.
     */
    public DisciplinaryNote(String noteId, String studentId, String description, String teacherId, LocalDate date) {
        // Basic validation for noteId could be added here
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
        this.noteId = noteId;
        this.studentId = studentId;
        this.description = description;
        this.teacherId = teacherId;
        this.date = date;
    }

    /**
     * Gets the unique identifier of the note.
     * @return The noteId.
     */
    public String getNoteId() { // Traceability: getNoteId() from Class Diagram.
        return noteId;
    }

    /**
     * Gets the ID of the student associated with this note.
     * @return The studentId.
     */
    public String getStudentId() { // Traceability: getStudentId() from Class Diagram.
        return studentId;
    }

    /**
     * Gets the description of the disciplinary action.
     * @return The description.
     */
    public String getDescription() { // Traceability: getDescription() from Class Diagram.
        return description;
    }

    /**
     * Gets the ID of the teacher who issued this note.
     * @return The teacherId.
     */
    public String getTeacherId() { // Traceability: getTeacherId() from Class Diagram.
        return teacherId;
    }

    /**
     * Gets the date the note was issued.
     * @return The date.
     */
    public LocalDate getDate() { // Traceability: getDate() from Class Diagram.
        return date;
    }

    /**
     * Updates the disciplinary note's details using an UpdateDisciplinaryNoteCommand.
     * Ensures R8: Note data has been modified.
     * Contributes to R12: Ensures integrity and accuracy.
     *
     * @param command The command containing the new data for the note.
     * @throws IllegalArgumentException if the command's noteId does not match this note's ID.
     */
    public void update(UpdateDisciplinaryNoteCommand command) { // Traceability: update(command) from Class Diagram.
        if (!this.noteId.equals(command.getNoteId())) {
            throw new IllegalArgumentException("Attempted to update a different note. Command ID: " + command.getNoteId() + ", Note ID: " + this.noteId);
        }
        // Apply updates from the command
        this.studentId = command.getStudentId();
        this.description = command.getDescription();
        this.teacherId = command.getTeacherId();
        this.date = command.getDate();
        // Log or trigger domain events if necessary
        System.out.println("[Domain] Disciplinary Note '" + this.noteId + "' updated with new details.");
    }
}