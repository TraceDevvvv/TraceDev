package com.smos.app;

import com.smos.model.*;
import com.smos.service.TeacherService;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main application class to simulate the teacher's interaction with the system
 * to display student report cards.
 * This class orchestrates the flow of events as described in the use case.
 */
public class DisplayOfAPageTeacherApp {

    private final TeacherService teacherService;
    private final Scanner scanner;
    private Teacher loggedInTeacher; // Represents the currently logged-in teacher

    /**
     * Constructs the application, initializing the TeacherService and Scanner.
     */
    public DisplayOfAPageTeacherApp() {
        this.teacherService = new TeacherService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the teacher login process.
     * In a real system, this would involve secure authentication.
     * For this simulation, it checks against mock data.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean loginTeacher() {
        System.out.println("--- Teacher Login ---");
        System.out.print("Enter Teacher ID: ");
        String teacherId = scanner.nextLine();
        System.out.print("Enter Teacher Name: ");
        String teacherName = scanner.nextLine();

        loggedInTeacher = teacherService.authenticateTeacher(teacherId, teacherName);

        if (loggedInTeacher != null) {
            System.out.println("Login successful! Welcome, " + loggedInTeacher.getName() + ".");
            return true;
        } else {
            System.out.println("Login failed. Invalid Teacher ID or Name.");
            return false;
        }
    }

    /**
     * Displays a list of items and prompts the user to select one.
     *
     * @param <T> The type of items in the list.
     * @param items The list of items to display.
     * @param prompt The message to display to the user.
     * @return The selected item, or null if selection is invalid or list is empty.
     */
    private <T> T selectFromList(List<T> items, String prompt) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items available to select.");
            return null;
        }

        System.out.println("\n" + prompt);
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }

        int choice = -1;
        while (choice < 1 || choice > items.size()) {
            System.out.print("Enter your choice (1-" + items.size() + "): ");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > items.size()) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the rest of the line after reading int
        return items.get(choice - 1);
    }

    /**
     * Runs the main application flow for displaying report cards.
     */
    public void run() {
        // Precondition: User logged in as a teacher
        if (!loginTeacher()) {
            System.out.println("Application terminated due to login failure.");
            scanner.close();
            return;
        }

        System.out.println("\n--- On-line Report Cards ---");

        // Event 1: System views list of academic years for the teacher
        List<AcademicYear> academicYears = teacherService.getAcademicYearsForTeacher(loggedInTeacher);
        if (academicYears.isEmpty()) {
            System.out.println("No academic years found for " + loggedInTeacher.getName() + ".");
            System.out.println("Exiting application.");
            scanner.close();
            return;
        }

        // Event 2: User selects academic year
        AcademicYear selectedAcademicYear = selectFromList(academicYears, "Select an academic year:");
        if (selectedAcademicYear == null) {
            System.out.println("No academic year selected. Exiting application.");
            scanner.close();
            return;
        }
        System.out.println("You selected: " + selectedAcademicYear.getYear());

        // Event 3: System views classes associated with the selected school year
        List<Classroom> classes = teacherService.getClassesForTeacherAndAcademicYear(loggedInTeacher, selectedAcademicYear);
        if (classes.isEmpty()) {
            System.out.println("No classes found for " + loggedInTeacher.getName() + " in " + selectedAcademicYear.getYear() + ".");
            System.out.println("Exiting application.");
            scanner.close();
            return;
        }

        // Event 4: User selects one of the displayed classes
        Classroom selectedClassroom = selectFromList(classes, "Select a class:");
        if (selectedClassroom == null) {
            System.out.println("No class selected. Exiting application.");
            scanner.close();
            return;
        }
        System.out.println("You selected: " + selectedClassroom.getName());

        // Event 5: System displays the list of class students
        List<Student> students = teacherService.getStudentsInClassroom(selectedClassroom);
        if (students.isEmpty()) {
            System.out.println("No students found in class " + selectedClassroom.getName() + ".");
            System.out.println("Exiting application.");
            scanner.close();
            return;
        }

        // Event 6: User selects pupil and quadrimestre
        Student selectedStudent = selectFromList(students, "Select a student:");
        if (selectedStudent == null) {
            System.out.println("No student selected. Exiting application.");
            scanner.close();
            return;
        }
        System.out.println("You selected: " + selectedStudent.getName());

        int quadrimestre = -1;
        while (quadrimestre != 1 && quadrimestre != 2) {
            System.out.print("Enter the reference quadrimestre (1 or 2): ");
            try {
                quadrimestre = scanner.nextInt();
                if (quadrimestre != 1 && quadrimestre != 2) {
                    System.out.println("Invalid quadrimestre. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the rest of the line

        // Event 7: System displays the chosen student report
        System.out.println("\n--- Displaying Report Card ---");
        ReportCard reportCard = teacherService.getReportCardForStudent(selectedStudent, selectedAcademicYear, quadrimestre);

        if (reportCard != null) {
            System.out.println("Report Card for " + reportCard.getStudent().getName() +
                               " (" + reportCard.getAcademicYear().getYear() +
                               ", Quadrimestre " + reportCard.getQuadrimestre() + ")");
            System.out.println("------------------------------------");
            if (reportCard.getGrades().isEmpty()) {
                System.out.println("No grades recorded for this period.");
            } else {
                for (Grade grade : reportCard.getGrades()) {
                    System.out.printf("  %-15s: %.2f%n", grade.getSubject(), grade.getValue());
                }
            }
            System.out.println("------------------------------------");
        } else {
            System.out.println("No report card found for " + selectedStudent.getName() +
                               " in " + selectedAcademicYear.getYear() +
                               ", Quadrimestre " + quadrimestre + ".");
        }

        // Postcondition: The user stops the connection to the SMOS server interrupted
        System.out.println("\nThank you for using the SMOS system. Connection terminated.");
        scanner.close();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DisplayOfAPageTeacherApp app = new DisplayOfAPageTeacherApp();
        app.run();
    }
}