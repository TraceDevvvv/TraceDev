package com.reportcard.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Report Card System.
 * This class simulates the interaction flow for a 'Direction' actor to view a student's report card,
 * as described in the use case.
 */
public class ReportCardSystem {

    private List<AcademicYear> academicYears;
    private Scanner scanner;

    /**
     * Constructor for ReportCardSystem.
     * Initializes the system with sample data and a scanner for user input.
     */
    public ReportCardSystem() {
        this.academicYears = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeData();
    }

    /**
     * Initializes sample data for academic years, classes, students, and report cards.
     * This simulates a pre-populated database for demonstration purposes.
     */
    private void initializeData() {
        // Academic Year 2023-2024
        AcademicYear year2023_2024 = new AcademicYear("2023-2024");

        SchoolClass class10A = new SchoolClass("10A", "Tenth Grade - Section A");
        SchoolClass class10B = new SchoolClass("10B", "Tenth Grade - Section B");

        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");
        Student student4 = new Student("S004", "Diana Prince");

        // Report Cards for Alice Smith
        ReportCard rcAliceQ1_23_24 = new ReportCard("2023-2024", "Q1");
        rcAliceQ1_23_24.addOrUpdateGrade("Math", "A");
        rcAliceQ1_23_24.addOrUpdateGrade("Physics", "B+");
        rcAliceQ1_23_24.addOrUpdateGrade("Chemistry", "A-");
        student1.addReportCard(rcAliceQ1_23_24);

        ReportCard rcAliceQ2_23_24 = new ReportCard("2023-2024", "Q2");
        rcAliceQ2_23_24.addOrUpdateGrade("Math", "A-");
        rcAliceQ2_23_24.addOrUpdateGrade("Physics", "A");
        rcAliceQ2_23_24.addOrUpdateGrade("Chemistry", "B+");
        student1.addReportCard(rcAliceQ2_23_24);

        // Report Cards for Bob Johnson
        ReportCard rcBobQ1_23_24 = new ReportCard("2023-2024", "Q1");
        rcBobQ1_23_24.addOrUpdateGrade("Math", "B");
        rcBobQ1_23_24.addOrUpdateGrade("Physics", "C+");
        rcBobQ1_23_24.addOrUpdateGrade("English", "A");
        student2.addReportCard(rcBobQ1_23_24);

        // Report Cards for Charlie Brown
        ReportCard rcCharlieQ1_23_24 = new ReportCard("2023-2024", "Q1");
        rcCharlieQ1_23_24.addOrUpdateGrade("History", "B+");
        rcCharlieQ1_23_24.addOrUpdateGrade("Geography", "A-");
        student3.addReportCard(rcCharlieQ1_23_24);

        class10A.addStudent(student1);
        class10A.addStudent(student2);
        class10B.addStudent(student3);
        class10B.addStudent(student4); // Diana has no report cards yet

        year2023_2024.addClass(class10A);
        year2023_2024.addClass(class10B);
        academicYears.add(year2023_2024);

        // Academic Year 2022-2023
        AcademicYear year2022_2023 = new AcademicYear("2022-2023");
        SchoolClass class9A = new SchoolClass("9A", "Ninth Grade - Section A");
        Student student5 = new Student("S005", "Eve Adams");
        ReportCard rcEveQ1_22_23 = new ReportCard("2022-2023", "Q1");
        rcEveQ1_22_23.addOrUpdateGrade("Algebra", "A");
        rcEveQ1_22_23.addOrUpdateGrade("Biology", "B");
        student5.addReportCard(rcEveQ1_22_23);
        class9A.addStudent(student5);
        year2022_2023.addClass(class9A);
        academicYears.add(year2022_2023);
    }

    /**
     * Simulates the user interaction flow to view a report card.
     */
    public void start() {
        System.out.println("--- Report Card System ---");
        System.out.println("Precondition: User logged in as 'Direction' and clicked 'Online report cards' button.");

        // Event 1 & 2: Select academic year and display classes
        AcademicYear selectedAcademicYear = selectAcademicYear();
        if (selectedAcademicYear == null) {
            System.out.println("No academic year selected. Exiting.");
            closeConnection();
            return;
        }

        // Event 3 & 4: Choose pupil class and display students
        SchoolClass selectedClass = selectClass(selectedAcademicYear);
        if (selectedClass == null) {
            System.out.println("No class selected. Exiting.");
            closeConnection();
            return;
        }

        // Event 5 & 6: Select student, quadrimestre, and display report
        Student selectedStudent = selectStudent(selectedClass);
        if (selectedStudent == null) {
            System.out.println("No student selected. Exiting.");
            closeConnection();
            return;
        }

        String selectedQuadrimestre = selectQuadrimestre();
        if (selectedQuadrimestre == null) {
            System.out.println("No quadrimestre selected. Exiting.");
            closeConnection();
            return;
        }

        displayReportCard(selectedStudent, selectedAcademicYear.getYear(), selectedQuadrimestre);

        // Postconditions
        System.out.println("\nPostcondition: The system displayed a student's report.");
        closeConnection();
    }

