package com.example.insertdelayadmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a DelayRecord entity in the system.
 * This records when a student was delayed, the reason, and by which administrator it was entered.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayRecord {
    /**
     * Unique identifier for the delay record.
     * Generated automatically using UUID.
     */
    @Id
    private String id;

    /**
     * The ID of the student associated with this delay record.
     * This is a foreign key to the Student entity.
     */
    @Column(nullable = false)
    private String studentId;

    /**
     * The date on which the delay occurred.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * The reason for the delay, using the DelayReason enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DelayReason reason;

    /**
     * The timestamp when this delay record was entered into the system.
     * Automatically set upon creation.
     */
    @Column(nullable = false)
    private LocalDateTime entryTimestamp;

    /**
     * The ID of the administrator who entered this delay record.
     * This is a foreign key to the Administrator entity.
     */
    @Column(nullable = false)
    private String enteredByAdminId;

    /**
     * A flag indicating whether the parent of the student has been notified about this delay.
     */
    private boolean parentNotified;

    /**
     * Many-to-one relationship with the Student entity.
     * Multiple delay records can belong to one student.
     * The 'studentId' column in this table is the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    /**
     * Many-to-one relationship with the Administrator entity.
     * Multiple delay records can be entered by one administrator.
     * The 'enteredByAdminId' column in this table is the foreign key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enteredByAdminId", referencedColumnName = "id", insertable = false, updatable = false)
    private Administrator administrator;

    /**
     * Pre-persist method to generate a UUID for the ID and set the entry timestamp.
     */
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        this.entryTimestamp = LocalDateTime.now();
        this.parentNotified = false; // Default to false when created
    }
}