package com.example;

import java.util.List;

/**
 * View model for displaying a list of student notes.
 */
public class StudentNotesViewModel {
    private String studentId;
    private List<NoteViewModel> notes;

    /**
     * Constructs a StudentNotesViewModel with the given student ID and notes.
     *
     * @param studentId the student ID
     * @param notes the list of note view models
     */
    public StudentNotesViewModel(String studentId, List<NoteViewModel> notes) {
        this.studentId = studentId;
        this.notes = notes;
    }

    /**
     * Gets the student ID.
     *
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the list of note view models.
     *
     * @return the list of notes
     */
    public List<NoteViewModel> getNotes() {
        return notes;
    }
}