package com.example.external;

public class EmailService {
    private String serverAddress;

    public EmailService(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via server: " + serverAddress);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        // Simulate email sending (always success for demonstration)
        return true;
    }
}