package com.example.justification.application.dto;

/**
 * Data Transfer Object (DTO) for conveying justification details.
 * Used to transfer data from the application service layer to the presentation layer.
 */
public class JustificationDetailsDto {
    public String id;
    public String details;
    public String status;
    public String absenceId;

    /**
     * Constructs a new JustificationDetailsDto.
     *
     * @param id        The unique identifier of the justification.
     * @param details   The detailed description of the justification.
     * @param status    The current status of the justification.
     * @param absenceId The identifier of the absence this justification is related to.
     */
    public JustificationDetailsDto(String id, String details, String status, String absenceId) {
        this.id = id;
        this.details = details;
        this.status = status;
        this.absenceId = absenceId;
    }
}