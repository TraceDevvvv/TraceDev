package com.example.school;

import java.util.List;

/**
 * Represents the Professor's user interface.
 * This class interacts with controllers to initiate actions and display results.
 */
public class ProfessorView {
    private final ClassRegistryController classRegistryController;
    private final ClassListController classListController; // Added to satisfy requirement EC3

    /**
     * Constructs a ProfessorView with its controller dependencies.
     * Modified to satisfy requirement EC3.
     * @param registryController The ClassRegistryController instance.
     * @param listController The ClassListController instance.
     */
    public ProfessorView(ClassRegistryController registryController, ClassListController listController) {
        this.classRegistryController = registryController;
        this.classListController = listController;
        System.out.println("ProfessorView initialized.");
    }

    /**
     * Initializes the view, typically by loading the professor's classes.
     * Added to satisfy requirement EC3.
     * @param professorId The ID of the logged-in professor.
     * (EC1: Professor IS logged in, EC2: Professor HAS executed 'VisualLancoclasses')
     */
    public void initializeView(String professorId) {
        System.out.println("\n[ProfessorView] Initializing view for professor: " + professorId + " (EC1, EC2 satisfied)");
        try {
            // Sequence Diagram: View -> ClassListCtrl : getProfessorClasses
            List<ClassDTO> classList = classListController.getProfessorClasses(professorId);
            // Sequence Diagram: View -> View : displayClassList
            displayClassList(classList); // System displays list of classes (fulfilling EC3)
        } catch (SMOSConnectionException e) {
            // Sequence Diagram: ClassListCtrl --X View : throws SMOSConnectionException
            // m14: errorViewModel = handleSMOSConnectionError(exception)
            ErrorViewModel errorViewModel = classListController.handleSMOSConnectionError(e);
            // m15: return errorViewModel : ErrorViewModel (to View)
            displayErrorMessage(errorViewModel.getErrorMessage());
        }
    }

    /**
     * Displays a list of classes to the professor.
     * Modified to satisfy requirement EC3.
     * @param classList The list of ClassDTOs to display.
     */
    public void displayClassList(List<ClassDTO> classList) {
        System.out.println("\n[ProfessorView] Displaying Professor's Class List (EC3):");
        if (classList.isEmpty()) {
            System.out.println("  No classes found for this professor.");
            return;
        }
        for (ClassDTO classDTO : classList) {
            System.out.println("  - " + classDTO.getClassName() + " (ID: " + classDTO.getClassId() + ")");
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Displays the full class registry data to the professor.
     * @param registryData The ClassRegistryDTO to display.
     */
    public void showClassRegistry(ClassRegistryDTO registryData) {
        System.out.println("\n[ProfessorView] Displaying Class Registry for " + registryData.getClassName() + " (ID: " + registryData.getClassId() + ")");
        System.out.println("  Registry Date: " + registryData.getRegistryDate());
        System.out.println("  Absences (" + registryData.getAbsences().size() + "):\n");
        if (registryData.getAbsences().isEmpty()) {
            System.out.println("    No absences recorded.");
        } else {
            registryData.getAbsences().forEach(abs ->
                    System.out.println("    - " + abs.getStudentName() + " on " + abs.getDate() + ": " + abs.getType() + " - " + abs.getJustification()));
        }

        System.out.println("\n  Disciplinary Notes (" + registryData.getDisciplinaryNotes().size() + "):\n");
        if (registryData.getDisciplinaryNotes().isEmpty()) {
            System.out.println("    No disciplinary notes recorded.");
        } else {
            registryData.getDisciplinaryNotes().forEach(note ->
                    System.out.println("    - " + note.getStudentName() + " on " + note.getDate() + ": " + note.getDescription()));
        }

        System.out.println("\n  Delays (" + registryData.getDelays().size() + "):\n");
        if (registryData.getDelays().isEmpty()) {
            System.out.println("    No delays recorded.");
        } else {
            registryData.getDelays().forEach(delay ->
                    System.out.println("    - " + delay.getStudentName() + " on " + delay.getDate() + ": " + delay.getDurationMinutes() + "min - " + delay.getJustification()));
        }
        System.out.println("\n------------------------------------------");
    }

    /**
     * Simulates a professor clicking a "Register" button for a specific class.
     * This triggers the retrieval and display of the class registry.
     * @param classId The ID of the class for which the button was clicked.
     */
    public void onRegisterButtonClick(String classId) {
        System.out.println("\n[ProfessorView] Professor clicks 'Register' button for class ID: " + classId);
        try {
            // Sequence Diagram: View -> Controller : viewClassRegistry
            ClassRegistryDTO registryData = classRegistryController.viewClassRegistry(classId);
            // Sequence Diagram: View -> View : showClassRegistry
            showClassRegistry(registryData);
        } catch (SMOSConnectionException e) {
            // Sequence Diagram: Controller --X View : throws SMOSConnectionException
            // m39: errorViewModel = handleSMOSConnectionError(exception)
            ErrorViewModel errorViewModel = classRegistryController.handleSMOSConnectionError(e);
            // m40: return errorViewModel : ErrorViewModel (to View)
            displayErrorMessage(errorViewModel.getErrorMessage());
        }
    }

    /**
     * Displays an error message to the user.
     * Added to satisfy requirement ExC2.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n[ProfessorView] ERROR: " + message);
        // In a real UI, this would update a label or show a dialog.
    }
}