package com.example.model;

import java.util.Objects;

/**
 * Represents a site with an identifier and name.
 */
public class Site {
    private String id;
    private String name;

    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(id, site.id) && Objects.equals(name, site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Site{id='" + id + "', name='" + name + "'}";
    }
}