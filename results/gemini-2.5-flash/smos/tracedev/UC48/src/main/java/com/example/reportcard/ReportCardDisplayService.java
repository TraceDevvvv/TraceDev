package com.example.reportcard;

/**
 * Interface for a service responsible for displaying report cards and managing selected report card context.
 * Added to satisfy REQ-002: Report Card Display Context.
 */
public interface ReportCardDisplayService {

    /**
     * Displays a specific report card to the user.
     *
     * @param reportCardId The ID of the report card to display.
     */
    void displayReportCard(String reportCardId);

    /**
     * Retrieves the ID of the currently selected or displayed report card.
     * This method might be used by a controller to get context for operations.
     *
     * @return The ID of the selected report card, or null if none is selected.
     */
    String getSelectedReportCardId();
}