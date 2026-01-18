package com.example.model;

import java.util.Date;

/**
 * Represents a child in the system.
 */
public class Child {
    private int childId;
    private String name;
    private int parentId;
    private Date dateOfBirth;

    public Child(int childId, String name, int parentId, Date dateOfBirth) {
        this.childId = childId;
        this.name = name;
        this.parentId = parentId;
        this.dateOfBirth = dateOfBirth;
    }

    public int getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}