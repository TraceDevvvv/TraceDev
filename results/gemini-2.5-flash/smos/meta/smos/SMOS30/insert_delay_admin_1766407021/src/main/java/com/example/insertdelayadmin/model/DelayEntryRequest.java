package com.example.insertdelayadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for encapsulating the request to create a new delay record.
 * Used when an administrator submits a form to register a student's delay.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayEntryRequest {
    /**
     * The ID of the student for whom the delay is being recorded.
     */
    private String studentId;

    /**
     * The date on which the delay occurred.
     * Expected in a format that can be parsed into LocalDate (e.g., "YYYY-MM-DD").
     */
    private LocalDate date;

    /**
     * The reason for the delay. This string should correspond to one of the
     * values in the {@link DelayReason} enum (e.g., "LATE_BUS", "APPOINTMENT").
     */
    private String reason;
}