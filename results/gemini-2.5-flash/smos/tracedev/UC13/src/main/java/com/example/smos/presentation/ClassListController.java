package com.example.smos.presentation;

import com.example.smos.application.ViewClassesUseCase;
import com.example.smos.domain.AcademicYear;
import com.example.smos.domain.Class;
import com.example.smos.exception.SMOSConnectionException;

import java.util.List;

/**
 * Controller for managing class lists.
 * It mediates between the ClassListView and the ViewClassesUseCase.
 */
public class ClassListController {
    private final ViewClassesUseCase viewClassesUseCase;
    private ClassListView view; // The controller holds a reference to its view

    /**
     * Constructs a ClassListController with its dependencies.
     * @param viewClassesUseCase The use case for viewing classes.
     */
    public ClassListController(ViewClassesUseCase viewClassesUseCase) {
        this.viewClassesUseCase = viewClassesUseCase;
    }

    /**
     * Sets the view associated with this controller.
     * This is often done after controller construction in a setup phase.
     * @param view The ClassListView instance.
     */
    public void setView(ClassListView view) {
        this.view = view;
    }

    /**
     * Handles the initial request to view the class list.
     * It fetches available academic years and instructs the view to display them.
     */
    public void handleViewClassListRequest() {
        System.out.println("[Controller] handleViewClassListRequest() called.");
        try {
            List<AcademicYear> academicYears = viewClassesUseCase.getAvailableAcademicYears();
            if (view != null) {
                view.displayAcademicYearSelection(academicYears);
            }
        } catch (SMOSConnectionException e) {
            System.err.println("[Controller] Error retrieving academic years: " + e.getMessage());
            if (view != null) {
                view.showErrorMessage("Connection to SMOS server interrupted.");
            }
        }
    }

    /**
     * Handles the selection of an academic year by the user.
     * It fetches classes for the selected year and updates the view.
     * @param yearId The ID of the selected academic year.
     */
    public void selectAcademicYear(String yearId) {
        System.out.println("[Controller] selectAcademicYear(" + yearId + ") called.");
        try {
            List<Class> classes = viewClassesUseCase.getClassesByAcademicYear(yearId);
            if (view != null) {
                view.displayClassManagementScreen(); // Transition to the class management screen
                view.displayClassList(classes); // Display the fetched classes
            }
        } catch (SMOSConnectionException e) {
            System.err.println("[Controller] Error retrieving classes: " + e.getMessage());
            if (view != null) {
                view.showErrorMessage("Connection to SMOS server interrupted.");
            }
        }
    }

    /**
     * Handles the cancellation of the class list operation (R11).
     * This method doesn't perform any complex logic but signals back to the view
     * that the operation has been acknowledged as cancelled.
     */
    public void cancelClassListOperation() {
        System.out.println("[Controller] cancelClassListOperation() called. Operation acknowledged as cancelled.");
        // In a real application, this might involve cleaning up temporary state or logging.
        // For this scenario, simply acknowledging the cancellation is sufficient.
        if (view != null) {
            // The sequence diagram shows "Controller --> View : operationCancelled()",
            // which implies the controller might trigger a method on the view.
            // Here, the view already knows it's cancelled and handles the next step.
            // No explicit 'operationCancelled()' method on View, as per CD.
        }
    }
}