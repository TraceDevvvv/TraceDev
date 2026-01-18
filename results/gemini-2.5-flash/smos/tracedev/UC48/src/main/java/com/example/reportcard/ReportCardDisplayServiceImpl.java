package com.example.reportcard;

import java.util.Objects;

/**
 * Mock implementation of ReportCardDisplayService for simulation.
 * In a real application, this would interact with a UI component or data service.
 */
public class ReportCardDisplayServiceImpl implements ReportCardDisplayService {

    private String currentSelectedReportCardId; // Simulates the currently viewed/selected report card

    /**
     * Constructs a mock ReportCardDisplayServiceImpl.
     * For demonstration, allows setting an initial selected report card.
     *
     * @param initialSelectedReportCardId The ID of a report card to initially set as selected.
     */
    public ReportCardDisplayServiceImpl(String initialSelectedReportCardId) {
        this.currentSelectedReportCardId = initialSelectedReportCardId;
    }

    public ReportCardDisplayServiceImpl() {
        this(null);
    }

    @Override
    public void displayReportCard(String reportCardId) {
        System.out.println("[ReportCardDisplayService] User Interface: Displaying report card with ID: " + reportCardId);
        this.currentSelectedReportCardId = reportCardId; // Update selected ID
    }

    @Override
    public String getSelectedReportCardId() {
        System.out.println("[ReportCardDisplayService] Retrieving selected report card ID: " + currentSelectedReportCardId);
        return currentSelectedReportCardId;
    }

    /**
     * Setter for the selected report card ID, useful for simulating user selection.
     * @param currentSelectedReportCardId The ID to set as currently selected.
     */
    public void setCurrentSelectedReportCardId(String currentSelectedReportCardId) {
        this.currentSelectedReportCardId = currentSelectedReportCardId;
    }
}