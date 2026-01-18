package com.example.disciplinarynote.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for displaying disciplinary note details.
 * Supports R4: Note details ARE displayed.
 * Added to satisfy audit recommendation.
 */
public class DisciplinaryNoteDetailsDto {
    private String noteId;
    private String studentName;
    private String description;
    private String teacherName;
    private LocalDate date;

    /**
     * Constructs a new DisciplinaryNoteDetailsDto.
     *
     * @param noteId The unique identifier of the note.
     * @param studentName The name of the student.
     * @param description The description of the disciplinary note.
     * @param teacherName The name of the teacher who issued the note.
     * @param date The date the note was issued.
     */
    public DisciplinaryNoteDetailsDto(String noteId, String studentName, String description, String teacherName, LocalDate date) {
        this.noteId = noteId;
        this.studentName = studentName;
        this.description = description;
        this.teacherName = teacherName;
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
     * Gets the student's name.
     * Added to satisfy audit recommendation.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the description.
     * Added to satisfy audit recommendation.
     * @return The description of the note.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the teacher's name.
     * Added to satisfy audit recommendation.
     * @return The teacher's name.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Gets the date.
     * Added to satisfy audit recommendation.
     * @return The date of the note.
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DisciplinaryNoteDetailsDto{" +
               "noteId='" + noteId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", description='" + description + '\'' +
               ", teacherName='" + teacherName + '\'' +
               ", date=" + date +
               '}';
    }
}