package com.example.ui;

import com.example.dto.AcademicYearDTO;
import com.example.dto.ClassDTO;
import com.example.service.AuthenticationService;
import com.example.controller.ClassListController;
import com.example.model.Professor;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console UI simulating the interactions described in the sequence diagram.
 */
public class UI {
    private AuthenticationService authService;
    private ClassListController controller;
    private Professor currentProfessor;
    private Scanner scanner;

    public UI(AuthenticationService authService, ClassListController controller) {
        this.authService = authService;
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays login prompt and authenticates the professor.
     */
    public void start() {
        System.out.println("=== Professor Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Professor prof = authService.authenticate(username, password);
        if (prof != null) {
            currentProfessor = prof;
            System.out.println("Login successful.");
            // Simulate clicking "Digital Log" button
            displayDigitalLogMenu();
        } else {
            showErrorMessage("Login failed.");
        }
    }

    private void displayDigitalLogMenu() {
        System.out.println("\n=== Digital Log Menu ===");
        System.out.println("1. View Academic Years");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            renderAcademicYearList();
        } else {
            System.out.println("Goodbye.");
        }
    }

    /**
     * Renders the academic year list by calling the controller.
     */
    public void renderAcademicYearList() {
        List<AcademicYearDTO> years = controller.displayAcademicYearList();
        displayAcademicYearList(years);
    }

    /**
     * Displays the academic year list to the professor.
     * @param years list of AcademicYearDTO
     */
    public void displayAcademicYearList(List<AcademicYearDTO> years) {
        if (years == null || years.isEmpty()) {
            showErrorMessage("No data available");
            return;
        }
        System.out.println("\n--- Academic Years ---");
        for (int i = 0; i < years.size(); i++) {
            System.out.println((i + 1) + ". " + years.get(i).getYearLabel() + " (ID: " + years.get(i).getId() + ")");
        }
        System.out.println("0. Back");
        System.out.print("Select an academic year: ");
        String input = scanner.nextLine();
        if (!"0".equals(input)) {
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < years.size()) {
                    String selectedYearId = years.get(index).getId();
                    displayClassesForSelectedYear(selectedYearId);
                } else {
                    showErrorMessage("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                showErrorMessage("Invalid input.");
            }
        }
    }

    /**
     * Displays classes for the selected academic year.
     * @param academicYearId the academic year ID
     */
    public void displayClassesForSelectedYear(String academicYearId) {
        List<ClassDTO> classes = controller.displayClassesForYear(academicYearId);
        displayClassList(classes);
    }

    /**
     * Renders class list (private method).
     * Simulates sequence message "render class list".
     */
    private void renderClassList() {
        // This is already done in displayClassesForSelectedYear.
    }

    /**
     * Displays class list to the professor.
     * @param classes list of ClassDTO
     */
    public void displayClassList(List<ClassDTO> classes) {
        if (classes == null || classes.isEmpty()) {
            showErrorMessage("Connection Error");
            return;
        }
        System.out.println("\n--- Classes ---");
        for (ClassDTO clazz : classes) {
            System.out.println("Class: " + clazz.getName() + " (ID: " + clazz.getId() +
                               ", Professor: " + clazz.getProfessorName() + ")");
        }
        System.out.println("Press Enter to go back.");
        scanner.nextLine();
    }

    /**
     * Simulates the action of selecting academic year by professor.
     * Corresponds to sequence message "select academic year".
     */
    public void selectAcademicYear(String academicYearId) {
        displayClassesForSelectedYear(academicYearId);
    }

    /**
     * Simulates clicking the "Digital Log" button by professor.
     * Corresponds to sequence message "click \"Digital Log\" button".
     */
    public void clickDigitalLogButton() {
        displayDigitalLogMenu();
    }

    /**
     * Displays an error message.
     * @param message the error message
     */
    private void showErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * Simulates rendering academic year list (UI internal).
     * Corresponds to sequence message "render academic year list".
     */
    public void renderAcademicYearListInternal() {
        renderAcademicYearList();
    }
}