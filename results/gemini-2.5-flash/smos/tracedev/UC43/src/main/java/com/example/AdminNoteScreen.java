package com.example;

/**
 * Boundary class representing the Administrator's Note Screen.
 * It handles displaying information and user notifications to the Administrator.
 */
public class AdminNoteScreen {

    // Added to facilitate the sequence diagram's message flow (AdminNoteScreen -> AdminNoteController)
    private AdminNoteController controller;

    /**
     * Default constructor for AdminNoteScreen.
     */
    public AdminNoteScreen() {
        // Default constructor, no-arg
    }

    /**
     * Sets the controller for this screen to enable interaction.
     * This allows the screen to delegate user actions to the controller.
     * @param controller The AdminNoteController instance.
     */
    public void setController(AdminNoteController controller) {
        this.controller = controller;
    }

    /**
     * Simulates the Administrator clicking the "Delete" button for a specific note.
     * This method acts as the entry point for the UI event as per the sequence diagram
     * (message "clicks 'Delete' button (noteId)" from Administrator to AdminNoteScreen).
     * It then delegates the actual deletion request to the AdminNoteController.
     *
     * @param noteId The ID of the note to be deleted.
     */
    public void clicksDeleteButton(String noteId) {
        System.out.println("\nAdminNoteScreen: User initiates 'Delete' action for note ID: " + noteId);
        if (controller != null) {
            // Delegate the action to the controller as per sequence diagram:
            // AdminNoteScreen -> AdminNoteController: handleDeleteNote(noteId)
            controller.handleDeleteNote(noteId);
        } else {
            notifyUser("Error: AdminNoteController not set for AdminNoteScreen. Cannot process delete request.");
        }
    }

    /**
     * Displays the details of a note on the screen.
     *
     * @param note The Note object to display.
     */
    public void displayNoteDetails(Note note) {
        System.out.println("\n--- Admin Note Screen ---");
        System.out.println("Displaying Note Details:");
        if (note != null) {
            System.out.println("  ID: " + note.getId());
            System.out.println("  Student ID: " + note.getStudentId());
            System.out.println("  Content: " + note.getContent());
            System.out.println("  Parent Email: " + note.getParentEmail());
        } else {
            System.out.println("  No note details to display.");
        }
        System.out.println("-------------------------");
    }

    /**
     * Displays the registry screen, typically after an operation.
     * This is a placeholder for navigating back to a list view.
     */
    public void displayRegistryScreen() {
        System.out.println("\n--- Admin Note Screen ---");
        System.out.println("Displaying updated Note Registry Screen.");
        // In a real UI, this would render a list of notes.
        System.out.println("-------------------------");
    }

    /**
     * Notifies the user with a given message.
     *
     * @param message The message to display to the user.
     */
    public void notifyUser(String message) {
        System.out.println("\n--- Admin Note Screen (Notification) ---");
        System.out.println("ALERT: " + message);
        System.out.println("----------------------------------------");
    }
}