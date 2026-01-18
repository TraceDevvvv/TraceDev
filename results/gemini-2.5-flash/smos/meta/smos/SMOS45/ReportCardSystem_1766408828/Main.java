package com.reportcard.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Report Card System application.
 * This class serves as the entry point and orchestrates the user interaction
 * flow for administrators to view student report cards.
 */
public class Main {

    /**
     * The main method where the application execution begins.
     * It will simulate the administrator's interaction with the system
     * to view report cards based on the use case.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize System Data (Mock Data)
        Administrator admin = new Administrator("admin", "admin123");

        AcademicYear year2023_2024 = new AcademicYear("2023-2024");
        AcademicYear year2024_2025 = new AcademicYear("2024-2025");
        admin.addAcademicYear(year2023_2024);
        admin.addAcademicYear(year2024_2025);

        PupilClass class10A = new PupilClass("10A");
        PupilClass class10B = new PupilClass("10B");
        admin.addClass(class10A);
        admin.addClass(class10B);

        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");

        class10A.addStudent(student1);
        class10A.addStudent(student2);
        class10B.addStudent(student3);

        // Add report cards for students
        ReportCard rc1_23_24 = new ReportCard(student1.getStudentId(), year2023_2024);
        rc1_23_24.addGrade("September", 85);
        rc1_23_24.addGrade("October", 90);
        rc1_23_24.addGrade("November", 88);
        rc1_23_24.addGrade("December", 92);
        rc1_23_24.addGrade("January", 87);
        rc1_23_24.addGrade("February", 91);
        rc1_23_24.addGrade("March", 89);
        rc1_23_24.addGrade("April", 93);
        student1.addReportCard(year2023_2024, rc1_23_24);

        ReportCard rc2_23_24 = new ReportCard(student2.getStudentId(), year2023_2024);
        rc2_23_24.addGrade("September", 78);
        rc2_23_24.addGrade("October", 82);
        rc2_23_24.addGrade("November", 80);
        rc2_23_24.addGrade("December", 85);
        rc2_23_24.addGrade("January", 79);
        rc2_23_24.addGrade("February", 83);
        rc2_23_24.addGrade("March", 81);
        rc2_23_24.addGrade("April", 86);
        student2.addReportCard(year2023_2024, rc2_23_24);

        ReportCard rc3_23_24 = new ReportCard(student3.getStudentId(), year2023_2024);
        rc3_23_24.addGrade("September", 90);
        rc3_23_24.addGrade("October", 92);
        rc3_23_24.addGrade("November", 91);
        rc3_23_24.addGrade("December", 94);
        rc3_23_24.addGrade("January", 88);
        rc3_23_24.addGrade("February", 90);
        rc3_23_24.addGrade("March", 89);
        rc3_23_24.addGrade("April", 93);
        student3.addReportCard(year2023_2024, rc3_23_24);

        System.out.println("Welcome to the Report Card System!");
        System.out.println("This application allows administrators to view student report cards.");

        // Simulate Preconditions
        System.out.println("\n--- Simulating Administrator Login ---");
        if (!admin.login("admin", "admin123")) {
            System.out.println("Application exiting due to login failure.");
            scanner.close();
            return;
        }

        System.out.println("\n--- Simulating 'Online reports' button click ---");
        if (!admin.clickOnlineReports()) {
            System.out.println("Application exiting due to inability to access reports.");
            scanner.close();
            return;
        }

        // Event Sequence
        // 1. User selects academic year
        System.out.println("\n--- Step 1 & 2: Select Academic Year ---");
        admin.displayAcademicYears();
        System.out.print("Enter academic year to view (e.g., 2023-2024): ");
        String selectedYear = scanner.nextLine();
        if (!admin.selectAcademicYear(selectedYear)) {
            System.out.println("Application exiting.");
            scanner.close();
            return;
        }

        // 3. User chooses pupil class
        System.out.println("\n--- Step 3 & 4: Choose Pupil Class ---");
        admin.displayClasses();
        System.out.print("Enter class identifier to view (e.g., 10A): ");
        String selectedClass = scanner.nextLine();
        if (!admin.choosePupilClass(selectedClass)) {
            System.out.println("Application exiting.");
            scanner.close();
            return;
        }

        // 5. User selects student and four months
        System.out.println("\n--- Step 5: Select Student and Months ---");
        admin.displayStudentsInClass();
        System.out.print("Enter student ID to view report (e.g., S001): ");
        String selectedStudentId = scanner.nextLine();

        List<String> monthsToView = new ArrayList<>();
        System.out.println("Enter four months to view report (e.g., September, October, November, December):");
        for (int i = 0; i < 4; i++) {
            System.out.print("Month " + (i + 1) + ": ");
            monthsToView.add(scanner.nextLine());
        }

        if (!admin.selectStudentAndMonths(selectedStudentId, monthsToView)) {
            System.out.println("Application exiting.");
            scanner.close();
            return;
        }

        // 6. System displays chosen student report
        System.out.println("\n--- Step 6: Display Student Report ---");
        admin.displayStudentReport();

        // Postconditions
        System.out.println("\n--- Postconditions: System displays student's report ---");
        System.out.println("Report displayed successfully.");

        System.out.println("\n--- Postconditions: User stops connection to SMOS server ---");
        admin.stopConnectionToSMOSServer();

        scanner.close();
        System.out.println("Application finished. Goodbye!");
    }
}