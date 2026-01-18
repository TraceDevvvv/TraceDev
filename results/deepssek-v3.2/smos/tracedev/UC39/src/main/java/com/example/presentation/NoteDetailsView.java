package com.example.presentation;

import com.example.application.NoteDetailsDTO;
import java.text.SimpleDateFormat;

/**
 * View component responsible for rendering the note details form.
 */
public class NoteDetailsView {
    private NoteDetailsController controller;

    public NoteDetailsView(NoteDetailsController controller) {
        this.controller = controller;
    }

    /**
     * Displays note details (alias for renderNoteDetailsForm to match class diagram).
     * @param dto the data transfer object containing note details
     */
    public void displayNoteDetails(NoteDetailsDTO dto) {
        renderNoteDetailsForm(dto);
    }

    /**
     * Renders the note details form with data from the DTO.
     * @param dto the data transfer object containing note details
     */
    public void renderNoteDetailsForm(NoteDetailsDTO dto) {
        System.out.println("=== Note Details Form ===");
        System.out.println("Student: " + dto.getStudentName());
        System.out.println("Description: " + dto.getDescription());
        System.out.println("Teacher: " + dto.getTeacherName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Date: " + sdf.format(dto.getDate()));
        System.out.println("========================");
    }

    /**
     * Handles connection interruption (e.g., browser closed).
     * Performs cleanup and notifies the controller.
     */
    public void handleConnectionInterruption() {
        System.out.println("NoteDetailsView: Connection interrupted.");
        controller.notifyDisconnect();
    }

    /**
     * Shows an error message to the user.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.err.println("Error in NoteDetailsView: " + message);
    }
}