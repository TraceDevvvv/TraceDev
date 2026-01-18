package com.example.absence.infrastructure;

import com.example.absence.domain.Student;
import com.example.absence.domain.Notification;
import java.util.Date;
import java.util.List;

/**
 * Service interface for sending notifications.
 */
public interface INotificationService {
    /**
     * Sends an absence notification for a single student.
     * @param student the student
     * @param absenceDate the date of absence
     */
    void sendAbsenceNotification(Student student, Date absenceDate);

    /**
     * Sends multiple notifications in bulk.
     * @param notifications the list of notifications to send
     * @return number of successfully sent notifications
     */
    int sendBulkNotifications(List<Notification> notifications);
}