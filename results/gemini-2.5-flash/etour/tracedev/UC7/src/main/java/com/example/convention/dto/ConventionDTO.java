package com.example.convention.dto;

/**
 * Data Transfer Object (DTO) for Convention details.
 * This class is used to transfer convention information to the UI.
 */
public class ConventionDTO {
    public String id;
    public String refreshmentPointName;
    public String status;
    public String details;

    /**
     * Constructor for ConventionDTO.
     *
     * @param id The unique identifier of the convention.
     * @param refreshmentPointName The name of the associated refreshment point.
     * @param status The current status of the convention.
     * @param details Additional details about the convention.
     */
    public ConventionDTO(String id, String refreshmentPointName, String status, String details) {
        this.id = id;
        this.refreshmentPointName = refreshmentPointName;
        this.status = status;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ConventionDTO{" +
               "id='" + id + '\'' +
               ", refreshmentPointName='" + refreshmentPointName + '\'' +
               ", status='" + status + '\'' +
               ", details='" + details + '\'' +
               '}';
    }
}