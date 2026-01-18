package com.example.absence.observer;

import com.example.absence.domain.Absence;
import com.example.absence.infrastructure.INotificationService;

/**
 * Observer that sends email notifications when an absence is recorded.
 */
public class EmailNotificationObserver implements IAbsenceObserver {
    private INotificationService notificationService;

    public EmailNotificationObserver(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onAbsenceRecorded(Absence absence) {
        // Send notification for the absent student
        if (absence.isAbsent()) {
            notificationService.sendAbsenceNotification(absence.getStudent(), absence.getDate());
        }
    }
}