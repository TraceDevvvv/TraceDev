package com.system.entities;

/**
 * Represents a request from an agency to add a banner.
 * Entry Condition: Agency intent.
 */
public class BannerRequest {
    private String agencyId;
    private String conventionId;
    private Banner bannerDetails;

    public BannerRequest(String agencyId, String conventionId, Banner bannerDetails) {
        this.agencyId = agencyId;
        this.conventionId = conventionId;
        this.bannerDetails = bannerDetails;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public String getConventionId() {
        return conventionId;
    }

    public Banner getBannerDetails() {
        return bannerDetails;
    }
}