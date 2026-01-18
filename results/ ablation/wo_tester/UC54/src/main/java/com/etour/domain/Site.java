package com.etour.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Domain entity representing a tourist site.
 */
public class Site {
    private String id;
    private String name;
    private String location;

    public Site(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    /**
     * Returns a map with site details as specified in the sequence diagram.
     * @return Map containing site details.
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("id", id);
        details.put("name", name);
        details.put("location", location);
        return details;
    }
}