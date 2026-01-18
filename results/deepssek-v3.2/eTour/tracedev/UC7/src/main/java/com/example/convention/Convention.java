package com.example.convention;

/**
 * Represents a convention agreement for a refreshment point.
 */
public class Convention {
    private String id;
    private String refreshmentPointId;
    private String agreementData;
    private ConventionStatus status;

    public Convention(String id, String refreshmentPointId, String agreementData, ConventionStatus status) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.agreementData = agreementData;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public String getAgreementData() {
        return agreementData;
    }

    public ConventionStatus getStatus() {
        return status;
    }

    /**
     * Activates the convention by setting its status to ACTIVE.
     */
    public void activate() {
        this.status = ConventionStatus.ACTIVE;
    }

    /**
     * Validates the agreement data.
     * In a real application, this would contain business logic.
     * For this example, we assume data is valid if it's not null and not empty.
     * @return true if data is valid, false otherwise.
     */
    public boolean validateAgreementData() {
        return agreementData != null && !agreementData.trim().isEmpty();
    }

    /**
     * Creates a DTO (Data Transfer Object) from this Convention instance.
     * @return a ConventionFormDTO populated with this convention's data.
     */
    public ConventionFormDTO toDTO() {
        return new ConventionFormDTO(this);
    }
}