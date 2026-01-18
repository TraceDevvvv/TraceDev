package com.example;

import java.util.List;
import java.util.Scanner; // For simulating user input

/**
 * Mock UI View for the Professor.
 * Displays academic years and classes, and handles user input (simulated).
 */
public class ProfessorView {

    // Reference to the controller for callbacks (implicit in MVC, but often explicit in some frameworks)
    private ProfessorController controller;
    private ClassListViewModel viewModel; // View can read from ViewModel

    public ProfessorView() {
        System.out.println("\nProfessorView initialized.");
    }

    // Setter for controller, allowing the view to trigger controller actions
    public void setController(ProfessorController controller) {
        this.controller = controller;
    }

    // Setter for ViewModel, allowing the view to get data to display
    public void setViewModel(ClassListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Displays a list of academic years to the professor.
     *
     * @param years The list of AcademicYear objects to display.
     */
    public void displayAcademicYears(List<AcademicYear> years) {
        System.out.println("\n--- Professor UI: Displaying Academic Years ---");
        if (years == null || years.isEmpty()) {
            System.out.println("No academic years found.");
            return;
        }
        System.out.println("Available Academic Years:");
        for (AcademicYear year : years) {
            System.out.println("- " + year.getId() + ": " + year.getYear());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Displays a list of classes to the professor.
     *
     * @param classes The list of Class objects to display.
     */
    public void displayClasses(List<Class> classes) {
        System.out.println("\n--- Professor UI: Displaying Classes ---");
        if (classes == null || classes.isEmpty()) {
            System.out.println("No classes found for the selected academic year.");
            return;
        }
        System.out.println("Classes for " + (viewModel.getSelectedAcademicYear() != null ? viewModel.getSelectedAcademicYear().getYear() : "selected year") + ":");
        for (Class clazz : classes) {
            System.out.println("- " + clazz.getId() + ": " + clazz.getName() + " (Prof: " + clazz.getProfessorId() + ")");
        }
        System.out.println("------------------------------------");
    }

    /**
     * Displays an error message to the professor.
     *
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n!!! Professor UI Error: " + message + " !!!");
    }

    /**
     * Simulates getting the selected academic year ID from the UI.
     * In a real UI, this would come from a dropdown or similar component.
     * For demonstration, it returns a hardcoded value or can prompt the user.
     *
     * @return The ID of the selected academic year.
     */
    public String getSelectedAcademicYearIdFromUI() {
        // For demonstration, let's pick the first academic year if available from the view model
        if (viewModel != null && !viewModel.getAcademicYears().isEmpty()) {
            System.out.println("\nProfessorView: Simulating user selection of first academic year: " + viewModel.getAcademicYears().get(0).getId());
            return viewModel.getAcademicYears().get(0).getId();
        }
        System.out.println("\nProfessorView: Simulating user selection of AY2023 (default).");
        return "AY2023"; // Default if no years loaded yet
    }

    /**
     * Simulates a user clicking the "Digital Log" button.
     * This would trigger the initial load.
     *
     * @param professorId The ID of the logged-in professor.
     */
    public void clicksDigitalLogButton(String professorId) {
        System.out.println("\nProfessor UI: Professor clicks 'Digital Log' button for " + professorId);
        if (controller != null) {
            controller.initializeProfessorView(professorId);
        } else {
            System.err.println("ProfessorView: Controller not set, cannot initialize view.");
        }
    }

    /**
     * Simulates a user selecting an academic year.
     * This would trigger the class loading for that year.
     *
     * @param yearId The ID of the selected academic year.
     */
    public void selectAcademicYear(String yearId) {
        System.out.println("Professor UI: Professor selects academic year: " + yearId);
        if (controller != null) {
            controller.onAcademicYearSelected(yearId);
        } else {
            System.err.println("ProfessorView: Controller not set, cannot select academic year.");
        }
    }
}