package presentation;

import domain.NoteDetailDTO;

/**
 * Presentation Layer component responsible for displaying note details and error messages to the user.
 * This class acts as the UI component in a MVP/MVC pattern.
 */
public class NoteDetailsView {

    /**
     * Displays the detailed information of a note to the user.
     * As specified by the sequence diagram, this method is called after successful data retrieval.
     * In a real application, this would update GUI elements. Here, it prints to console.
     *
     * @param dto The NoteDetailDTO containing the details to display.
     */
    public void displayNoteDetails(NoteDetailDTO dto) {
        System.out.println("\n--- Note Details View ---");
        if (dto != null) {
            System.out.println(dto.toString());
        } else {
            System.out.println("No note details available.");
        }
        System.out.println("-------------------------\n");
    }

    /**
     * Displays an error message to the user (REQ-012), as specified by the class diagram.
     * As specified by the sequence diagram, this method is called when an error occurs
     * during data retrieval or processing.
     * In a real application, this would show an error dialog or message on screen.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("Error: " + message);
        System.err.println("-------------\n");
    }
}