package com.example;

/**
 * Represents the web interface boundary.
 * Ensures WebInterface consistency and delegates user actions to the controller.
 */
public class WebInterface {
    private ViewStudentNotesController controller;
    private Session session;

    /**
     * Constructs a WebInterface with the given controller and session.
     *
     * @param controller the controller to delegate to
     * @param session the current user session
     */
    public WebInterface(ViewStudentNotesController controller, Session session) {
        this.controller = controller;
        this.session = session;
    }

    /**
     * Initiates the view notes flow when the administrator clicks "Notes" for a student.
     *
     * @param studentId the ID of the student whose notes are to be viewed
     */
    public void initiateViewNotes(String studentId) {
        // Delegates the action to the controller
        StudentNotesViewModel viewModel = controller.execute(studentId);
        render(viewModel);
    }

    /**
     * Renders the view model to display the list of student notes.
     *
     * @param viewModel the view model containing notes data
     */
    public void render(StudentNotesViewModel viewModel) {
        // In a real implementation, this would update the UI.
        // For demonstration, we print to console.
        System.out.println("Rendering notes for student: " + viewModel.getStudentId());
        for (NoteViewModel note : viewModel.getNotes()) {
            System.out.println("Date: " + note.getFormattedDate() + ", Content: " + note.getContent());
        }
    }

    /**
     * Displays an access denied message to the user.
     */
    public void displayAccessDenied() {
        System.out.println("Access Denied: User is not authenticated as administrator.");
    }

    /**
     * Simulates the UI action "Clicks \"Notes\" for a student".
     * This method corresponds to sequence diagram message m5.
     *
     * @param studentId the student ID
     */
    public void clicksNotesForStudent(String studentId) {
        initiateViewNotes(studentId);
    }

    /**
     * Simulates the UI rendering the notes list.
     * This method corresponds to sequence diagram message m27.
     */
    public void renderNotesList() {
        // This method is called by the controller or interactor to trigger rendering.
        // In our current flow, rendering is done inside render(StudentNotesViewModel).
        // For traceability, we implement it as a separate method that delegates.
        System.out.println("UI rendering notes list");
    }

    /**
     * Simulates the UI displaying the list of student notes.
     * This method corresponds to sequence diagram return message m28.
     */
    public void displayListOfStudentNotes() {
        // This method is the final display action.
        // In our current flow, this is achieved by the render method.
        // For traceability, we implement it as a separate method.
        System.out.println("UI displaying list of student notes");
    }
}