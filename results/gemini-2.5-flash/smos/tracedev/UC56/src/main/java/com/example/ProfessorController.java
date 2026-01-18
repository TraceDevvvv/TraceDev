package com.example;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Professor's class list view.
 * Orchestrates interactions between the view, view model, and academic service.
 */
public class ProfessorController {
    private AcademicService academicService;
    private ProfessorView view;
    private ClassListViewModel viewModel;
    private AuthenticationService authenticationService; // Added for EC1

    /**
     * Constructor for ProfessorController.
     *
     * @param service The AcademicService instance.
     * @param view The ProfessorView instance.
     * @param viewModel The ClassListViewModel instance.
     * @param authenticationService The AuthenticationService instance.
     */
    public ProfessorController(AcademicService service, ProfessorView view, ClassListViewModel viewModel, AuthenticationService authenticationService) {
        this.academicService = service;
        this.view = view;
        this.viewModel = viewModel;
        this.authenticationService = authenticationService;
        // The view needs a reference to its controller for user action callbacks
        this.view.setController(this);
        this.view.setViewModel(this.viewModel); // View also needs access to ViewModel for displaying data
        System.out.println("ProfessorController initialized.");
    }

    /**
     * Initializes the professor's view by fetching academic years.
     * This method is called when the professor first accesses their "Digital Log".
     *
     * @param professorId The ID of the professor whose data is to be loaded.
     */
    public void initializeProfessorView(String professorId) {
        System.out.println("\nProfessorController: initializeProfessorView(" + professorId + ")");

        // EC1: Check if professor is logged in and ID matches
        if (!authenticationService.isLoggedIn() || !authenticationService.getLoggedInProfessorId().equals(professorId)) {
            view.showErrorMessage("Authentication error: User not logged in or ID mismatch.");
            return;
        }

        viewModel.setProfessorId(professorId);

        try {
            // Retrieves academic years for the professor
            List<AcademicYear> academicYears = academicService.getAcademicYearsForProfessor(professorId);
            viewModel.setAcademicYears(academicYears);
            view.displayAcademicYears(academicYears);

            // If there are academic years, automatically select the first one and load classes
            if (!academicYears.isEmpty()) {
                AcademicYear firstYear = academicYears.get(0); // Assuming we select the first one by default
                viewModel.setSelectedAcademicYear(firstYear);
                // Automatically trigger class loading for the first year, similar to onAcademicYearSelected
                System.out.println("ProfessorController: Automatically selecting first academic year: " + firstYear.getId());
                onAcademicYearSelected(firstYear.getId());
            } else {
                view.displayClasses(null); // Display no classes if no academic years
            }

        } catch (ServiceException e) {
            System.err.println("ProfessorController: Error initializing view: " + e.getMessage());
            view.showErrorMessage("Failed to load academic years: " + e.getMessage());
        }
    }

    /**
     * Handles the event when an academic year is selected by the professor.
     * Fetches and displays the classes for the selected year.
     *
     * @param selectedYearId The ID of the academic year selected by the user.
     */
    public void onAcademicYearSelected(String selectedYearId) {
        System.out.println("\nProfessorController: onAcademicYearSelected(" + selectedYearId + ")");

        String professorId = viewModel.getProfessorId();
        if (professorId == null) {
            view.showErrorMessage("Professor ID not set. Cannot load classes.");
            return;
        }

        // Update the selected academic year in the view model
        Optional<AcademicYear> yearOptional = viewModel.getAcademicYears().stream()
                .filter(ay -> ay.getId().equals(selectedYearId))
                .findFirst();

        if (yearOptional.isPresent()) {
            viewModel.setSelectedAcademicYear(yearOptional.get());
        } else {
            view.showErrorMessage("Selected academic year not found in list: " + selectedYearId);
            return;
        }

        try {
            // Retrieves classes for the selected academic year
            List<Class> classes = academicService.getClassesForAcademicYear(professorId, selectedYearId);
            viewModel.setClasses(classes);
            view.displayClasses(classes);
        } catch (ServiceException e) {
            System.err.println("ProfessorController: Error loading classes: " + e.getMessage());
            view.showErrorMessage("Failed to load classes for selected year: " + e.getMessage());
        }
    }
}