package com.example.attendancetracker.service;

import com.example.attendancetracker.external.EmailGateway;
import com.example.attendancetracker.exception.ExternalServiceException;
import com.example.attendancetracker.model.NotificationEvent;
import com.example.attendancetracker.repository.AttendanceRecordRepository;

import java.text.SimpleDateFormat;

/**
 * Service responsible for processing notification events and sending emails.
 * <<Service>> layer.
 */
public class NotificationService {
    private final MessageQueueService messageQueueService;
    private final EmailGateway emailGateway;
    private final AttendanceRecordRepository attendanceRecordRepository;

    public NotificationService(MessageQueueService messageQueueService, EmailGateway emailGateway, AttendanceRecordRepository attendanceRecordRepository) {
        this.messageQueueService = messageQueueService;
        this.emailGateway = emailGateway;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    /**
     * Processes a single notification event. This involves sending an email
     * and updating the attendance record's notification status.
     * Handles potential failures in email sending (e.g., SMOS server connection issues).
     * @param event The NotificationEvent to process.
     */
    public void processNotificationEvent(NotificationEvent event) {
        log("NotificationService: Processing event for student " + event.getStudentId() + " on " + event.getDate());
        try {
            // Format date for email body
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(event.getDate());

            // EmailGateway: sendEmail
            emailGateway.sendEmail(
                event.getParentEmail(),
                "Absence Notification",
                "Dear Parent,\n\nYour child (" + event.getStudentId() + ") was absent on " + formattedDate + ".\n\nRegards,\nSchool Attendance System"
            );

            // If email sent successfully, update notification status
            attendanceRecordRepository.updateNotificationStatus(event.getStudentId(), event.getDate(), true);

            // Acknowledge processing to the MessageQueueService
            messageQueueService.acknowledgeProcessing(event.getEventId());

        } catch (ExternalServiceException e) {
            // Handle "Connection to SMOS server interrupted" scenario
            log("SMOS server connection interrupted for email notification for " + event.getStudentId() + ". Error: " + e.getMessage());
            messageQueueService.reportProcessingFailure(event.getEventId(), "SMOS connection error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected errors during processing
            log("An unexpected error occurred while processing notification for " + event.getStudentId() + ": " + e.getMessage());
            messageQueueService.reportProcessingFailure(event.getEventId(), "Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Logs a message to the console.
     * Added to satisfy requirement R17 and SD consistency.
     * @param message The message to log.
     */
    public void log(String message) {
        System.out.println("[NotificationService] " + message);
    }
}