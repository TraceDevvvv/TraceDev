package com.tourism.dto;

/**
 * Data Transfer Object for Site information.
 * Required by REQ-005.
 */
public class SiteDTO {
    private String siteId;
    private String name;
    private String location;

    public SiteDTO() {
    }

    public SiteDTO(String siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}