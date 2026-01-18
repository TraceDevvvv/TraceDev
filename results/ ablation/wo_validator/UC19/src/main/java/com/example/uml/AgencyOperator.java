package com.example.uml;

import java.util.List;

/**
 * Represents an Agency Operator, a specialized User who can manage banners.
 */
public class AgencyOperator extends User {
    private String operatorId;
    private String agencyCode;

    public AgencyOperator(String userId, String username, String operatorId, String agencyCode) {
        super(userId, username);
        this.operatorId = operatorId;
        this.agencyCode = agencyCode;
    }

    public RefreshmentPoint selectRefreshmentPoint(String refreshmentPointId) {
        // Simulate selection logic; in a real system, this would fetch from a service.
        System.out.println("Operator selected refreshment point: " + refreshmentPointId);
        return new RefreshmentPoint(refreshmentPointId, "Sample Location", "Sample Point");
    }

    public List<Banner> viewBanners() {
        // Simulate fetching banners; actual implementation would delegate to a controller.
        System.out.println("Operator viewing banners.");
        return List.of();
    }

    public Banner selectBanner(String bannerId) {
        // Simulate selection logic.
        System.out.println("Operator selected banner: " + bannerId);
        return new Banner(bannerId, "http://example.com/banner.png");
    }

    public boolean confirmDeletion() {
        // Simulate user confirmation; in a real UI, this would come from user input.
        System.out.println("Operator confirmed deletion.");
        return true;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}