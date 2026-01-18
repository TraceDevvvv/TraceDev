package com.example.dto;

/**
 * DTO for change banner image request.
 */
public class ChangeBannerImageRequest implements DataTransferObject {
    private String agencyOperatorId;
    private String restPointId;
    private String bannerId;
    private String selectedImagePath;

    public ChangeBannerImageRequest(String agencyOperatorId, String restPointId, 
                                   String bannerId, String selectedImagePath) {
        this.agencyOperatorId = agencyOperatorId;
        this.restPointId = restPointId;
        this.bannerId = bannerId;
        this.selectedImagePath = selectedImagePath;
    }

    public String getAgencyOperatorId() {
        return agencyOperatorId;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }
}