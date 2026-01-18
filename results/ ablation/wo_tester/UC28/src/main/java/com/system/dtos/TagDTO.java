package com.system.dtos;

import com.system.entities.Tag;

/**
 * Data Transfer Object for Tag entities.
 */
public class TagDTO {
    private String id;
    private String name;
    private String description;

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.description = tag.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}