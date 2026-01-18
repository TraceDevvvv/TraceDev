package dto;

/**
 * Request DTO for deleting a banner.
 * Contains necessary identifiers for the operation.
 */
public class DeleteBannerRequest {
    private String bannerId;
    private String operatorId;
    private String pointOfRestId;

    public DeleteBannerRequest(String bannerId, String operatorId, String pointOfRestId) {
        this.bannerId = bannerId;
        this.operatorId = operatorId;
        this.pointOfRestId = pointOfRestId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }
}