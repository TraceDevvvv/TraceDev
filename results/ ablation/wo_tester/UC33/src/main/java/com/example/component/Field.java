package com.example.component;

/**
 * Represents a field in a registration form.
 */
public class Field {
    private String name;
    private String type;
    private String value;

    // Constructors
    public Field() {}

    public Field(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Validates the field value.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        // Simplified validation: non-null and non-empty for demonstration
        return value != null && !value.trim().isEmpty();
    }
}