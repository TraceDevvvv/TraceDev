package com.example.service;

/**
 * Simulated email service.
 */
public class EmailService {
    public void send(String to, String subject, String body) {
        System.out.println("Email sent to: " + to + ", Subject: " + subject);
    }
}