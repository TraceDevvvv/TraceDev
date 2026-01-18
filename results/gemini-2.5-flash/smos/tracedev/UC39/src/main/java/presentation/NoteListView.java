package presentation;

import domain.NoteSummaryDTO;
import java.util.List;

/**
 * Presentation Layer component responsible for displaying a list of notes.
 * This class acts as the initial entry point for selecting a note to view its details (REQ-004).
 */
public class NoteListView {
    private NoteDetailsController noteDetailsController; // Dependency for initiating detail view

    /**
     * Constructs a NoteListView.
     * @param noteDetailsController The controller responsible for handling note detail requests.
     */
    public NoteListView(NoteDetailsController noteDetailsController) {
        this.noteDetailsController = noteDetailsController;
    }

    /**
     * Displays a list of note summaries to the user.
     * (Placeholder method, actual implementation would involve GUI rendering).
     * @param notes A list of NoteSummaryDTO objects to display.
     */
    public void displayNoteList(List<NoteSummaryDTO> notes) {
        System.out.println("\n--- Note List View ---");
        if (notes == null || notes.isEmpty()) {
            System.out.println("No notes to display.");
            return;
        }
        System.out.println("Available Notes:");
        for (NoteSummaryDTO note : notes) {
            System.out.println("  ID: " + note.getNoteId() + ", Student: " + note.getStudentName() + ", Desc: " + note.getBriefDescription());
        }
        System.out.println("----------------------\n");
    }

    /**
     * Simulates a user selecting a note from the list.
     * This method triggers the `viewNoteDetails` operation in the NoteDetailsController,
     * as depicted in the sequence diagram.
     * @param noteId The ID of the selected note.
     */
    public void onNoteSelected(String noteId) {
        System.out.println("\n[NoteListView] Administrator clicks \"Details\" button for note ID: " + noteId);
        // Delegate to the controller to handle the viewing of note details (REQ-005)
        noteDetailsController.viewNoteDetails(noteId);
    }
}