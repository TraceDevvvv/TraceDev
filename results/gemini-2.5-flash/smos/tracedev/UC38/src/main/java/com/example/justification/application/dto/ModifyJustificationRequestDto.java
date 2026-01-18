package com.example.justification.application.dto;

/**
 * Data Transfer Object (DTO) for requesting a modification to a justification.
 * Carries data from the presentation layer to the application service layer for updates.
 */
public class ModifyJustificationRequestDto {
    public String details;
    public String status;

    /**
     * Constructs a new ModifyJustificationRequestDto.
     *
     * @param details The new detailed description for the justification.
     * @param status  The new status for the justification.
     */
    public ModifyJustificationRequestDto(String details, String status) {
        this.details = details;
        this.status = status;
    }
}