package com.example.insertdelayadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing a delay record in a simplified format for display.
 * This DTO is used to send relevant delay information to the client, often combining data
 * from multiple entities (e.g., DelayRecord, Student, Administrator) into a single object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayRecordDTO {
    /**
     * Unique identifier for the delay record.
     */
    private String id;

    /**
     * The name of the student associated with this delay record.
     * This is derived from the Student entity.
     */
    private String studentName;

    /**
     * The date on which the delay occurred.
     */
    private LocalDate date;

    /**
     * The reason for the delay (e.g., "LATE_BUS", "APPOINTMENT").
     */
    private String reason;

    /**
     * The timestamp when this delay record was entered into the system.
     */
    private LocalDateTime entryTimestamp;

    /**
     * A flag indicating whether the parent of the student has been notified about this delay.
     */
    private boolean parentNotified;

    /**
     * The name of the administrator who entered this delay record.
     * This is derived from the Administrator entity.
     */
    private String enteredByAdminName;
}