package com.example.app;

/**
 * Controller for managing the display of class details.
 * This class corresponds to the 'ClassDetailsController' class in the UML Class Diagram.
 * It orchestrates the "Administrator Views Class Details" use case, including preconditions (R3, R4) and error handling (R12).
 */
public class ClassDetailsController {
    private ClassService classService;
    private ClassDetailsView classDetailsView;
    private SessionManager sessionManager; // Added to satisfy requirement R3
    private SystemContext systemContext; // Added to satisfy requirement R4

    /**
     * Constructs a new ClassDetailsController.
     *
     * @param service The service responsible for class data.
     * @param view The view responsible for displaying class details.
     * @param sessionManager The manager for checking session authentication (R3).
     * @param systemContext The context for checking system state (R4).
     */
    public ClassDetailsController(ClassService service, ClassDetailsView view,
                                  SessionManager sessionManager, SystemContext systemContext) {
        this.classService = service;
        this.classDetailsView = view;
        this.sessionManager = sessionManager;
        this.systemContext = systemContext;
        // The view needs to know its controller to call handleShowDetailsClick
        this.classDetailsView.setController(this);
    }

    /**
     * Handles the request to show details for a specific class.
     * This method implements the main logic of the "Administrator Views Class Details" sequence diagram.
     *
     * @param classId The ID of the class whose details are to be displayed.
     * @throws ConnectionException If a connection error occurs during data retrieval (R12).
     */
    public void showClassDetails(String classId) throws ConnectionException {
        System.out.println("\nController: showClassDetails(" + classId + ") - Processing user action.");
        // Sequence Diagram: Controller -> SessionMgr: isAuthenticated()
        if (!sessionManager.isAuthenticated()) { // Precondition: Administrator IS logged in (R3)
            System.out.println("Controller: Administrator not logged in. Displaying error.");
            // Sequence Diagram: Controller -> View: displayErrorMessage(...)
            classDetailsView.displayErrorMessage("Administrator not logged in.");
            return;
        }

        // Sequence Diagram: Controller -> SysContext: isViewingLancoclassesCompleted()
        if (!systemContext.isViewingLancoclassesCompleted()) { // Precondition: ViewingLancoclasses use case IS completed (R4)
            System.out.println("Controller: Previous use case not completed. Displaying error.");
            // Sequence Diagram: Controller -> View: displayErrorMessage(...)
            classDetailsView.displayErrorMessage("Previous use case not completed.");
            return;
        }

        System.out.println("Controller: Preconditions met. Requesting class details from service.");
        // Sequence Diagram: Controller -> Service: getClassDetails(classId)
        try {
            ClassDetailsDTO details = classService.getClassDetails(classId); // Can throw ConnectionException

            if (details != null) {
                System.out.println("Controller: Received classDetailsDTO. Instructing view to display.");
                // Sequence Diagram: Controller -> View: displayClassDetails(classDetailsDTO)
                classDetailsView.displayClassDetails(details);
            } else {
                System.out.println("Controller: Class details not found for ID: " + classId + ". Displaying error.");
                classDetailsView.displayErrorMessage("Class with ID '" + classId + "' not found.");
            }
        } catch (ConnectionException e) {
            System.err.println("Controller: Caught ConnectionException: " + e.getMessage());
            // Sequence Diagram: Service --> Controller: ConnectionException
            // Sequence Diagram: Controller -> View: displayErrorMessage("Failed to retrieve class details...")
            classDetailsView.displayErrorMessage("Failed to retrieve class details. Please try again later. (" + e.getMessage() + ")");
            throw e; // Re-throwing as per sequence diagram showing controller throwing, though view displays error.
                     // The sequence diagram shows the controller calling displayErrorMessage, then returning.
                     // It doesn't explicitly show the exception propagating beyond the controller's immediate scope
                     // if handled, but the signature `throws ConnectionException` implies it can.
                     // For this implementation, the view is instructed to display the error and the exception is re-thrown.
        }
    }
}