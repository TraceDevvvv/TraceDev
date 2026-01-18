package com.example.presentation;

import com.example.application.NoteDetailsDTO;
import com.example.domain.Note;
import java.util.List;

/**
 * View component responsible for displaying the list of notes.
 * It interacts with the NoteDetailsController for handling user actions.
 */
public class NoteListView {
    private NoteDetailsController controller;

    public NoteListView(NoteDetailsController controller) {
        this.controller = controller;
    }

    /**
     * Displays the list of notes to the administrator.
     * @param notes the list of Note objects to display
     */
    public void displayNoteList(List<Note> notes) {
        System.out.println("=== Note List ===");
        for (Note note : notes) {
            System.out.println("Note ID: " + note.getId() + ", Student: " + note.getStudent().getName());
        }
        System.out.println("=================");
    }

    /**
     * Handles the administrator's selection of a note.
     * Delegates to the controller.
     * @param noteId the ID of the selected note
     */
    public void selectNote(String noteId) {
        System.out.println("NoteListView: Note selected - " + noteId);
        controller.handleNoteSelection(noteId);
    }

    /**
     * Simulates the administrator clicking the "Details" button.
     * @param noteId the ID of the note for which details are requested
     */
    public void clickDetailsButton(String noteId) {
        System.out.println("NoteListView: 'Details' button clicked for note ID: " + noteId);
        controller.handleDetailsButtonClick(noteId);
    }

    /**
     * Shows an error message to the user.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.err.println("Error in NoteListView: " + message);
    }
}