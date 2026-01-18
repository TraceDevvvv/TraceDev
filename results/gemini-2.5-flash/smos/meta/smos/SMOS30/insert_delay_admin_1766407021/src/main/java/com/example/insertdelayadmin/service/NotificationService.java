package com.example.insertdelayadmin.service;

import com.example.insertdelayadmin.model.DelayRecord;
import com.example.insertdelayadmin.model.Notification;
import com.example.insertdelayadmin.model.Student;
import com.example.insertdelayadmin.repository.NotificationRepository;
import com.example.insertdelayadmin.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class responsible for sending notifications to parents.
 * It interacts with {@link NotificationRepository} to persist notification records
 * and {@link StudentRepository} to retrieve parent contact information.
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;

    /**
     * Constructor for NotificationService, injecting required dependencies.
     *
     * @param notificationRepository Repository for Notification entities.
     * @param studentRepository Repository for Student entities.
     */
    @Autowired
    public NotificationService(NotificationRepository notificationRepository, StudentRepository studentRepository) {
        this.notificationRepository = notificationRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Sends a notification to the parent of the student associated with the given delay record.
     * This method simulates sending a notification and records its status in the database.
     *
     * @param delayRecord The {@link DelayRecord} for which to send a notification.
     */
    @Transactional
    public void sendParentNotification(DelayRecord delayRecord) {
        Optional<Student> studentOptional = studentRepository.findById(delayRecord.getStudentId());

        if (studentOptional.isEmpty()) {
            logger.warn("Could not send notification: Student with ID {} not found for delay record {}.",
                    delayRecord.getStudentId(), delayRecord.getId());
            return;
        }

        Student student = studentOptional.get();
        String recipient = student.getParentContactInfo();

        if (recipient == null || recipient.isEmpty()) {
            logger.warn("Could not send notification: Parent contact info missing for student {} (ID: {}).",
                    student.getName(), student.getId());
            // Optionally, create a notification with FAILED status here
            saveNotification(delayRecord, student, "FAILED", "Parent contact information missing.");
            return;
        }

        // Construct the notification message
        String message = String.format("Dear Parent/Guardian, your child %s was delayed on %s due to %s. " +
                        "This record was entered by an administrator at %s.",
                student.getName(), delayRecord.getDate().toString(), delayRecord.getReason().name().replace("_", " "),
                delayRecord.getEntryTimestamp().toLocalTime().toString());

        // Simulate sending the notification (e.g., via email, SMS API call)
        boolean notificationSentSuccessfully = simulateExternalNotificationSend(recipient, message);

        String status = notificationSentSuccessfully ? "SENT" : "FAILED";
        String statusMessage = notificationSentSuccessfully ? "Notification sent successfully." : "Failed to send notification to external system.";

        saveNotification(delayRecord, student, status, statusMessage);

        if (notificationSentSuccessfully) {
            logger.info("Notification sent to {} for student {} (ID: {}) regarding delay record {}.",
                    recipient, student.getName(), student.getId(), delayRecord.getId());
        } else {
            logger.error("Failed to send notification to {} for student {} (ID: {}) regarding delay record {}.",
                    recipient, student.getName(), student.getId(), delayRecord.getId());
        }
    }

    /**
     * Helper method to simulate an external notification sending process.
     * In a real application, this would involve integrating with an email service, SMS gateway, etc.
     *
     * @param recipient The recipient's contact information.
     * @param message The message to send.
     * @return true if the simulated send was successful, false otherwise.
     */
    private boolean simulateExternalNotificationSend(String recipient, String message) {
        // For demonstration purposes, we'll always return true.
        // In a real application, this would involve actual API calls and error handling.
        logger.debug("Simulating sending notification to: {} with message: {}", recipient, message);
        return true; // Assume success for now
    }

    /**
     * Saves a notification record to the database.
     *
     * @param delayRecord The associated delay record.
     * @param student The associated student.
     * @param status The status of the notification (e.g., "SENT", "FAILED").
     * @param statusMessage A descriptive message about the notification status.
     */
    private void saveNotification(DelayRecord delayRecord, Student student, String status, String statusMessage) {
        Notification notification = new Notification();
        // ID and sentTimestamp are set by @PrePersist
        notification.setRecipient(student.getParentContactInfo());
        notification.setMessage(statusMessage + " Original message: " +
                String.format("Dear Parent/Guardian, your child %s was delayed on %s due to %s.",
                        student.getName(), delayRecord.getDate().toString(), delayRecord.getReason().name().replace("_", " ")));
        notification.setStatus(status);
        notification.setDelayRecordId(delayRecord.getId());
        notificationRepository.save(notification);
    }
}