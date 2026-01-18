package com.example.view;

import com.example.model.AcademicYear;
import com.example.model.SchoolClass; // Renamed
import com.example.model.Teacher;
import com.example.model.TeacherAssignment;
import com.example.model.Teaching;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MVC View for displaying teacher assignment information and user interface elements.
 * Uses System.out.println to simulate a GUI.
 */
public class TeacherAssignmentView {

    /**
     * Displays the main management form for teacher assignments.
     * @param teacher The teacher whose assignments are being managed.
     * @param academicYears A list of available academic years.
     * @param schoolClasses A list of classes for a selected academic year (can be null initially).
     * @param teachings A list of teachings for a selected class (can be null initially).
     * @param currentAssignments A list of the teacher's current assignments.
     */
    public void displayManagementForm(Teacher teacher, List<AcademicYear> academicYears,
                                      List<SchoolClass> schoolClasses, List<Teaching> teachings, // Renamed
                                      List<TeacherAssignment> currentAssignments) {
        System.out.println("\n--- Teacher Assignment Management for " + teacher.getName() + " (ID: " + teacher.getId() + ") ---");

        System.out.println("\nAvailable Academic Years:");
        if (academicYears != null && !academicYears.isEmpty()) {
            academicYears.forEach(ay -> System.out.println("  [" + ay.getId() + "] " + ay.getDescription()));
        } else {
            System.out.println("  No academic years available.");
        }

        System.out.println("\nCurrent Assignments:");
        if (currentAssignments != null && !currentAssignments.isEmpty()) {
            currentAssignments.forEach(ta -> System.out.println("  - Teaching ID: " + ta.getTeachingId() + " (Assigned: " + ta.getAssignedDate() + ")"));
        } else {
            System.out.println("  No current assignments.");
        }

        renderClasses(schoolClasses);
        renderTeachings(teachings, currentAssignments);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Please select an Academic Year, then a Class, then update teachings.");
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * Renders a list of academic years.
     * @param years The list of academic years to display.
     */
    public void renderAcademicYears(List<AcademicYear> years) {
        System.out.println("\n--- Academic Years ---");
        if (years != null && !years.isEmpty()) {
            years.forEach(ay -> System.out.println("  [" + ay.getId() + "] " + ay.getDescription()));
        } else {
            System.out.println("  No academic years to display.");
        }
        System.out.println("---------------------");
    }

    /**
     * Renders a list of classes for a selected academic year.
     * @param schoolClasses The list of school classes to display.
     */
    public void renderClasses(List<SchoolClass> schoolClasses) { // Renamed
        System.out.println("\n--- Classes for Selected Academic Year ---");
        if (schoolClasses != null && !schoolClasses.isEmpty()) {
            schoolClasses.forEach(c -> System.out.println("  [" + c.getId() + "] " + c.getName()));
        } else {
            System.out.println("  No classes found for the selected academic year.");
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Renders a list of teachings for a selected class, indicating which ones are currently assigned.
     * @param teachings The list of teachings to display.
     * @param currentAssignments The teacher's current assignments (used to check checkboxes).
     */
    public void renderTeachings(List<Teaching> teachings, List<TeacherAssignment> currentAssignments) {
        System.out.println("\n--- Teachings for Selected Class ---");
        if (teachings != null && !teachings.isEmpty()) {
            // Extract current teaching IDs for easy lookup
            Set<String> assignedTeachingIds = currentAssignments.stream()
                    .map(TeacherAssignment::getTeachingId)
                    .collect(Collectors.toSet());

            teachings.forEach(t -> {
                String checkbox = assignedTeachingIds.contains(t.getId()) ? "[X]" : "[ ]";
                System.out.println("  " + checkbox + " [" + t.getId() + "] " + t.getName());
            });
        } else {
            System.out.println("  No teachings found for the selected class.");
        }
        System.out.println("------------------------------------");
    }

    /**
     * Displays a success message to the user.
     * @param message The success message.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[ERROR] " + message);
    }
}