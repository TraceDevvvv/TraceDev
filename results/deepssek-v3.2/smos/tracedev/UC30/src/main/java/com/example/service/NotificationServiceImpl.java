package com.example.service;

import com.example.model.StudentDelay;

/**
 * Implementation of INotificationService.
 * Sends email and SMS notifications.
 */
public class NotificationServiceImpl implements INotificationService {
    // In a real implementation, these would be injected.
    private EmailService emailService = new EmailService();
    private SMSService smsService = new SMSService();
    
    @Override
    public void sendParentNotification(StudentDelay delay) {
        // Asynchronous notification processing (as per sequence diagram group)
        prepareNotification();
        sendEmail(delay);
        sendSMS(delay);
    }
    
    private void prepareNotification() {
        System.out.println("Preparing notification...");
    }
    
    private void sendEmail(StudentDelay delay) {
        emailService.send(delay.getParentEmail(), "Delay Notice", 
                "Your child " + delay.getStudentName() + " was delayed by " + delay.getDelayDuration() + " minutes.");
    }
    
    private void sendSMS(StudentDelay delay) {
        smsService.send(delay.getParentPhone(), 
                "Delay alert: " + delay.getStudentName() + " delayed by " + delay.getDelayDuration() + " min.");
    }
}