package com.example.app.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a class entity in the domain layer.
 * This class holds the core business data for a class.
 * Renamed from 'Class' to 'ArchivedClass' to avoid conflict with Java's reserved keyword.
 */
public class ArchivedClass {
    public String id;
    public String name;
    public String description;
    public Date createdDate;
    public Date lastModifiedDate;

    /**
     * Constructs a new ArchivedClass.
     *
     * @param id A unique identifier for the class.
     * @param name The name of the class.
     * @param description A brief description of the class.
     * @param createdDate The date when the class was created.
     * @param lastModifiedDate The date when the class was last modified.
     */
    public ArchivedClass(String id, String name, String description, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Standard getters and setters (or public fields as per UML)
    // The UML specifies public fields directly, so no explicit getters/setters are needed for them.

    @Override
    public String toString() {
        return "ArchivedClass{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", createdDate=" + createdDate +
               ", lastModifiedDate=" + lastModifiedDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchivedClass that = (ArchivedClass) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}