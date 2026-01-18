package com.example.disciplinarynote.presentation;

import com.example.disciplinarynote.dto.DisciplinaryNoteDetailsDto;
import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;

import java.time.LocalDate;

/**
 * Represents the UI view for editing a disciplinary note.
 * Interacts with the NoteController and manages the NoteEditFormModel.
 */
public class NoteEditView {
    private NoteEditFormModel noteEditFormModel;
    private NoteController noteController; // Direct reference for simplicity in simulation
    private RegistryController registryController; // Direct reference for simplicity in simulation

    /**
     * Constructs a NoteEditView.
     *
     * @param noteEditFormModel The model holding the form data.
     * @param noteController The controller responsible for handling view actions.
     * @param registryController The controller for navigating to the registry screen.
     */
    public NoteEditView(NoteEditFormModel noteEditFormModel, NoteController noteController, RegistryController registryController) {
        this.noteEditFormModel = noteEditFormModel;
        this.noteController = noteController;
        this.registryController = registryController;
    }

    /**
     * Displays note details on the form.
     * Supports R4: Note details ARE displayed.
     *
     * @param details The DTO containing the note details to display.
     */
    public void displayNoteDetails(DisciplinaryNoteDetailsDto details) {
        noteEditFormModel.setNoteDetails(details);
        System.out.println("[Presentation] NoteEditView: Displaying details for note: " + details.getNoteId());
        System.out.println("   Current Form State: " + noteEditFormModel.getNoteId() + " | "
                + noteEditFormModel.getStudentId() + " | "
                + noteEditFormModel.getDescription() + " | "
                + noteEditFormModel.getTeacherId() + " | "
                + noteEditFormModel.getDate());
    }

    /**
     * Simulates the administrator editing fields.
     * Added to satisfy requirement R5.
     *
     * @param studentId The student's ID.
     * @param description The note's description.
     * @param teacherId The teacher's ID.
     * @param date The date of the note.
     */
    public void editFields(String studentId, String description, String teacherId, LocalDate date) {
        System.out.println("[Presentation] NoteEditView: Administrator is editing fields...");
        // Update the form model directly from UI inputs
        noteEditFormModel.setStudentId(studentId);
        noteEditFormModel.setDescription(description);
        noteEditFormModel.setTeacherId(teacherId);
        noteEditFormModel.setDate(date);
        System.out.println("   New Form State: " + noteEditFormModel.getNoteId() + " | "
                + noteEditFormModel.getStudentId() + " | "
                + noteEditFormModel.getDescription() + " | "
                + noteEditFormModel.getTeacherId() + " | "
                + noteEditFormModel.getDate());
    }

    /**
     * Gets the data currently in the edited form fields as an UpdateDisciplinaryNoteCommand.
     *
     * @return The command object representing the edited note data.
     */
    public UpdateDisciplinaryNoteCommand getEditedNoteData() {
        return noteEditFormModel.toCommand();
    }

    /**
     * Simulates a click on the Cancel button.
     * Added to satisfy requirement R10.
     */
    public void clickCancel() {
        System.out.println("[Presentation] NoteEditView: Cancel button clicked.");
        // This directly calls redirectToRegistryScreen on the RegistryController
        // as per the sequence diagram's interaction.
        registryController.redirectToRegistryScreen();
    }

    /**
     * Simulates a click on the Save button.
     * Added to satisfy requirement R6.
     */
    public void clickSave() {
        System.out.println("[Presentation] NoteEditView: Save button clicked.");
        // Collects edited data into UpdateDisciplinaryNoteCommand
        UpdateDisciplinaryNoteCommand command = getEditedNoteData();
        // Delegates saving to the controller
        noteController.saveNote(command);
    }

    /**
     * Redirects to the registry screen.
     * Added to satisfy requirement R9, R10.
     */
    public void redirectToRegistryScreen() {
        System.out.println("[Presentation] NoteEditView: Navigating to Registry Screen.");
        registryController.redirectToRegistryScreen();
    }

    /**
     * Displays an error message to the user.
     * Added to satisfy requirement R11.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("[Presentation] NoteEditView: ERROR displayed to Admin: " + message);
    }
}