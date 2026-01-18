package com.example.view;

import com.example.dtos.RegistryDetailsDTO;
import com.example.dtos.UserInputDTO;
import java.util.Scanner;

/**
 * View for displaying registry details and capturing user input.
 * Flow of Events 5: The system shows a form.
 */
public class RegistryDetailsView {
    private Scanner scanner = new Scanner(System.in);

    public void displayForm(RegistryDetailsDTO details) {
        System.out.println("Registry Details Form");
        System.out.println("Academic Year: " + details.getAcademicYear());
        System.out.println("Class Name: " + details.getClassName());
        System.out.println("Timeline:");
        details.getTimeline().forEach(daily -> {
            System.out.println("  Date: " + daily.getDate());
            daily.getStudents().forEach(student -> {
                System.out.println("    Student ID: " + student.getStudentId());
                System.out.println("    Status: " + student.getStatus());
                System.out.println("    Delayed: " + student.isDelayed());
                System.out.println("    Justification: " + student.getJustification());
                System.out.println("    Disciplinary Note: " + student.getDisciplinaryNote());
            });
        });
    }

    public UserInputDTO captureUserInput() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Justification: ");
        String justification = scanner.nextLine();
        System.out.print("Enter Disciplinary Note: ");
        String note = scanner.nextLine();
        return new UserInputDTO(studentId, justification, note);
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    // Additional method for simulating button click from sequence diagram
    public void clickRegisterButton(String academicYear, String className) {
        System.out.println("Register button clicked for " + className + " (" + academicYear + ")");
    }
}