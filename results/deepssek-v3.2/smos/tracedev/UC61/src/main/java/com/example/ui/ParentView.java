package com.example.ui;

import com.example.controller.ParentViewController;
import com.example.dto.ChildSummaryDTO;

/**
 * Boundary/UI class for parent view.
 * This would typically be a GUI class, but simplified for demonstration.
 */
public class ParentView {
    private ParentViewController controller;

    public ParentView(ParentViewController controller) {
        this.controller = controller;
    }

    /**
     * Simulates user clicking "View Child Summary"
     */
    public void onViewChildSummaryClicked(int parentId, int childId) {
        try {
            // 1. Call controller to get summary
            ChildSummaryDTO summary = controller.displayChildSummary(parentId, childId);
            
            // 2. Display information (simulating UI rendering)
            displayInformation(summary);
        } catch (Exception e) {
            System.err.println("Error displaying child summary: " + e.getMessage());
        }
    }

    private void displayInformation(ChildSummaryDTO summary) {
        System.out.println("=== Child Information Summary ===");
        System.out.println("Child Name: " + summary.getChildName());
        System.out.println("Child ID: " + summary.getChildId());
        System.out.println("Summary Date: " + summary.getSummaryDate());
        
        System.out.println("\n1. Displaying summary table...");
        
        System.out.println("\n2. Display date: " + summary.getSummaryDate());
        
        System.out.println("\n3. Absences:");
        if (summary.getAbsences() != null) {
            summary.getAbsences().forEach(absence -> 
                System.out.println("  - Date: " + absence.getDate() + ", Type: " + absence.getType() + ", Justified: " + absence.isJustified()));
        } else {
            System.out.println("  None");
        }
        
        System.out.println("\n4. Disciplinary notes:");
        if (summary.getDisciplinaryNotes() != null) {
            summary.getDisciplinaryNotes().forEach(note -> 
                System.out.println("  - Date: " + note.getDate() + ", Teacher: " + note.getTeacher() + ", Description: " + note.getDescription()));
        } else {
            System.out.println("  None");
        }
        
        System.out.println("\n5. Delays:");
        if (summary.getDelays() != null) {
            summary.getDelays().forEach(delay -> 
                System.out.println("  - Date: " + delay.getDate() + ", Duration: " + delay.getDurationMinutes() + " minutes, Justified: " + delay.isJustified()));
        } else {
            System.out.println("  None");
        }
        
        System.out.println("\n6. Justifications:");
        if (summary.getJustifications() != null) {
            summary.getJustifications().forEach(justification -> 
                System.out.println("  - Record ID: " + justification.getRecordId() + ", Type: " + justification.getRecordType() + 
                    ", Reason: " + justification.getReason() + ", Status: " + justification.getStatus()));
        } else {
            System.out.println("  None");
        }
        
        System.out.println("\nChild information displayed.");
    }
}