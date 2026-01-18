package com.example.app;

import java.util.List;

/**
 * Mock UI component for displaying a list of classes.
 * This class corresponds to the 'ClassListView' class in the UML Class Diagram (R5).
 */
public class ClassListView {

    /**
     * Displays a list of class details to the user.
     *
     * @param classList A list of ClassDetailsDTOs to display.
     */
    public void displayClassList(List<ClassDetailsDTO> classList) {
        System.out.println("\n--- Class List View ---");
        if (classList == null || classList.isEmpty()) {
            System.out.println("No classes to display.");
        } else {
            System.out.println("Available Classes:");
            for (ClassDetailsDTO dto : classList) {
                System.out.println("- " + dto.getName() + " (" + dto.getSchoolYear() + ")");
            }
        }
        System.out.println("-----------------------");
    }

    /**
     * Simulates getting the ID of a class selected from the list by the user.
     * For demonstration, this method is a placeholder as it's not directly used in the "View Class Details" sequence.
     *
     * @return The ID of the selected class.
     */
    public String getSelectedClassId() {
        System.out.println("View: getSelectedClassId() called (placeholder for list selection).");
        return "CLASS001"; // Placeholder
    }
}