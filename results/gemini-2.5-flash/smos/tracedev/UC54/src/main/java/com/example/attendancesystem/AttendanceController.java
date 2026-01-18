package com.example.attendancesystem;

import java.util.List;
import java.util.Map;

/**
 * Handles user interactions related to attendance and orchestrates the application flow.
 * Defined in the Presentation Layer of the Class Diagram.
 * Acts as a bridge between the AttendanceView and the AttendanceService.
 */
public class AttendanceController {
    private AttendanceService attendanceService;
    private String currentClassId; // Stores the class ID currently being managed
    private AttendanceView view; // Reference to the view for direct interaction (SD requirement)

    /**
     * Constructs an AttendanceController with its dependencies.
     * Note: The AttendanceView is set via a setter after instantiation to resolve circular dependency
     * with AttendanceView needing a controller reference.
     * @param attendanceService The service layer for attendance operations.
     */
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Sets the view associated with this controller. This is used to resolve a circular dependency
     * and allow the controller to directly interact with the view as per the Sequence Diagram.
     * @param view The AttendanceView instance.
     */
    public void setView(AttendanceView view) {
        this.view = view;
    }

    /**
     * Handles the request to display the attendance screen for a specific class.
     * Sequence Diagram: ATAStaff -> Controller : requestAttendanceScreen
     *
     * @param classId The ID of the class for which attendance is to be managed.
     */
    public void requestAttendanceScreen(String classId) {
        System.out.println("\n[AttendanceController] Requesting attendance screen for class: " + classId);
        this.currentClassId = classId; // Store the class ID
        // Sequence Diagram: Controller -> Service : getStudentsForClass
        List<Student> students = attendanceService.getStudentsForClass(classId);
        // Sequence Diagram: Controller -> View : displayStudents
        if (view != null) {
            view.displayStudents(students);
        } else {
            System.err.println("[AttendanceController] Error: View not set for controller.");
        }
    }

    /**
     * Saves the attendance data provided by the view.
     * Sequence Diagram: View -> Controller : saveAttendance
     *
     * @param attendanceData A map where keys are student IDs and values are their attendance statuses.
     */
    public void saveAttendance(Map<String, AttendanceStatus> attendanceData) {
        System.out.println("\n[AttendanceController] Saving attendance for class: " + currentClassId);
        if (view == null) {
            System.err.println("[AttendanceController] Error: View not set for controller. Cannot display messages.");
            return;
        }
        if (currentClassId == null) {
            view.showErrorMessage("Cannot save attendance: No class selected.");
            return;
        }
        if (attendanceData == null || attendanceData.isEmpty()) {
            view.showErrorMessage("No attendance data provided to save. Please select student statuses.");
            return;
        }

        try {
            // Sequence Diagram: Controller -> Service : recordAttendance
            attendanceService.recordAttendance(currentClassId, attendanceData);
            System.out.println("[AttendanceController] Attendance service call successful.");
            // Sequence Diagram: Controller -> View : showSuccessMessage
            view.showSuccessMessage("Attendance recorded and notifications sent.");
        } catch (ServiceError e) {
            // R16, Sequence Diagram: Service --x Controller : throws ServiceError
            // Sequence Diagram: Controller -> View : showErrorMessage
            System.err.println("[AttendanceController] Error during attendance save: " + e.getMessage());
            view.showErrorMessage("Failed to save attendance: " + e.getMessage());
        }
    }
}