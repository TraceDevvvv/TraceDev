package com.example.domain;

import java.util.Date;

/**
 * Domain object representing a site.
 */
public class Site {
    private String name;
    private String path;
    private Date lastModified;

    /**
     * Default constructor.
     */
    public Site() {}

    /**
     * Constructs a Site with the specified parameters.
     * @param name the site name
     * @param path the site path
     * @param lastModified the last modified date
     */
    public Site(String name, String path, Date lastModified) {
        this.name = name;
        this.path = path;
        this.lastModified = lastModified;
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
        return "Site{" +
               "name='" + name + '\'' +
               ", path='" + path + '\'' +
               ", lastModified=" + lastModified +
               '}';
    }
}