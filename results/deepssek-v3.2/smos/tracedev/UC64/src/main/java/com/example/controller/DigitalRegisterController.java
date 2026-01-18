package com.example.controller;

import com.example.service.DigitalRegisterService;
import com.example.dto.RegisterDTO;
import com.example.model.Session;
import com.example.exception.ConnectionException;
import java.util.List;

public class DigitalRegisterController {
    private DigitalRegisterService digitalRegisterService;

    public DigitalRegisterController(DigitalRegisterService digitalRegisterService) {
        this.digitalRegisterService = digitalRegisterService;
    }

    /**
     * Shows the academic year selection screen.
     * As per sequence diagram step after clicking "Digital Register" button.
     */
    public void showYearSelection(Session session) {
        if (!session.isValid()) {
            throw new SecurityException("Session is not valid");
        }
        System.out.println("Showing academic year selection for user: " + session.getUserId());
        // In a real UI, this would navigate to a year selection screen.
    }

    /**
     * Implements sequence diagram message: selectYear(academicYear : int)
     */
    public void selectYear(Session session, int academicYear) {
        if (!session.isValid()) {
            throw new SecurityException("Invalid session");
        }
        // Delegates to the existing getRegistersByYear method which already handles the flow
        List<RegisterDTO> registers = getRegistersByYear(session, academicYear);
        displayRegisters(registers);
    }

    /**
     * Fetches registers for a selected academic year and returns them as DTOs.
     * Implements the main flow from sequence diagram.
     */
    public List<RegisterDTO> getRegistersByYear(Session session, int academicYear) {
        try {
            return digitalRegisterService.getRegistersByYear(session, academicYear);
        } catch (ConnectionException e) {
            // Handle connection error as per sequence diagram.
            displayError("Connection failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Displays the list of registers to the user (UI).
     */
    public void displayRegisters(List<RegisterDTO> registers) {
        System.out.println("Displaying digital registers:");
        for (RegisterDTO dto : registers) {
            System.out.println("  " + dto);
        }
    }

    /**
     * Displays an error message to the user.
     * Implements sequence diagram message: ErrorMessage
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Called when user interrupts the operation (cancel).
     * As per sequence diagram opt block.
     */
    public void cancelOperation(Session session) {
        digitalRegisterService.cancelRequest(session);
        operationCancelled();
    }

    /**
     * Notifies that the operation was cancelled.
     */
    public void operationCancelled() {
        System.out.println("Operation cancelled by user.");
    }
}