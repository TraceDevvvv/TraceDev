package com.convention.request.model;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object (DTO) for creating or updating a Convention request.
 * This class is used to receive convention data from the client and includes
 * validation annotations to ensure data integrity.
 */
@Data
public class ConventionRequestDTO {

    @NotBlank(message = "Agency name cannot be empty")
    @Size(max = 255, message = "Agency name cannot exceed 255 characters")
    private String agencyName;

    @NotBlank(message = "Convention type cannot be empty")
    @Size(max = 100, message = "Convention type cannot exceed 100 characters")
    private String conventionType;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotBlank(message = "Contact person cannot be empty")
    @Size(max = 255, message = "Contact person name cannot exceed 255 characters")
    private String contactPerson;

    @NotBlank(message = "Contact email cannot be empty")
    @Email(message = "Contact email must be a valid email address")
    @Size(max = 255, message = "Contact email cannot exceed 255 characters")
    private String contactEmail;

    // Required documents can be optional, but if present, size constraints might apply
    private List<String> requiredDocuments;

    /**
     * Custom validation logic can be added here if needed,
     * for example, to check if endDate is after startDate.
     * This method is not part of standard JSR 380 validation but can be called
     * by a custom validator or directly in the service layer.
     *
     * @throws IllegalArgumentException if validation fails
     */
    public void validate() {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        // Additional custom validation rules can be added here
    }
}