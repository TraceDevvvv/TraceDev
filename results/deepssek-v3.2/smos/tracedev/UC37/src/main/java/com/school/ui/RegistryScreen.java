package com.school.ui;

/**
 * UI boundary representing the registry screen.
 */
public class RegistryScreen {
    private DisciplinaryNoteController controller;

    public RegistryScreen(DisciplinaryNoteController controller) {
        this.controller = controller;
    }

    public void showNewNoteForm() {
        System.out.println("Showing new disciplinary note form.");
    }

    /**
     * Called when the New Note button is clicked, as per entry condition.
     */
    public void onNewNoteButtonClick() {
        showNewNoteForm();
        // For demonstration, we create a sample request
        CreateNoteRequest request = new CreateNoteRequest(
                "STU001",
                new java.util.Date(),
                "TCH001",
                "Disruptive behavior in class."
        );
        CreateNoteResponse response = controller.createNote(request);
        if (response.isSuccess()) {
            System.out.println("Note created successfully with ID: " + response.getNoteId());
        } else {
            System.out.println("Failed to create note: " + response.getMessage());
        }
        returnToRegistry();
    }

    /**
     * Returns to the registry screen as per exit condition.
     */
    public void returnToRegistry() {
        System.out.println("Returning to registry screen.");
    }

    public void showRegistryScreen() {
        System.out.println("Registry screen displayed.");
    }
}