    /**
     * Prompts the user to select an academic year from the available list.
     *
     * @return The selected AcademicYear object, or null if the user cancels or input is invalid.
     */
    private AcademicYear selectAcademicYear() {
        System.out.println("\n1. Select the academic year:");
        if (academicYears.isEmpty()) {
            System.out.println("No academic years available in the system.");
            return null;
        }

        for (int i = 0; i < academicYears.size(); i++) {
            System.out.println((i + 1) + ". " + academicYears.get(i).getYear());
        }
        System.out.print("Enter the number of the academic year: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice > 0 && choice <= academicYears.size()) {
                AcademicYear year = academicYears.get(choice - 1);
                System.out.println("System: Displays the list of classes in " + year.getYear() + " with a 'report cards' button next to each class.");
                return year;
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectAcademicYear(); // Recursive call for retry
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return selectAcademicYear(); // Recursive call for retry
        }
    }

    /**
     * Prompts the user to select a class from the given academic year.
     *
     * @param academicYear The AcademicYear object from which to select a class.
     * @return The selected SchoolClass object, or null if the user cancels or input is invalid.
     */
    private SchoolClass selectClass(AcademicYear academicYear) {
        System.out.println("\n3. Choose the pupil class whose report card wants to display:");
        List<SchoolClass> classes = academicYear.getClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes available for " + academicYear.getYear() + ".");
            return null;
        }

        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName() + " (ID: " + classes.get(i).getClassId() + ") [Report Cards]");
        }
        System.out.print("Enter the number of the class: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice > 0 && choice <= classes.size()) {
                SchoolClass schoolClass = classes.get(choice - 1);
                System.out.println("System: Displays the list of class students chosen by the user for " + schoolClass.getName() + ".");
                return schoolClass;
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectClass(academicYear); // Recursive call for retry
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return selectClass(academicYear); // Recursive call for retry
        }
    }

    /**
     * Prompts the user to select a student from the given school class.
     *
     * @param schoolClass The SchoolClass object from which to select a student.
     * @return The selected Student object, or null if the user cancels or input is invalid.
     */
    private Student selectStudent(SchoolClass schoolClass) {
        System.out.println("\n5. Select the student to which the report card is displayed:");
        List<Student> students = schoolClass.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students available in " + schoolClass.getName() + ".");
            return null;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName() + " (ID: " + students.get(i).getStudentId() + ")");
        }
        System.out.print("Enter the number of the student: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice > 0 && choice <= students.size()) {
                return students.get(choice - 1);
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectStudent(schoolClass); // Recursive call for retry
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return selectStudent(schoolClass); // Recursive call for retry
        }
    }

    /**
     * Prompts the user to select a quadrimestre (semester/term).
     *
     * @return The selected quadrimestre string, or null if the user cancels or input is invalid.
     */
    private String selectQuadrimestre() {
        System.out.println("\n   Select the quadrimestre of interest:");
        List<String> quadrimestres = Arrays.asList("Q1", "Q2", "Q3", "Q4", "Semester 1", "Semester 2"); // Example quadrimestres
        for (int i = 0; i < quadrimestres.size(); i++) {
            System.out.println((i + 1) + ". " + quadrimestres.get(i));
        }
        System.out.print("Enter the number of the quadrimestre: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice > 0 && choice <= quadrimestres.size()) {
                return quadrimestres.get(choice - 1);
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectQuadrimestre(); // Recursive call for retry
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return selectQuadrimestre(); // Recursive call for retry
        }
    }

    /**
     * Displays the chosen student's report card for the selected academic year and quadrimestre.
     *
     * @param student The Student object whose report card is to be displayed.
     * @param academicYear The academic year for the report card.
     * @param quadrimestre The quadrimestre for the report card.
     */
    private void displayReportCard(Student student, String academicYear, String quadrimestre) {
        System.out.println("\nSystem: Displays the chosen student report referred to the selected semester.");
        System.out.println("--- Displaying Report Card ---");
        System.out.println("Student: " + student.getName() + " (ID: " + student.getStudentId() + ")");
        System.out.println("Academic Year: " + academicYear);
        System.out.println("Quadrimestre: " + quadrimestre);

        ReportCard reportCard = student.getReportCard(academicYear, quadrimestre);

        if (reportCard != null) {
            System.out.println(reportCard.toString());
        } else {
            System.out.println("No report card found for " + student.getName() +
                               " for " + academicYear + " " + quadrimestre + ".");
        }
        System.out.println("----------------------------");
    }

    /**
     * Closes the scanner and indicates the connection interruption.
     */
    private void closeConnection() {
        scanner.close();
        System.out.println("SMOS server connection interrupted.");
    }

    /**
     * Main method to run the Report Card System.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ReportCardSystem system = new ReportCardSystem();
        system.start();
    }
}