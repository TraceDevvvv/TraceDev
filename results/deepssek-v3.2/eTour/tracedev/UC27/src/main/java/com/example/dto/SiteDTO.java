package com.example.dto;

import com.example.domain.Site;
import java.util.Date;

/**
 * Data transfer object for Site.
 * Used to transfer site data to the presentation layer.
 */
public class SiteDTO {
    private String name;
    private String path;
    private Date lastModified;

    /**
     * Default constructor.
     */
    public SiteDTO() {}

    /**
     * Constructs a SiteDTO from a Site domain object.
     * @param site the site domain object
     */
    public SiteDTO(Site site) {
        this.name = site.getName();
        this.path = site.getPath();
        this.lastModified = site.getLastModified();
    }

    /**
     * Gets the site name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the site name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the site path.
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the site path.
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the last modified date.
     * @return the last modified date
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Sets the last modified date.
     * @param lastModified the last modified date
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
               "name='" + name + '\'' +
               ", path='" + path + '\'' +
               ", lastModified=" + lastModified +
               '}';
    }
}