package com.example.service;

/**
 * Simulated SMS service.
 */
public class SMSService {
    public void send(String phoneNumber, String message) {
        System.out.println("SMS sent to: " + phoneNumber + ", Message: " + message);
    }
}