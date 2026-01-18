```java
package com.reportcard.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents an Administrator in the Report Card System.
 * This class encapsulates the logic for an administrator to view student report cards,
 * managing the available academic years, classes, students, and their report cards.
 * It simulates the system's responses to administrator actions as per the use case.
 */
public class Administrator {
    private final String username;
    private final String password; // For simulation, a simple password check
    private boolean loggedIn;

    // Data storage for the system (simulated database)
    private final List<AcademicYear> availableAcademicYears;
    private final List<PupilClass> availableClasses;
    private final Map<String, Student> allStudents; // Key: studentId

    // State variables for the current viewing session
    private AcademicYear currentSelectedAcademicYear;
    private PupilClass currentSelectedClass;
    private Student currentSelectedStudent;
    private List<String> currentSelectedMonths;

    /**
     * Constructs a new Administrator with a username and password.
     * Initializes internal data structures for academic years, classes, and students.
     *
     * @param username The administrator's username. Must not be null or empty.
     * @param password The administrator's password. Must not be null or empty.
     * @throws IllegalArgumentException if username or password is null or empty.
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.username = username;
        this.password = password;
        this.loggedIn = false;

        this.availableAcademicYears = new ArrayList<>();
        this.availableClasses = new ArrayList<>();
        this.allStudents = new HashMap<>();

        this.currentSelectedAcademicYear = null;
        this.currentSelectedClass = null;
        this.currentSelectedStudent = null;
        this.currentSelectedMonths = new ArrayList<>();
    }

    /**
     * Simulates the administrator logging into the system.
     *
     * @param inputUsername The username entered by the user.
     * @param inputPassword The password entered by the user.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
            this.loggedIn = true;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        }
        System.out.println("Login failed for username '" + inputUsername + "'. Invalid credentials.");
        return false;
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Simulates the administrator clicking the "Online reports" button.
     * This method ensures the administrator is logged in before proceeding.
     *
     * @return true if the action is allowed (admin is logged in), false otherwise.
     */
    public boolean clickOnlineReports() {
        if (!loggedIn) {
            System.out.println("Error: Administrator must be logged in to access online reports.");
            return false;
        }
        System.out.println("Administrator clicked 'Online reports'.");
        // Reset current selections when starting a new report viewing session
        this.currentSelectedAcademicYear = null;
        this.currentSelectedClass = null;
        this.currentSelectedStudent = null;
        this.currentSelectedMonths = new ArrayList<>();
        return true;
    }

    /**
     * Adds an academic year to the system's available list.
     *
     * @param academicYear The AcademicYear object to add.
     * @throws IllegalArgumentException if academicYear is null or already exists.
     */
    public void addAcademicYear(AcademicYear academicYear) {
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null.");
        }
        if (availableAcademicYears.contains(academicYear)) {
            throw new IllegalArgumentException("Academic year " + academicYear.getYearIdentifier() + " already exists.");
        }
        availableAcademicYears.add(academicYear);
    }

    /**
     * Adds a pupil class to the system's available list.
     *
     * @param pupilClass The PupilClass object to add.
     * @throws IllegalArgumentException if pupilClass is null or already exists.
     */
    public void addClass(PupilClass pupilClass) {
        if (pupilClass == null) {
            throw new IllegalArgumentException("PupilClass cannot be null.");
        }
        if (availableClasses.contains(pupilClass)) {
            throw new IllegalArgumentException("Class " + pupilClass.getClassIdentifier() + " already exists.");
        }
        availableClasses.add(pupilClass);
        // Add students from this class to the global student map
        for (Student student : pupilClass.getStudents()) {
            allStudents.putIfAbsent(student.getStudentId(), student);
        }
    }

    /**
     * Adds a student to the system's global student list.
     * This method is primarily for initial data setup if students are not added via classes.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if student is null or a student with the same ID already exists.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (allStudents.containsKey(student.getStudentId())) {
            throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists.");
        }
        allStudents.put(student.getStudentId(), student);
    }

    /**
     * Displays the list of available academic years for the administrator to select.
     *
     * @return An unmodifiable list of available AcademicYear objects.
     * @throws IllegalStateException if the administrator is not logged in.
     */
    public List<AcademicYear> displayAcademicYears() {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to display academic years.");
        }
        System.out.println("\nAvailable Academic Years:");
        if (availableAcademicYears.isEmpty()) {
            System.out.println("No academic years available in the system.");
        } else {
            for (int i = 0; i < availableAcademicYears.size(); i++) {
                System.out.println((i + 1) + ". " + availableAcademicYears.get(i).getYearIdentifier());
            }
        }
        return Collections.unmodifiableList(availableAcademicYears);
    }

    /**
     * Allows the administrator to select an academic year.
     * This sets the `currentSelectedAcademicYear` state.
     *
     * @param yearIdentifier The identifier of the academic year to select (e.g., "2023-2024").
     * @return true if the academic year was successfully selected, false otherwise.
     * @throws IllegalStateException if the administrator is not logged in.
     */
    public boolean selectAcademicYear(String yearIdentifier) {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to select an academic year.");
        }
        Optional<AcademicYear> selectedYear = availableAcademicYears.stream()
                .filter(year -> year.getYearIdentifier().equals(yearIdentifier))
                .findFirst();

        if (selectedYear.isPresent()) {
            this.currentSelectedAcademicYear = selectedYear.get();
            System.out.println("Academic Year '" + yearIdentifier + "' selected.");
            return true;
        } else {
            System.out.println("Error: Academic Year '" + yearIdentifier + "' not found.");
            this.currentSelectedAcademicYear = null;
            return false;
        }
    }

    /**
     * Displays the list of classes in the system.
     * This corresponds to System Event 2: "Displays the list of classes in the system with a 'report cards' button next to each class".
     *
     * @return An unmodifiable list of available PupilClass objects.
     * @throws IllegalStateException if the administrator is not logged in or no academic year is selected.
     */
    public List<PupilClass> displayClasses() {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to display classes.");
        }
        if (currentSelectedAcademicYear == null) {
            throw new IllegalStateException("An academic year must be selected first.");
        }

        System.out.println("\nClasses available for Academic Year '" + currentSelectedAcademicYear.getYearIdentifier() + "':");
        if (availableClasses.isEmpty()) {
            System.out.println("No classes available in the system.");
        } else {
            for (int i = 0; i < availableClasses.size(); i++) {
                // In a real UI, there would be a button. Here, we just list them.
                System.out.println((i + 1) + ". " + availableClasses.get(i).getClassIdentifier() + " (Report Cards)");
            }
        }
        return Collections.unmodifiableList(availableClasses);
    }

    /**
     * Allows the administrator to choose a pupil class.
     * This sets the `currentSelectedClass` state.
     *
     * @param classIdentifier The identifier of the class to choose (e.g., "Grade 10A").
     * @return true if the class was successfully chosen, false otherwise.
     * @throws IllegalStateException if the administrator is not logged in or no academic year is selected.
     */
    public boolean choosePupilClass(String classIdentifier) {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to choose a class.");
        }
        if (currentSelectedAcademicYear == null) {
            throw new IllegalStateException("An academic year must be selected first.");
        }

        Optional<PupilClass> chosenClass = availableClasses.stream()
                .filter(pc -> pc.getClassIdentifier().equals(classIdentifier))
                .findFirst();

        if (chosenClass.isPresent()) {
            this.currentSelectedClass = chosenClass.get();
            System.out.println("Class '" + classIdentifier + "' chosen.");
            return true;
        } else {
            System.out.println("Error: Class '" + classIdentifier + "' not found.");
            this.currentSelectedClass = null;
            return false;
        }
    }

    /**
     * Displays the list of students in the currently chosen class.
     * This corresponds to System Event 4: "Displays the list of class students chosen by the user".
     *
     * @return An unmodifiable list of Student objects in the chosen class.
     * @throws IllegalStateException if the administrator is not logged in, no academic year is selected, or no class is chosen.
     */
    public List<Student> displayStudentsInClass() {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to display students.");
        }
        if (currentSelectedAcademicYear == null) {
            throw new IllegalStateException("An academic year must be selected first.");
        }
        if (currentSelectedClass == null) {
            throw new IllegalStateException("A class must be chosen first.");
        }

        List<Student> studentsInClass = currentSelectedClass.getStudents();
        System.out.println("\nStudents in Class '" + currentSelectedClass.getClassIdentifier() + "' for Academic Year '" + currentSelectedAcademicYear.getYearIdentifier() + "':");
        if (studentsInClass.isEmpty()) {
            System.out.println("No students found in this class.");
        } else {
            for (int i = 0; i < studentsInClass.size(); i++) {
                System.out.println((i + 1) + ". " + studentsInClass.get(i).getName() + " (ID: " + studentsInClass.get(i).getStudentId() + ")");
            }
        }
        return Collections.unmodifiableList(studentsInClass);
    }

    /**
     * Allows the administrator to select a student and four months to view their report.
     * This sets the `currentSelectedStudent` and `currentSelectedMonths` states.
     *
     * @param studentId The ID of the student to select.
     * @param months A list of four month names (e.g., "January", "February", "March", "April").
     * @return true if the student and months were successfully selected, false otherwise.
     * @throws IllegalStateException if the administrator is not logged in, no academic year is selected, or no class is chosen.
     * @throws IllegalArgumentException if the list of months is not exactly four.
     */
    public boolean selectStudentAndMonths(String studentId, List<String> months) {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to select a student.");
        }
        if (currentSelectedAcademicYear == null) {
            throw new IllegalStateException("An academic year must be selected first.");
        }
        if (currentSelectedClass == null) {
            throw new IllegalStateException("A class must be chosen first.");
        }
        if (months == null || months.size() != 4) {
            throw new IllegalArgumentException("Exactly four months must be selected to view the report.");
        }

        Optional<Student> selectedStudent = currentSelectedClass.getStudents().stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();

        if (selectedStudent.isPresent()) {
            this.currentSelectedStudent = selectedStudent.get();
            this.currentSelectedMonths = new ArrayList<>(months); // Create a mutable copy
            System.out.println("Student '" + currentSelectedStudent.getName() + "' (ID: " + studentId + ") and months " + months + " selected.");
            return true;
        } else {
            System.out.println("Error: Student with ID '" + studentId + "' not found in class '" + currentSelectedClass.getClassIdentifier() + "'.");
            this.currentSelectedStudent = null;
            this.currentSelectedMonths = new ArrayList<>();
            return false;
        }
    }

    /**
     * Displays the chosen student's report for the selected four months.
     * This corresponds to System Event 6: "Displays the chosen student report referred to the selected four months."
     *
     * @throws IllegalStateException if the administrator is not logged in, no academic year, class, student, or months are selected.
     */
    public void displayStudentReport() {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to display a report.");
        }
        if (currentSelectedAcademicYear == null) {
            throw new IllegalStateException("An academic year must be selected first.");
        }
        if (currentSelectedClass == null) {
            throw new IllegalStateException("A class must be chosen first.");
        }
        if (currentSelectedStudent == null) {
            throw new IllegalStateException("A student must be selected first.");
        }
        if (currentSelectedMonths.isEmpty()) {
            throw new IllegalStateException("Four months must be selected first.");
        }

        System.out.println("\n--- Report Card for " + currentSelectedStudent.getName() + " (ID: " + currentSelectedStudent.getStudentId() + ") ---");
        System.out.println("Academic Year: " + currentSelectedAcademicYear.getYearIdentifier());
        System.out.println("Class: " + currentSelectedClass.getClassIdentifier());

        ReportCard reportCard = currentSelectedStudent.getReportCard(currentSelectedAcademicYear);

        if (reportCard != null) {
            System.out.println("Grades for selected months:");
            Map<String, Integer> grades = reportCard.getGradesForMonths(currentSelectedMonths);
            if (grades.isEmpty()) {
                System.out.println("No grades found for the selected months.");
            } else {
                for (String month : currentSelectedMonths) {
                    System.out.println("  " + month + ": " + (grades.containsKey(month) ? grades.get(month) : "N/A"));
                }
            }
        } else {
            System.out.println("No report card found for this student in the selected academic year.");
        }
        System.out.println("---------------------------------------------------");
    }

    /**
     * Simulates the administrator stopping the connection to the SMOS server.
     * In this simulation, it just logs out the administrator.
     */
    public void stopConnectionToSM