package com.example;

/**
 * Controller for the View Student Notes use case.
 * Supports requirement R4 - Requires prior execution of StudentRegisterController.
 */
public class ViewStudentNotesController {
    private ViewStudentNotesUseCase useCase;
    private Session session;
    // Assuming a dependency on StudentRegisterController for requirement R4
    private StudentRegisterController studentRegisterController;

    /**
     * Constructs a ViewStudentNotesController with the given use case and session.
     *
     * @param useCase the use case interactor
     * @param session the current session for authentication
     * @param studentRegisterController the controller for student registration (requirement R4)
     */
    public ViewStudentNotesController(ViewStudentNotesUseCase useCase, Session session,
                                      StudentRegisterController studentRegisterController) {
        this.useCase = useCase;
        this.session = session;
        this.studentRegisterController = studentRegisterController;
    }

    /**
     * Executes the view student notes flow for the given student ID.
     * Validates administrator authentication via session.
     *
     * @param studentId the ID of the student whose notes are to be viewed
     * @return the view model containing the student's notes
     * @throws SecurityException if the user is not authenticated as administrator
     */
    public StudentNotesViewModel execute(String studentId) {
        // Requirement R1: user must be logged in as administrator
        if (!session.validateAdmin()) {
            throw new SecurityException("Access denied: User is not an administrator.");
        }
        // Requirement R4: prior execution of StudentRegisterController is assumed.
        // In a real scenario, we might check that the student exists.
        System.out.println("Controller: Executing view notes for student " + studentId);
        return useCase.execute(studentId);
    }
}