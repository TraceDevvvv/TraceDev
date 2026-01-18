package com.tourism.domain;

/**
 * Domain entity representing a Site.
 * Includes location as required by REQ-003.
 */
public class Site {
    private String siteId;
    private String name;
    private String location;

    public Site() {
    }

    public Site(String siteId, String name, String location) {
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
}