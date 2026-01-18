
package com.school.infrastructure;

/**
 * SMTP client for sending emails.
 * Uses SMOSConnection for integration as per REQ-005.
 */
public class SmtpClient {
    private String host;
    private int port;
    private com.school.smos.SMOSConnection smosConnection;

    public SmtpClient(String host, int port, com.school.smos.SMOSConnection smosConnection) {
        this.host = host;
        this.port = port;
        this.smosConnection = smosConnection;
    }

    public void send(EmailMessage message) {
        // Simulate sending email via SMTP
        System.out.println("Sending email via SMTP to " + message.getTo());
        // Integration with SMOSConnection as per diagram
        if (smosConnection != null) {
            smosConnection.send("EMAIL: " + message.getSubject());
        }
    }
}
