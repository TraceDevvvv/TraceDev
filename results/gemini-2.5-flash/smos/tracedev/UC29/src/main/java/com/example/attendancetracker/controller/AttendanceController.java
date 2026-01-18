package com.example.attendancetracker.controller;

import com.example.attendancetracker.model.AttendanceModel;
import com.example.attendancetracker.model.AttendanceRecord;
import com.example.attendancetracker.model.Student;
import com.example.attendancetracker.service.AttendanceService;
import com.example.attendancetracker.repository.StudentRepository; // Needed to get all students for the form
import com.example.attendancetracker.view.AttendanceView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller class managing attendance-related interactions.
 * Acts as the <<Controller>> in MVC.
 */
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final AttendanceView attendanceView;
    private final StudentRepository studentRepository; // Controller needs to fetch students for form
    private AttendanceModel currentModel; // Holds the state of the current attendance form/data

    public AttendanceController(AttendanceService attendanceService, AttendanceView attendanceView, StudentRepository studentRepository) {
        this.attendanceService = attendanceService;
        this.attendanceView = attendanceView;
        this.studentRepository = studentRepository;
        this.currentModel = new AttendanceModel(); // Initialize an empty model
    }

    /**
     * Displays the attendance form for a given date.
     * Fetches existing attendance records and available students for that date.
     * @param date The date for which the attendance form is to be displayed.
     * @return An AttendanceModel populated with data for the form.
     */
    public AttendanceModel displayAttendanceForm(Date date) {
        System.out.println("[AttendanceController] Administrator requests attendance form for date: " + date.toInstant().toString().substring(0,10));
        // Get existing attendance records for the selected date
        List<AttendanceRecord> existingRecords = attendanceService.getAttendanceRecords(date);

        // Get all students for form display (assumption: all registered students are shown)
        List<Student> allStudents = studentRepository.findAllStudents();

        // Create and populate the model
        this.currentModel = new AttendanceModel(date, allStudents, existingRecords);

        // Render the view
        attendanceView.render(currentModel);
        return currentModel;
    }

    /**
     * Saves attendance data submitted by the administrator.
     * Processes the data through the service layer and updates the view.
     * @param attendanceData A map where key is student ID and value is a list containing the attendance status string.
     * @return A list of updated attendance records after the save operation.
     */
    public List<AttendanceRecord> saveAttendance(Map<String, List<String>> attendanceData) {
        System.out.println("[AttendanceController] Administrator submits attendance data.");
        // Ensure a date context is available, typically from the currentModel's selectedDate
        if (currentModel.selectedDate == null) {
            System.err.println("[AttendanceController] Error: No selected date available in model. Cannot save attendance.");
            return null; // Or throw an exception
        }

        // Delegate to the service to process attendance entry.
        // The `selectedDate` needs to be passed to the service, as per the refined `AttendanceService` method.
        List<AttendanceRecord> updatedLogData = attendanceService.processAttendanceEntry(attendanceData, currentModel.selectedDate);

        // Update the model with the new log data
        currentModel.setUpdatedLogData(updatedLogData);

        // Render the view with updated information (display updated log)
        attendanceView.render(currentModel);
        return updatedLogData;
    }

    /**
     * Handles the administrator's request to cancel an operation.
     * Displays a confirmation message.
     */
    public void cancelOperation() {
        System.out.println("[AttendanceController] Administrator interrupts operation (cancelOperation called).");
        attendanceView.displayConfirmation("Operation cancelled by Administrator.");
    }
}