package com.example;

/**
 * User interface for teaching form interactions.
 * Handles displaying forms and interacting with controllers.
 */
public class TeachingFormUI {
    private TechnologyViewer techViewer;
    private InsertTeachingController controller;
    private Session session;

    /**
     * Constructor for TeachingFormUI.
     * Assumption: Initializes with default dependencies.
     */
    public TeachingFormUI() {
        this.techViewer = new TechnologyViewer();
        // Assumption: TeachingRepository and ErrorHandler are created here for simplicity.
        TeachingRepository repository = new TeachingRepositoryImpl(null); // null DataSource for example
        ErrorHandler errorHandler = new ErrorHandler();
        this.controller = new InsertTeachingController(repository, errorHandler);
        this.session = new Session(); // Assumption: Session is managed elsewhere
        errorHandler.setUi(this);
    }

    /**
     * Sets the session for authentication checks.
     * @param session the current session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Displays the teaching form.
     */
    public void displayForm() {
        System.out.println("Displaying teaching form...");
    }

    /**
     * Shows a new teaching form as per sequence diagram.
     */
    public void showNewTeachingForm() {
        System.out.println("Showing new teaching form...");
        displayForm();
    }

    /**
     * Shows a success message to the administrator.
     * @param message the success message
     */
    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Shows an error message to the administrator.
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Notifies about an error, used by ErrorHandler.
     * @param errorMessage the error message
     */
    public void notifyError(String errorMessage) {
        showErrorMessage(errorMessage);
    }

    /**
     * Initiates viewing technology as per entry condition.
     */
    public void viewTechnology() {
        techViewer.viewTechnology();
    }

    /**
     * Simulates the administrator clicking "New Teaching" button.
     */
    public void onNewTeachingClicked() {
        // Check authentication as per sequence diagram
        if (session.isAuthenticated()) {
            showNewTeachingForm();
        } else {
            showErrorMessage("User not authenticated");
        }
    }

    /**
     * Simulates the administrator filling the form.
     * @param teachingName the name entered
     */
    public void fillForm(String teachingName) {
        TeachingForm form = new TeachingForm();
        form.setTeachingName(teachingName);
        System.out.println("Form filled with teaching name: " + teachingName);
        // Update form data internally
        updateFormData(form);
    }

    /**
     * Updates form data as per sequence diagram.
     * @param form the teaching form
     */
    private void updateFormData(TeachingForm form) {
        // Update form data internally
        System.out.println("Updating form data...");
    }

    /**
     * Simulates the administrator clicking "Save" button.
     * @param form the filled teaching form
     */
    public void onSaveClicked(TeachingForm form) {
        InsertTeachingRequest request = controller.createInsertTeachingRequest(form);
        InsertTeachingResponse response = controller.execute(request);
        if (response.isSuccess()) {
            showSuccessMessage("Teaching saved successfully!");
        } else {
            showErrorMessage(response.getErrorMessage());
        }
    }

    /**
     * Simulates the administrator cancelling the operation.
     */
    public void onCancelClicked() {
        controller.cancelInsertion();
    }

    /**
     * Display form to insert teaching as per sequence diagram.
     * @param admin the administrator
     */
    public void displayFormToInsertTeaching(Administrator admin) {
        System.out.println("Display form to insert teaching for administrator: " + admin.getUsername());
    }

    /**
     * Show cancellation message as per sequence diagram.
     * @param admin the administrator
     */
    public void showCancellationMessage(Administrator admin) {
        System.out.println("Operation cancelled for administrator: " + admin.getUsername());
    }

    /**
     * Show connection error message as per sequence diagram.
     * @param admin the administrator
     */
    public void showConnectionErrorMessage(Administrator admin) {
        System.out.println("Connection error for administrator: " + admin.getUsername());
    }
}