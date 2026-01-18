package com.absenceapp;

/**
 * Interface for notification serv.
 * <<Notification>> stereotype suggests it handles sending out various notifications.
 */
public interface INotificationService {
    /**
     * Sends a notification about an absence modification to the student's parent.
     *
     * @param student The student related to the absence.
     * @param parentEmail The email address of the parent to notify.
     * @param absence The absence record that was modified.
     */
    void sendAbsenceModificationNotification(Student student, String parentEmail, Absence absence);
}