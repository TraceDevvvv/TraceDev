'''
Represents a cultural good with its unique identifier, name, description, location, and type.
This class serves as the data model for the application.
'''
package com.chatdev.culturalviewer.model;
/**
 * '''
 * Represents a cultural good with its unique identifier, name, description, location, and type.
 * This class serves as the data model for the application.
 * '''
 */
public class BeneCulturale {
    private String id;
    private String name;
    private String description;
    private String location;
    private String type;
    /**
     * '''
     * Constructs a new BeneCulturale object.
     *
     * @param id The unique identifier of the cultural good.
     * @param name The name of the cultural good.
     * @param description A detailed description of the cultural good.
     * @param location The physical location where the cultural good can be found or is related to.
     * @param type The classification or type of the cultural good (e.g., "Painting", "Sculpture", "Historic Site").
     * '''
     */
    public BeneCulturale(String id, String name, String description, String location, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
    }
    /**
     * '''
     * Returns the unique identifier of the cultural good.
     *
     * @return The ID of the cultural good.
     * '''
     */
    public String getId() {
        return id;
    }
    /**
     * '''
     * Returns the name of the cultural good.
     *
     * @return The name of the cultural good.
     * '''
     */
    public String getName() {
        return name;
    }
    /**
     * '''
     * Returns the description of the cultural good.
     *
     * @return The description of the cultural good.
     * '''
     */
    public String getDescription() {
        return description;
    }
    /**
     * '''
     * Returns the location of the cultural good.
     *
     * @return The location of the cultural good.
     * '''
     */
    public String getLocation() {
        return location;
    }
    /**
     * '''
     * Returns the type of the cultural good.
     *
     * @return The type of the cultural good.
     * '''
     */
    public String getType() {
        return type;
    }
    /**
     * '''
     * Provides a string representation of the cultural good, primarily its name,
     * which is suitable for display in lists.
     *
     * @return A string representing the cultural good's name.
     * '''
     */
    @Override
    public String toString() {
        // This is useful for JList to display the name directly
        return name + " (ID: " + id + ")";
    }
}