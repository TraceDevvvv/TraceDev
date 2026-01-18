package com.example.absence.presentation;

import com.example.absence.application.RegisterAbsenceCommand;
import com.example.absence.application.RegisterAbsenceResult;
import com.example.absence.application.RegisterAbsenceUseCase;
import com.example.absence.domain.AbsenceEntry;
import com.example.absence.domain.Student;
import java.util.*;

/**
 * Presentation layer screen for entering student absences.
 * Simulates a UI component.
 */
public class AbsenceEntryScreen {
    private RegisterAbsenceUseCase useCase;
    private Map<Student, Boolean> selections = new HashMap<>();
    private String currentClassId;

    public AbsenceEntryScreen(RegisterAbsenceUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Displays the class with its students.
     * @param students the list of students in the class
     */
    public void displayClassWithStudents(List<Student> students) {
        System.out.println("Displaying students for class: ");
        for (Student student : students) {
            System.out.println("- " + student.getFullName() + " (" + student.getStudentId() + ")");
        }
    }

    /**
     * Sets default attendance (all present).
     */
    public void setDefaultAttendance() {
        for (Student student : selections.keySet()) {
            selections.put(student, false);
        }
        System.out.println("Default attendance set: all present.");
    }

    /**
     * Gets the current absence selections.
     * @return map of student to absence status (true = absent)
     */
    public Map<Student, Boolean> getSelectedAbsences() {
        return new HashMap<>(selections);
    }

    /**
     * Shows a success message.
     */
    public void showSuccessMessage() {
        System.out.println("Success: Absences saved successfully!");
    }

    /**
     * Shows an error message.
     */
    public void showErrorMessage() {
        System.out.println("Error: An error occurred.");
    }

    /**
     * Shows a specific error message.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Validates selections before saving.
     * @return true if selections are valid, false otherwise
     */
    public boolean validateSelections() {
        // Check if any selections have been made (i.e., any true values)
        for (Boolean absent : selections.values()) {
            if (absent != null && absent) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clears all selections.
     */
    public void clearSelections() {
        selections.clear();
        System.out.println("Selections cleared.");
    }

    /**
     * Resets the form to initial state.
     */
    public void resetForm() {
        selections.clear();
        currentClassId = null;
        System.out.println("Form reset.");
    }

    // Additional methods to simulate UI actions
    public void selectClass(String classId) {
        this.currentClassId = classId;
        List<Student> students = new ArrayList<>();
        selections.clear();
        for (Student student : students) {
            selections.put(student, false);
        }
        displayClassWithStudents(students);
    }

    public void selectAbsentStudents(List<String> studentIds) {
        for (Map.Entry<Student, Boolean> entry : selections.entrySet()) {
            if (studentIds.contains(entry.getKey().getStudentId())) {
                selections.put(entry.getKey(), true);
            }
        }
        System.out.println("Selected absent students: " + studentIds);
    }

    public void clickSave() {
        if (!validateSelections()) {
            showErrorMessage("No changes to save");
            return;
        }

        List<AbsenceEntry> entries = new ArrayList<>();
        for (Map.Entry<Student, Boolean> entry : selections.entrySet()) {
            entries.add(new AbsenceEntry(entry.getKey().getStudentId(), entry.getValue()));
        }

        RegisterAbsenceCommand command = new RegisterAbsenceCommand(
                currentClassId,
                new Date(),
                entries
        );

        RegisterAbsenceResult result = useCase.execute(command);
        if (result.isSuccess()) {
            showSuccessMessage();
            resetForm();
        } else {
            showErrorMessage(result.getMessage());
        }
    }

    public void clickCancel() {
        clearSelections();
        resetForm();
    }
}