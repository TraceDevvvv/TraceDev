package com.example.domain;

/**
 * Represents a tourist entity.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String email;
    private String otherAttributes;

    public Tourist(String touristId, String name, String email, String otherAttributes) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
        this.otherAttributes = otherAttributes;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setOtherAttributes(String attrs) {
        this.otherAttributes = attrs;
    }

    public String getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", otherAttributes='" + otherAttributes + '\'' +
                '}';
    }
}