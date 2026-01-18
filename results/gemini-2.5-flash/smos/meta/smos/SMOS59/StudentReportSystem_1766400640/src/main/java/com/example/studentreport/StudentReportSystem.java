```java
package com.example.studentreport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a single course with its grade.
 */
class CourseGrade {
    private String courseName;
    private String grade; // e.g., "A", "B+", "C"

    /**
     * Constructs a new CourseGrade.
     *
     * @param courseName The name of the course.
     * @param grade      The grade received in the course.
     */
    public CourseGrade(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("  - %s: %s", courseName, grade);
    }
}

/**
 * Represents a student's report card for a specific period (e.g., semester/year).
 */
class ReportCard {
    private String reportId;
    private String semester;
    private int year;
    private List<CourseGrade> courseGrades;

    /**
     * Constructs a new ReportCard.
     *
     * @param reportId     A unique identifier for the report card.
     * @param semester     The semester (e.g., "Fall", "Spring").
     * @param year         The academic year.
     * @param courseGrades A list of course grades included in this report card.
     */
    public ReportCard(String reportId, String semester, int year, List<CourseGrade> courseGrades) {
        this.reportId = reportId;
        this.semester = semester;
        this.year = year;
        this.courseGrades = new ArrayList<>(courseGrades); // Defensive copy
    }

    public String getReportId() {
        return reportId;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public List<CourseGrade> getCourseGrades() {
        return new ArrayList<>(courseGrades); // Return a defensive copy
    }

    /**
     * Provides a detailed string representation of the report card.
     *
     * @return A formatted string with report card details.
     */
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("--- Report Card ID: %s ---\n", reportId));
        sb.append(String.format("Semester: %s %d\n", semester, year));
        sb.append("Courses:\n");
        if (courseGrades.isEmpty()) {
            sb.append("  No courses listed.\n");
        } else {
            for (CourseGrade cg : courseGrades) {
                sb.append(cg.toString()).append("\n");
            }
        }
        sb.append("---------------------------\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Report ID: %s (%s %d)", reportId, semester, year);
    }
}

/**
 * Represents a student in the system.
 */
class Student {
    private String studentId;
    private String name;
    private List<ReportCard> reportCards;

    /**
     * Constructs a new Student.
     *
     * @param studentId   The unique identifier for the student.
     * @param name        The name of the student.
     * @param reportCards A list of report cards associated with this student.
     */
    public Student(String studentId, String name, List<ReportCard> reportCards) {
        this.studentId = studentId;
        this.name = name;
        this.reportCards = new ArrayList<>(reportCards); // Defensive copy
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<ReportCard> getReportCards() {
        return new ArrayList<>(reportCards); // Return a defensive copy
    }

    @Override
    public String toString() {
        return String.format("Student ID: %s, Name: %s", studentId, name);
    }
}

/**
 * Simulates the SMOS (Student Management and Online Serv) server connection.
 */
class SMOSServer {
    private boolean connected;

    public SMOSServer() {
        this.connected = false;
    }

    /**
     * Simulates establishing a connection to the SMOS server.
     */
    public void connect() {
        System.out.println("Attempting to connect to SMOS server...");
        // In a real system, this would involve network calls, authentication, etc.
        this.connected = true;
        System.out.println("SMOS server connected successfully.");
    }

    /**
     * Simulates interrupting the connection to the SMOS server.
     */
    public void disconnect() {
        if (connected) {
            System.out.println("Connection to the SMOS server interrupted.");
            this.connected = false;
        } else {
            System.out.println("SMOS server was not connected.");
        }
    }

    public boolean isConnected() {
        return connected;
    }
}

/**
 * Main class for the Student Report System, handling user interaction and report display.
 * This class orchestrates the display of a student's report card based on user input.
 */
public class StudentReportSystem {

    private Student currentStudent; // Represents the logged-in student
    private List<Student> allStudents; // A repository of all students in the system
    private SMOSServer smosServer; // Simulated SMOS server connection

    /**
     * Constructs a new StudentReportSystem.
     * Initializes dummy student data and the SMOS server.
     */
    public StudentReportSystem() {
        this.allStudents = new ArrayList<>();
        this.smosServer = new SMOSServer();
        initializeDummyData();
    }

    /**
     * Initializes some dummy student and report card data for demonstration purposes.
     */
    private void initializeDummyData() {
        // Create CourseGrades
        CourseGrade math101_A = new CourseGrade("Math 101", "A");
        CourseGrade physics101_B = new CourseGrade("Physics 101", "B+");
        CourseGrade compsci101_A = new CourseGrade("Computer Science 101", "A-");
        CourseGrade english101_C = new CourseGrade("English 101", "C");
        CourseGrade history101_B = new CourseGrade("History 101", "B");
        CourseGrade chemistry101_A = new CourseGrade("Chemistry 101", "A");

        // Create ReportCards
        ReportCard report1 = new ReportCard("RC001", "Fall", 2022,
                Arrays.asList(math101_A, physics101_B, compsci101_A));
        ReportCard report2 = new ReportCard("RC002", "Spring", 2023,
                Arrays.asList(english101_C, history101_B, math101_A));
        ReportCard report3 = new ReportCard("RC003", "Fall", 2023,
                Arrays.asList(chemistry101_A, compsci101_A, physics101_B));

        // Create Students
        Student student1 = new Student("S001", "Alice Smith", Arrays.asList(report1, report2));
        Student student2 = new Student("S002", "Bob Johnson", Arrays.asList(report3));
        Student student3 = new Student("S003", "Charlie Brown", new ArrayList<>()); // Student with no reports

        allStudents.add(student1);
        allStudents.add(student2);
        allStudents.add(student3);
    }

    /**
     * Simulates the login process for a student.
     * Precondition: The user has been logged in to the system as a student.
     *
     * @param studentId The ID of the student attempting to log in.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String studentId) {
        Optional<Student> foundStudent = allStudents.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();

        if (foundStudent.isPresent()) {
            this.currentStudent = foundStudent.get();
            System.out.println("Login successful for student: " + currentStudent.getName());
            return true;
        } else {
            System.out.println("Login failed: Student ID '" + studentId + "' not found.");
            this.currentStudent = null;
            return false;
        }
    }

    /**
     * Displays the list of available report cards for the currently logged-in student.
     * This corresponds to "Event 1: The system displays the student's reports logged in in the archive".
     * Precondition: A student must be logged in.
     *
     * @return A list of report cards available to the student, or an empty list if no student is logged in or no reports exist.
     */
    public List<ReportCard> displayAvailableReports() {
        if (currentStudent == null) {
            System.out.println("Error: No student is currently logged in.");
            return new ArrayList<>();
        }

        List<ReportCard> reports = currentStudent.getReportCards();
        if (reports.isEmpty()) {
            System.out.println(currentStudent.getName() + " has no report cards in the archive.");
            return new ArrayList<>();
        }

        System.out.println("\n--- " + currentStudent.getName() + "'s Online Report Archive ---");
        for (int i = 0; i < reports.size(); i++) {
            System.out.println((i + 1) + ". " + reports.get(i).toString());
        }
        System.out.println("------------------------------------------");
        return reports;
    }

    /**
     * Allows the user to select a report card from a given list.
     * This corresponds to "Event 2: Select the report card".
     *
     * @param availableReports The list of report cards from which to select.
     * @param scanner          The scanner object for user input.
     * @return The selected ReportCard, or null if selection is invalid or cancelled.
     */
    public ReportCard selectReportCard(List<ReportCard> availableReports, Scanner scanner) {
        if (availableReports.isEmpty()) {
            System.out.println("No reports available to select.");
            return null;
        }

        System.out.print("Enter the number of the report card you wish to view (or 0 to cancel): ");
        int selection;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }

        if (selection == 0) {
            System.out.println("Report card selection cancelled.");
            return null;
        }

        if (selection > 0 && selection <= availableReports.size()) {
            return availableReports.get(selection - 1);
        } else {
            System.out.println("Invalid selection. Please choose a number from the list.");
            return null;
        }
    }

    /**
     * Displays the detailed information of a selected report card.
     * This corresponds to "Event 3: Displays the details of the selected report card".
     * Postcondition: The system displays a student's report.
     *
     * @param report The ReportCard object to display.
     */
    public void displayReportDetails(ReportCard report) {
        if (report == null) {
            System.out.println("No report card selected or available to display details.");
            return;
        }
        System.out.println("\n--- Details for Selected Report Card ---");
        System.out.println(report.getDetails());
        System.out.println("----------------------------------------");
    }

    /**
     * Simulates the "Online report" click and the entire report display process.
     * This method encapsulates the use case flow.
     * Preconditions:
     * - The user has been logged in to the system as a student.
     * - The user clicks on "Online report".
     */
    public void handleOnlineReportRequest(Scanner scanner) {
        // Precondition check: User logged in
        if (currentStudent == null) {
            System.out.println("Operation failed: Please log in as a student first.");
            return;
        }

        // Simulate connection to SMOS server (if not already connected)
        if (!smosServer.isConnected()) {
            smosServer.connect();
        }

        // Event 1: The system displays the student's reports logged in in the archive
        List<ReportCard> availableReports = displayAvailableReports();

        // Check if there are reports to select
        if (availableReports.isEmpty()) {
            System.out.println("No reports to display for " + currentStudent.getName() + ".");
            // Postcondition: Connection to the SMOS server interrupted
            smosServer.disconnect();
            return;
        }

        // Event 2: User selects the report card
        ReportCard selectedReport = selectReportCard(availableReports, scanner);

        // Event 3: Displays the details of the selected report card
        displayReportDetails(selectedReport);

        // Postcondition: Connection to the SMOS server interrupted
        smosServer.disconnect();
    }

    /**
     * The main method to run the Student Report