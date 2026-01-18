package com.example.attendancesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random; // For simulating random selections for demo

/**
 * Represents the user interface for attendance management.
 * Defined in the Presentation Layer of the Class Diagram.
 * Interacts with the AttendanceController to manage state and actions.
 */
public class AttendanceView {
    private AttendanceController attendanceController;
    private List<Student> displayedStudents; // Students currently displayed on the UI
    private String currentClassId; // Keep track of the current class ID for context

    /**
     * Constructs an AttendanceView with its controller.
     * @param attendanceController The controller responsible for handling UI actions.
     */
    public AttendanceView(AttendanceController attendanceController) {
        this.attendanceController = attendanceController;
    }

    /**
     * Displays a list of students on the attendance screen.
     * Sequence Diagram: Controller -> View : displayStudents
     * @param students The list of students to display.
     */
    public void displayStudents(List<Student> students) {
        System.out.println("\n--- Attendance View ---");
        if (students.isEmpty()) {
            System.out.println("No students found for this class.");
            this.displayedStudents = new ArrayList<>();
        } else {
            System.out.println("Displaying students:");
            this.displayedStudents = students;
            // As per SD note, all students are typically present by default
            students.forEach(s -> System.out.println("    " + s.getStudentId() + ": " + s.getName() + " (default: PRESENT)"));
        }
        System.out.println("-----------------------\n");
    }

    /**
     * Simulates collecting attendance data from the UI.
     * As per SD, View -> View : attendanceData = handleSaveAction()
     * For this simulation, it will mark one random student absent, others present.
     * @return A map of student IDs to their selected attendance status.
     */
    public Map<String, AttendanceStatus> getAttendanceData() {
        Map<String, AttendanceStatus> data = new HashMap<>();
        if (displayedStudents != null && !displayedStudents.isEmpty()) {
            Random random = new Random();
            int absentIndex = random.nextInt(displayedStudents.size()); // Pick one student to be absent
            for (int i = 0; i < displayedStudents.size(); i++) {
                Student student = displayedStudents.get(i);
                if (i == absentIndex) {
                    data.put(student.getStudentId(), AttendanceStatus.ABSENT);
                    System.out.println("[AttendanceView] Simulating user marking " + student.getName() + " as ABSENT.");
                } else {
                    data.put(student.getStudentId(), AttendanceStatus.PRESENT);
                }
            }
            System.out.println("[AttendanceView] Simulated attendance data collected: " + data);
        } else {
             System.out.println("[AttendanceView] No students displayed to collect attendance for.");
        }
        return data;
    }

    /**
     * Displays a success message to the user.
     * Sequence Diagram: Controller -> View : showSuccessMessage
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n[AttendanceView] SUCCESS: " + message);
        displayInitialScreen(); // Simulate returning to initial screen
    }

    /**
     * Displays an error message to the user.
     * Sequence Diagram: Controller -> View : showErrorMessage
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[AttendanceView] ERROR: " + message);
        // In a real UI, this might keep the form open with the error.
        // For simulation, we'll assume it just displays the error.
    }

    /**
     * Simulates the user's action of clicking the save button.
     * Sequence Diagram: ATAStaff -> View : clickSaveButton()
     * Sequence Diagram: View -> View : attendanceData = handleSaveAction()
     * Sequence Diagram: View -> Controller : saveAttendance()
     */
    public void clickSaveButton() {
        System.out.println("\n[ATA Staff] clicks save button.");
        Map<String, AttendanceStatus> attendanceData = handleSaveAction();
        // The controller handles calling its saveAttendance method and will manage success/error messages via its view reference.
        attendanceController.saveAttendance(attendanceData);
    }

    /**
     * Handles the logic for collecting attendance data from the UI when save is clicked.
     * This method is called internally by clickSaveButton to prepare data.
     * Defined in the Class Diagram.
     * @return The attendance data collected from the view.
     */
    public Map<String, AttendanceStatus> handleSaveAction() {
        System.out.println("[AttendanceView] User clicked save button. Collecting attendance data...");
        // In a real UI, this would collect data from form elements.
        // Here, we just call getAttendanceData() which returns dummy data.
        return getAttendanceData();
    }

    /**
     * Simulates the user clicking the cancel button.
     * Sequence Diagram: ATAStaff -> View : clickCancel()
     * Defined in the Class Diagram.
     */
    public void clickCancel() {
        System.out.println("[AttendanceView] User clicked cancel. Returning to initial screen.");
        displayInitialScreen(); // Simulate going back to initial screen
    }

    /**
     * Helper method to simulate navigating back to an initial screen state.
     */
    private void displayInitialScreen() {
        System.out.println("--- Initial Screen Displayed ---");
        this.displayedStudents = null; // Clear displayed students
        this.currentClassId = null; // Clear current class context
    }

    // --- Entry point methods called by Main simulation ---

    /**
     * Initiates the process of requesting the attendance screen.
     * This acts as the ATA Staff's interaction with the View.
     * Sequence Diagram: ATAStaff -> Controller : requestAttendanceScreen (via View)
     * @param classId The class ID for which to load the attendance screen.
     */
    public void requestAttendanceScreen(String classId) {
        System.out.println("\n[ATA Staff] requests attendance screen for class: " + classId);
        this.currentClassId = classId;
        // The View delegates the request to the Controller, which will then tell the View to display students.
        attendanceController.requestAttendanceScreen(classId);
    }
}