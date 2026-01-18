package com.example.justification;

/**
 * Data Transfer Object for Justification.
 */
public class JustificationDTO {
    private int justificationId;
    private String reason;
    private int adminId;

    // Default constructor
    public JustificationDTO() {
    }

    public JustificationDTO(int justificationId, String reason, int adminId) {
        this.justificationId = justificationId;
        this.reason = reason;
        this.adminId = adminId;
    }

    // Getters and setters
    public int getJustificationId() {
        return justificationId;
    }

    public void setJustificationId(int justificationId) {
        this.justificationId = justificationId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Convert a Justification entity to a JustificationDTO.
     * Assumption: adminId is mapped from createdBy field.
     */
    public static JustificationDTO fromEntity(Justification justification) {
        if (justification == null) {
            return null;
        }
        JustificationDTO dto = new JustificationDTO();
        dto.setJustificationId(justification.getId());
        dto.setReason(justification.getReason());
        dto.setAdminId(justification.getCreatedBy());
        return dto;
    }
}