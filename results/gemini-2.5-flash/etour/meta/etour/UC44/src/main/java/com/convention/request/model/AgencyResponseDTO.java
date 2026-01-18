package com.convention.request.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the response received from the external agency system (ETOUR)
 * after sending a convention request.
 * This class encapsulates the status and any relevant messages from the agency.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyResponseDTO {
    /**
     * The unique identifier for the convention as recognized by the agency,
     * which might be the same as the internal conventionId or a different one.
     */
    private String conventionId;

    /**
     * The status of the convention request as reported by the agency (e.g., "ACCEPTED", "REJECTED", "PENDING").
     */
    private String status;

    /**
     * A message from the agency providing more details about the response,
     * such as reasons for rejection or confirmation details.
     */
    private String message;
}