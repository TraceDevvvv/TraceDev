package ui.entrypoint;

/**
 * DTO representing the form for creating a new disciplinary note.
 * This class is a placeholder as its structure was not detailed in the class diagram,
 * but it is returned by DisciplinaryNoteController.displayNewNoteForm().
 */
public class NewNoteForm {
    // In a real application, this would contain fields for studentId, date, teacherId, description, etc.
    // For this exercise, it remains empty as its internal structure wasn't specified.

    /**
     * Constructor for NewNoteForm.
     */
    public NewNoteForm() {
        System.out.println("NewNoteForm: Initializing a new disciplinary note form.");
    }

    @Override
    public String toString() {
        return "NewNoteForm{} - Ready for user input.";
    }
}