package com.example.attendancetracker.external;

import com.example.attendancetracker.exception.ExternalServiceException;

/**
 * External service for sending emails.
 * It interacts with the SMOSServer for underlying infrastructure.
 * <<External>> service.
 */
public class EmailGateway {

    /**
     * Sends an email to the specified recipient.
     * Simulates external service call and potential connection issues.
     * @param to The recipient's email address.
     * @param subject The subject of the email.
     * @param body The body of the email.
     * @throws ExternalServiceException if there is a connection issue with the SMOS server.
     */
    public void sendEmail(String to, String subject, String body) throws ExternalServiceException {
        System.out.println("[EmailGateway] Attempting to send email to " + to + " via SMOSServer...");
        if (!SMOSServer.isAvailable()) {
            // Simulate connection error as per sequence diagram "Connection to SMOS server interrupted"
            throw new ExternalServiceException("Connection to SMOS server interrupted.");
        }

        // Simulate successful email sending
        System.out.println("--- EMAIL SENT ---");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("------------------");
    }
}