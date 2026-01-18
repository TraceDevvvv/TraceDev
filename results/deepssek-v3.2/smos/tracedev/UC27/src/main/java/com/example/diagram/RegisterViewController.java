package com.example.diagram;

import java.util.List;
import java.util.Map;

/**
 * View controller for handling register view interactions.
 */
public class RegisterViewController {
    private RegisterViewModel viewModel;

    public RegisterViewController(RegisterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public RegisterViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(RegisterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Shows the academic year selection screen.
     * Called when the administrator clicks "Digital Register".
     * This corresponds to sequence message m3: shows academic year selection screen.
     */
    public void showAcademicYearSelection() {
        // m3: shows academic year selection screen
        System.out.println("Academic year selection screen displayed.");
    }

    /**
     * Called when an academic year is selected by the administrator.
     * This corresponds to sequence message m4: selects school year.
     * @param year the selected academic year.
     */
    public void onAcademicYearSelected(String year) {
        // m4: selects school year
        viewModel.selectAcademicYear(year);
        List<RegisterDTO> registers = viewModel.loadRegistersForYear();
        Map<String, List<RegisterDTO>> groupedRegisters = viewModel.organizeRegistersByClass(registers);
        // m24: display registers grouped by class
        displayRegistersGroupedByClass(groupedRegisters);
        // m25: shows digital records by class (implied in the display)
    }

    /**
     * Displays a list of registers.
     * @param registers the list of RegisterDTO objects to display.
     */
    public void displayRegisters(List<RegisterDTO> registers) {
        System.out.println("Displaying registers:");
        for (RegisterDTO reg : registers) {
            System.out.println(reg);
        }
    }

    /**
     * Displays registers grouped by class to satisfy requirement REQ-005.
     * This corresponds to sequence message m24: display registers grouped by class.
     * @param groupedRegisters a map where key is className and value is list of RegisterDTO for that class.
     */
    public void displayRegistersGroupedByClass(Map<String, List<RegisterDTO>> groupedRegisters) {
        // m24: display registers grouped by class
        System.out.println("Displaying registers grouped by class:");
        for (Map.Entry<String, List<RegisterDTO>> entry : groupedRegisters.entrySet()) {
            System.out.println("Class: " + entry.getKey());
            for (RegisterDTO reg : entry.getValue()) {
                System.out.println("  - " + reg);
            }
        }
        // m25: shows digital records by class (implied by the display)
    }

    /**
     * Shows an error message when access is denied.
     * This corresponds to sequence message m12: shows error message.
     * @param message the error message.
     */
    public void showErrorMessage(String message) {
        // m12: shows error message
        System.err.println("Error: " + message);
    }

    /**
     * Handles connection interruption notification from the administrator.
     * Called in the alternative flow for SMOS connection interruption.
     */
    public void handleConnectionInterruption() {
        // This method simulates the admin notifying about connection interruption.
        System.out.println("Connection interrupted by administrator.");
        // In real scenario, would interact with SMOSConnectionManager.
    }

    /**
     * Displays a connection error message.
     */
    public void displayConnectionError() {
        System.err.println("Connection error: SMOS connection interrupted.");
    }
}