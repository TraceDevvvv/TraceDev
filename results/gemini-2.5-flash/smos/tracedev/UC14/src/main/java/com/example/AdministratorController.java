package com.example;

import java.util.List;

// Presentation Controller for administrator actions related to classes.
public class AdministratorController {
    private ClassForm classForm;
    private InsertClassService insertClassService;
    private AuthenticationService authenticationService; // Added to satisfy requirement R3
    private SessionManager sessionManager;             // Added to satisfy requirement R3
    private ClassListView classListView;              // Added for R4, R5 preconditions

    // Dummy user/session for preconditions (R3)
    private String currentUser = "admin";
    private String currentSessionId = "dummy_session_123";

    /**
     * Constructor for AdministratorController, injecting its dependencies.
     *
     * @param classForm The presentation form for class creation.
     * @param insertClassService The application service for inserting classes.
     * @param authenticationService The service for user authentication (R3).
     * @param sessionManager The manager for user sessions (R3).
     * @param classListView The view for displaying class lists (R4, R5).
     */
    public AdministratorController(ClassForm classForm,
                                   InsertClassService insertClassService,
                                   AuthenticationService authenticationService,
                                   SessionManager sessionManager,
                                   ClassListView classListView) {
        this.classForm = classForm;
        this.insertClassService = insertClassService;
        this.authenticationService = authenticationService;
        this.sessionManager = sessionManager;
        this.classListView = classListView;
        System.out.println("AdministratorController initialized.");
    }

    /**
     * Setter for ClassForm to resolve circular dependency during initialization.
     * This allows the AdministratorController to be created first, and then the ClassForm
     * to be injected once it's available.
     * @param classForm The ClassForm instance.
     */
    public void setClassForm(ClassForm classForm) {
        this.classForm = classForm;
    }

    /**
     * Handles the event when the "New Class" button is clicked by the administrator.
     * This is the entry point for the sequence diagram.
     */
    public void handleNewClassButtonClick() {
        System.out.println("\nAdministratorController: Administrator clicks 'New Class' button.");

        // Preconditions R3, R4, R5 are met: Administrator is logged in, has performed ViewingLancoclasses use case,
        // and system is viewing class list. (Simulated)
        if (!authenticationService.authenticate(currentUser, "password")) {
            System.out.println("AdministratorController: Precondition R3 failed - Authentication failed.");
            return;
        }
        if (!sessionManager.isValidSession(currentSessionId)) {
            System.out.println("AdministratorController: Precondition R3 failed - Invalid session.");
            return;
        }
        // Simulate viewing class list for a dummy year.
        classListView.displayClassList("2023-2024"); // R4, R5
        System.out.println("AdministratorController: Preconditions R3, R4, R5 are met (simulated).");

        // Controller -> Form : displayForm()
        classForm.displayForm();

        // Simulate user interaction: get form data or cancellation
        ClassCreationDTO dto = classForm.getFormData();
        if (dto != null) {
            submitClassForm(dto);
        }
        // If dto is null, it means the user cancelled, and handleCancellation() would have been called.
    }

    /**
     * Submits the class creation form data for processing.
     *
     * @param dto The ClassCreationDTO containing the class data.
     */
    public void submitClassForm(ClassCreationDTO dto) {
        System.out.println("AdministratorController: Submitting class form with DTO.");

        // Controller -> Service : createClass(dto)
        List<String> errors = insertClassService.createClass(dto);

        if (errors.isEmpty()) {
            // Success path
            System.out.println("AdministratorController: Class creation service reported success.");
            // Controller -> Form : showSuccessMessage()
            classForm.showSuccessMessage();
            System.out.println("AdministratorController: New class created confirmation (to Admin).");
        } else {
            // Failure path (validation errors or SMOS connection error)
            System.out.println("AdministratorController: Class creation service reported errors.");
            // Controller -> Form : showErrorMessage(errors)
            classForm.showErrorMessage(errors);
            System.out.println("AdministratorController: Data error notification (to Admin).");
        }
    }

    /**
     * Handles the cancellation of the class creation operation (R16).
     */
    public void handleCancellation() {
        System.out.println("AdministratorController: Operation cancelled by user (R16).");
        // Controller --> Admin : Operation cancelled (simulated by print)
    }
}