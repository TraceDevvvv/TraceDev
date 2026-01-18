package com.example.banner;

/**
 * Banner.java
 * Represents a banner advertisement with an identifier and image path.
 * This class is a simple POJO (Plain Old Java Object) to hold banner data.
 */
public class Banner {
    private String id;          // Unique identifier for the banner
    private String imagePath;   // Path or URL to the banner image

    /**
     * Default constructor.
     */
    public Banner() {
        // Empty constructor for flexibility
    }

    /**
     * Parameterized constructor to create a banner with initial values.
     *
     * @param id        Unique identifier for the banner
     * @param imagePath Path or URL to the banner image
     */
    public Banner(String id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    /**
     * Get the banner's unique identifier.
     *
     * @return banner id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the banner's unique identifier.
     *
     * @param id new banner id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the path or URL to the banner image.
     *
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Set the path or URL to the banner image.
     *
     * @param imagePath new image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Returns a string representation of the banner for debugging.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}