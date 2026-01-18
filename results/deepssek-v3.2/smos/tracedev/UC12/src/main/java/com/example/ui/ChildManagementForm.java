package com.example.ui;

import com.example.entities.Parent;
import com.example.entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the UI form for managing child assignments.
 * Corresponds to ChildManagementForm in the class diagram.
 */
public class ChildManagementForm {
    private Parent parent;
    private List<Student> availableStudents;
    private List<Integer> selectedForAssignment;
    private List<Integer> selectedForRemoval;

    public ChildManagementForm(Parent parent, List<Student> availableStudents) {
        this.parent = parent;
        this.availableStudents = new ArrayList<>(availableStudents);
        this.selectedForAssignment = new ArrayList<>();
        this.selectedForRemoval = new ArrayList<>();
    }

    /**
     * Renders the form as a string (simulated UI).
     * Corresponds to render() in class diagram.
     */
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Child Management Form ===\n");
        sb.append("Parent: ").append(parent.getName()).append(" (ID: ").append(parent.getId()).append(")\n");
        sb.append("Available Students:\n");
        for (Student s : availableStudents) {
            sb.append("  [ID: ").append(s.getId()).append("] ").append(s.getName()).append(" - ").append(s.getGrade()).append("\n");
        }
        sb.append("Selected for Assignment IDs: ").append(selectedForAssignment).append("\n");
        sb.append("Selected for Removal IDs: ").append(selectedForRemoval).append("\n");
        return sb.toString();
    }

    public List<Integer> getSelectedAssignments() {
        return new ArrayList<>(selectedForAssignment);
    }

    public List<Integer> getSelectedRemovals() {
        return new ArrayList<>(selectedForRemoval);
    }

    /**
     * Updates the list of students selected for assignment.
     * Called when administrator selects students in the UI.
     */
    public void updateSelectedForAssignment(List<Integer> studentIds) {
        this.selectedForAssignment = new ArrayList<>(studentIds);
        System.out.println("ChildManagementForm: Updated selected for assignment: " + studentIds);
    }

    /**
     * Updates the list of students selected for removal.
     * Called when administrator selects students in the UI.
     */
    public void updateSelectedForRemoval(List<Integer> studentIds) {
        this.selectedForRemoval = new ArrayList<>(studentIds);
        System.out.println("ChildManagementForm: Updated selected for removal: " + studentIds);
    }
}