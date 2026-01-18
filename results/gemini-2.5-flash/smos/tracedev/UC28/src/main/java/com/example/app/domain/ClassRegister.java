package com.example.app.domain;

import com.example.app.Domain;
import java.util.Date;

/**
 * Represents a Class Register in the domain.
 */
public class ClassRegister implements Domain {
    public String id;
    public String name;
    public String description;
    public Date creationDate;

    /**
     * Constructs a new ClassRegister.
     * @param id The unique identifier for the register.
     * @param name The name of the register.
     * @param description A description of the register.
     * @param creationDate The date the register was created.
     */
    public ClassRegister(String id, String name, String description, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }

    // Getters for public fields
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}