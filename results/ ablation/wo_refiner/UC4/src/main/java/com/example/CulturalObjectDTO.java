package com.example;

// Data Transfer Object for cultural objects to be sent to the presentation layer
public class CulturalObjectDTO {
    private String id;
    private String name;
    private String description;
    private String category;

    public CulturalObjectDTO() {
    }

    public CulturalObjectDTO(String id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    // Constructor to create DTO from Entity
    public CulturalObjectDTO(CulturalObject culturalObject) {
        this.id = culturalObject.getId();
        this.name = culturalObject.getName();
        this.description = culturalObject.getDescription();
        this.category = culturalObject.getCategory();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CulturalObjectDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}