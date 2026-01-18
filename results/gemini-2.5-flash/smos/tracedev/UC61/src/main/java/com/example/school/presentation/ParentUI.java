package com.example.school.presentation;

import com.example.school.application.ParentService;
import com.example.school.dto.AcademicRecordItemDTO;
import com.example.school.dto.ChildAcademicSummaryDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents the User Interface for parents to interact with the system.
 * This class is responsible for initiating requests and displaying results.
 */
public class ParentUI {
    // Parent's ID, typically set upon login or session
    private String parentId;
    // The currently selected child's ID, maintained in the UI state
    private String selectedChildId;

    private ParentService parentService;

    /**
     * Constructs a new ParentUI instance.
     *
     * @param parentService The ParentService instance to interact with the application layer.
     * @param loggedInParentId The ID of the parent currently logged in.
     */
    public ParentUI(ParentService parentService, String loggedInParentId) {
        this.parentService = parentService;
        this.parentId = loggedInParentId;
        // In a real UI, selectedChildId would be set dynamically by user selection.
        // For this example, it's set when requestChildAcademicSummary is called.
    }

    /**
     * Initiates the request for a child's academic summary.
     * This method acts as the entry point from the UI perspective.
     *
     * @param childId The ID of the child whose academic summary is to be viewed.
     */
    public void viewChildAcademicStatus(String childId) { // Renamed from requestChildAcademicSummary
        System.out.println("\\nParentUI: Parent '" + parentId + "' requesting academic summary for child '" + childId + "'.");
        this.selectedChildId = childId; // Update UI's internal state for selected child.

        try {
            // Call the application layer to get the summary
            ChildAcademicSummaryDTO summary = parentService.getChildAcademicSummary(this.parentId, this.selectedChildId);

            // Display the summary to the parent
            displaySummaryTable(summary); // Updated call
        } catch (IllegalArgumentException e) {
            System.err.println("ParentUI: Error displaying summary: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ParentUI: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays the child's academic summary on the UI.
     * This method would typically render data on a web page or a desktop application UI.
     *
     * @param summary The ChildAcademicSummaryDTO containing the data to display.
     */
    public void displaySummaryTable(ChildAcademicSummaryDTO summary) { // Renamed from displayChildAcademicSummary
        System.out.println("\\n--- Child Academic Summary ---");
        System.out.println("Student ID: " + summary.getStudentId());
        System.out.println("Student Name: " + summary.getStudentName());
        System.out.println("Academic Records:");

        if (summary.getAcademicRecords().isEmpty()) {
            System.out.println("  No academic records found.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (AcademicRecordItemDTO record : summary.getAcademicRecords()) {
                System.out.println("  -------------------------");
                System.out.println("  Record Date: " + record.getRecordDate().format(formatter));
                System.out.println("  Absences: " + record.getAbsences());
                System.out.println("  Delays: " + record.getDelayCount());
                System.out.println("  Disciplinary Notes: " + (record.getDisciplinaryNotes().isEmpty() ? "N/A" : record.getDisciplinaryNotes()));
                System.out.println("  Justification: " + (record.getJustification().isEmpty() ? "N/A" : record.getJustification()));
            }
        }
        System.out.println("--- End of Summary ---\\n");
    }
}