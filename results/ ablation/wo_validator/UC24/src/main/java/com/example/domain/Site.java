package com.example.domain;

import java.util.Objects;

/**
 * Represents a site/event location in the domain model.
 * Corresponds to the Site class in the Class Diagram.
 */
public class Site {
    private int siteId;
    private String name;
    private String address;

    public Site(int siteId, String name, String address) {
        this.siteId = siteId;
        this.name = name;
        this.address = address;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return siteId == site.siteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId);
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}