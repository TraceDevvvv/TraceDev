package com.example.adapter.out.notification;

import com.example.application.port.out.AgencyNotificationPort;
import com.example.domain.ConventionRequest;
import com.example.adapter.out.notification.EmailService;
import com.example.adapter.out.notification.Email;

public class EmailAgencyNotifier implements AgencyNotificationPort {
    private final EmailService emailService;

    public EmailAgencyNotifier(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean notifyAgency(ConventionRequest request) {
        Email email = buildNotificationEmail(request);
        return emailService.send(email);
    }

    private Email buildNotificationEmail(ConventionRequest request) {
        // Build email content based on request
        String subject = "New Convention Request: " + request.getRequestId();
        String body = "A new convention request has been submitted.\n" +
                      "Restaurant: " + request.getRestaurantId() + "\n" +
                      "Terms: " + request.getConventionData().getAgreementTerms() + "\n" +
                      "Status: " + request.getStatus();
        return new Email("agency@example.com", subject, body);
    }
}