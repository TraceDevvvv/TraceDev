package com.example.ui.model;

/**
 * Data transfer object for form input.
 */
public class FormData {
    private String name;
    private String description;
    private String type; // String representation of heritage type.
    private String location;
    
    public FormData(String name, String description, String type, String location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void formData(String name, String description, String type, String location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }
}