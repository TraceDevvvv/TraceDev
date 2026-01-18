package com.example.reportcardeditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to simulate the "EditOfOnePage" use case for an Administrator.
 * This class demonstrates the interaction flow:
 * 1. Administrator logs in (simulated).
 * 2. Administrator views a list of students (simulated).
 * 3. Administrator selects a student to edit (simulated by choosing an ID).
 * 4. System displays the student's current report card for modification.
 * 5. Administrator enters new data (simulated via console input).
 * 6. System updates the report card and confirms the change.
 * 7. System displays the updated list of students.
 * 8. Administrator interrupts the connection (simulated).
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the ReportCardManager
        ReportCardManager manager = new ReportCardManager();
        Scanner scanner = new Scanner(System.in);

        // --- Precondition: Administrator logged in and initial data setup ---
        System.out.println("--- System Initialization ---");
        System.out.println("Administrator logged in successfully.");

        // Add some initial student data
        Student student1 = new Student("S001", "Alice Smith",
                Arrays.asList(new Subject("Math", 85.5), new Subject("Physics", 90.0)));
        Student student2 = new Student("S002", "Bob Johnson",
                Arrays.asList(new Subject("Chemistry", 78.0), new Subject("Biology", 88.5)));
        Student student3 = new Student("S003", "Charlie Brown",
                Arrays.asList(new Subject("History", 92.0), new Subject("Geography", 81.0)));

        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);

        System.out.println("\n--- Current List of Students (Simulating 'DisplayedUnapagella') ---");
        displayAllStudents(manager);

        // --- Event Sequence: User clicks on the edit button ---
        System.out.println("\n--- Edit Report Card Use Case ---");
        System.out.print("Enter the Student ID to edit (e.g., S001): ");
        String studentIdToEdit = scanner.nextLine();

        try {
            // 1. System displays the form with the fields to be modified
            // (including the votes of the different subjects)
            ReportCard currentReportCard = manager.displayStudentReportCardForEdit(studentIdToEdit);
            System.out.println("\n--- Displaying Report Card for Editing ---");
            System.out.println(currentReportCard);

            // 2. User enters the new data and clicks the confirmation key
            System.out.println("\n--- Enter New Data ---");
            System.out.print("Enter new student name (current: " + currentReportCard.getStudent().getName() + "): ");
            String newStudentName = scanner.nextLine();
            if (newStudentName.trim().isEmpty()) {
                newStudentName = currentReportCard.getStudent().getName(); // Keep old name if empty input
            }

            List<Subject> updatedSubjects = new ArrayList<>();
            System.out.println("Enter new grades for subjects (type 'done' to finish):");
            int subjectCounter = 1;
            while (true) {
                System.out.print("Subject " + subjectCounter + " Name (or 'done'): ");
                String subjectName = scanner.nextLine();
                if (subjectName.equalsIgnoreCase("done")) {
                    break;
                }
                System.out.print("Subject " + subjectCounter + " Grade (0.0-100.0): ");
                double grade;
                try {
                    grade = Double.parseDouble(scanner.nextLine());
                    updatedSubjects.add(new Subject(subjectName, grade));
                    subjectCounter++;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid grade format. Please enter a number.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }

            // If no subjects were entered, retain existing subjects
            if (updatedSubjects.isEmpty()) {
                System.out.println("No new subjects entered. Retaining existing subjects.");
                updatedSubjects = currentReportCard.getStudent().getSubjects();
            }

            // Simulate clicking the confirmation key by calling the edit method
            ReportCard modifiedReportCard = manager.editStudentReportCard(studentIdToEdit, newStudentName, updatedSubjects);

            // 3. The system displays a confirmation message and displays the form with the list of students
            System.out.println("\n--- Confirmation ---");
            System.out.println("SUCCESS: Report card for " + modifiedReportCard.getStudent().getName() + " (ID: " + modifiedReportCard.getStudent().getStudentId() + ") has been updated.");
            System.out.println("\n--- Updated Report Card ---");
            System.out.println(modifiedReportCard);

            System.out.println("\n--- Displaying Updated List of Students ---");
            displayAllStudents(manager);

        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            // --- Postcondition: User interrupts the connection to the SMOS server interrupted ---
            System.out.println("\n--- Session End ---");
            manager.interruptConnection();
            scanner.close();
        }
    }

    /**
     * Helper method to display all students in the manager.
     * @param manager The ReportCardManager instance.
     */
    private static void displayAllStudents(ReportCardManager manager) {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getStudentId() + ", Name: " + s.getName());
        }
    }
}