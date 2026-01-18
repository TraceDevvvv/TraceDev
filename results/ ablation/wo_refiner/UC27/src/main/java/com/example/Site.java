package com.example;

import java.util.Date;

/**
 * Data class representing a Site.
 */
public class Site {
    private int id;
    private String name;
    private String path;
    private Date lastModified;

    public Site(int id, String name, String path, Date lastModified) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.lastModified = lastModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}