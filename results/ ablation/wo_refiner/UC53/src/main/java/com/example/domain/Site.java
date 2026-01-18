package com.example.domain;

/**
 * Represents a Site entity from the domain.
 */
public class Site {
    private String siteId;
    private String siteName;

    public Site(String siteId, String siteName) {
        this.siteId = siteId;
        this.siteName = siteName;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }
}