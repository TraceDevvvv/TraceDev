package com.example.controller;

import com.example.service.AttendanceService;
import com.example.service.NotificationService;
import com.example.dto.AttendanceFormDTO;
import com.example.dto.AttendanceDisplayData;
import com.example.dto.RegistrationResult;
import com.example.dto.AbsenceNotificationDTO;
import com.example.entity.AttendanceRecord;
import java.util.Date;

/**
 * Controller for attendance registration.
 * Corresponds to the sequence diagram participant "Controller".
 */
public class AttendanceRegistrationController {
    private AttendanceService attendanceService;
    private NotificationService notificationService;

    public AttendanceRegistrationController(AttendanceService attendanceService, NotificationService notificationService) {
        this.attendanceService = attendanceService;
        this.notificationService = notificationService;
    }

    /**
     * Handles date selection from UI.
     */
    public void handleDateSelection(Date date) {
        // Update display via service
        AttendanceDisplayData displayData = attendanceService.updateDisplay(date);
        // In a real application, you would pass displayData to the UI.
    }

    /**
     * Handles form submission.
     */
    public RegistrationResult handleFormSubmission(AttendanceFormDTO formData) {
        // Save attendance record via service
        RegistrationResult result = attendanceService.saveAttendanceRecord(formData);
        
        // Send data to server (if needed)
        if (result.success && result.savedRecord != null) {
            sendDataToServer(result.savedRecord);
        }
        
        return result;
    }

    /**
     * Sends data to server.
     */
    public void sendDataToServer(AttendanceRecord record) {
        // Implementation would involve sending to a remote server.
        // For simplicity, we just log.
        System.out.println("Sending attendance record to server: " + record.getId());
    }

    /**
     * Cancels the current process.
     */
    public void cancelCurrentProcess() {
        // Cancel pending operations in service
        attendanceService.cancelPendingOperations();
    }

    /**
     * Handles notification failure.
     */
    public void handleNotificationFailure() {
        // Delegate to service
        attendanceService.handleNotificationFailure();
    }

    /**
     * Directly sends email via notification service (used in error flow).
     */
    public void sendEmail(AbsenceNotificationDTO notificationDTO) {
        // This is called when Controller directly sends email in error flow
        notificationService.sendEmail(notificationDTO);
    }
}