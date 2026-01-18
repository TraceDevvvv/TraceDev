package com.example.model;

import java.util.Date;

/**
 * Represents a Site entity with basic properties.
 */
public class Site {
    private String siteId;
    private String siteName;
    private String filePath;
    private Date lastModified;

    public Site(String siteId, String siteName, String filePath, Date lastModified) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.filePath = filePath;
        this.lastModified = lastModified;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getFilePath() {
        return filePath;
    }

    public Date getLastModified() {
        return lastModified;
    }
}