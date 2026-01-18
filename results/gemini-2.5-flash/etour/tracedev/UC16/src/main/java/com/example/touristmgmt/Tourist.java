package com.example.touristmgmt;

/**
 * Represents a Tourist entity with basic personal information.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String email;

    /**
     * Constructs a new Tourist object.
     *
     * @param touristId The unique identifier for the tourist.
     * @param name The name of the tourist.
     * @param email The email address of the tourist.
     */
    public Tourist(String touristId, String name, String email) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the unique ID of the tourist.
     * @return The tourist's ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the name of the tourist.
     * @return The tourist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the tourist.
     * @return The tourist's email.
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "touristId='" + touristId + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}