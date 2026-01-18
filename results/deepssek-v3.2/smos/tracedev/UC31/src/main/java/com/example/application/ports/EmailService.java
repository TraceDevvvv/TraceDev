package com.example.application.ports;

/**
 * Port for sending emails.
 */
public interface EmailService {
    boolean sendEmail(String to, String subject, String body);
}