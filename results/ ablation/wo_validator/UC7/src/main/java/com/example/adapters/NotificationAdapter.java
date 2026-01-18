package com.example.adapters;

import com.example.domain.Convention;
import com.example.ports.NotificationPort;

/**
 * Adapter implementing NotificationPort using email and SMS serv.
 */
public class NotificationAdapter implements NotificationPort {

    private final EmailService emailService;
    private final SmsService smsService;

    public NotificationAdapter(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @Override
    public void notifyActivation(Convention convention) {
        // Send notifications via email and SMS
        String message = "Convention " + convention.getId() + " has been activated.";
        emailService.send("admin@example.com", "Convention Activated", message);
        smsService.send("+1234567890", message);
    }
}

// Assumed external service classes
class EmailService {
    public void send(String to, String subject, String body) {
        System.out.println("Email sent to " + to + ": " + subject + " - " + body);
    }
}

class SmsService {
    public void send(String phoneNumber, String message) {
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }
}