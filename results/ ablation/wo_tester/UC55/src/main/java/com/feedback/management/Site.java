// Entity representing a site that can receive feedback.
package com.feedback.management;

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
}