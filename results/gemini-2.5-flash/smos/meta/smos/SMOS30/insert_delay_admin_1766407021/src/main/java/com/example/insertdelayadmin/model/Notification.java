package com.example.insertdelayadmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a Notification entity in the system.
 * Notifications are sent to parents when a delay record is created for their child.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    /**
     * Unique identifier for the notification.
     * Generated automatically using UUID.
     */
    @Id
    private String id;

    /**
     * The recipient of the notification (e.g., parent's email or phone number).
     */
    @Column(nullable = false)
    private String recipient;

    /**
     * The content of the notification message.
     */
    @Column(nullable = false, length = 1000) // Assuming a reasonable length for messages
    private String message;

    /**
     * The timestamp when the notification was sent.
     * Automatically set upon creation.
     */
    @Column(nullable = false)
    private LocalDateTime sentTimestamp;

    /**
     * The status of the notification (e.g., "SENT", "FAILED", "PENDING").
     */
    @Column(nullable = false)
    private String status;

    /**
     * The ID of the delay record that triggered this notification.
     * This is a foreign key to the DelayRecord entity.
     */
    @Column(nullable = false)
    private String delayRecordId;

    /**
     * Many-to-one relationship with the DelayRecord entity.
     * One delay record can trigger one or more notifications (e.g., to multiple parents/guardians).
     * The 'delayRecordId' column in this table is the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delayRecordId", referencedColumnName = "id", insertable = false, updatable = false)
    private DelayRecord delayRecord;

    /**
     * Pre-persist method to generate a UUID for the ID and set the sent timestamp.
     */
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        this.sentTimestamp = LocalDateTime.now();
        // Default status can be set here or in the service layer
        if (this.status == null) {
            this.status = "PENDING"; // Or "SENT" if sending is immediate
        }
    }
}