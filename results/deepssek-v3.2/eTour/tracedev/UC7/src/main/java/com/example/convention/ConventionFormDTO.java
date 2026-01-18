package com.example.convention;

/**
 * Data Transfer Object for Convention form data.
 */
public class ConventionFormDTO {
    public String conventionId;
    public String pointId;
    public String agreementDetails;
    public String status;

    public ConventionFormDTO() {
    }

    /**
     * Constructs a DTO from a Convention entity.
     * @param convention the convention to copy data from.
     */
    public ConventionFormDTO(Convention convention) {
        this.conventionId = convention.getId();
        this.pointId = convention.getRefreshmentPointId();
        this.agreementDetails = convention.getAgreementData();
        this.status = convention.getStatus().name();
    }
}