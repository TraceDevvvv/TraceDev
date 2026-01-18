package com.example.application;

/**
 * Command DTO for deleting a banner.
 */
public class DeleteBannerCommand {
    private String bannerId;
    private String agencyOperatorId;
    
    public DeleteBannerCommand(String bannerId, String agencyOperatorId) {
        this.bannerId = bannerId;
        this.agencyOperatorId = agencyOperatorId;
    }
    
    public String getBannerId() {
        return bannerId;
    }
    
    public String getAgencyOperatorId() {
        return agencyOperatorId;
    }
}