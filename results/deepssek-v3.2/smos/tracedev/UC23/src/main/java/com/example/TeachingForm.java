package com.example;

/**
 * Represents a form for entering teaching data.
 */
public class TeachingForm {
    private String teachingName;

    /**
     * Constructor for TeachingForm.
     */
    public TeachingForm() {
        this.teachingName = "";
    }

    /**
     * Gets the teaching name from the form.
     * @return the teaching name
     */
    public String getTeachingName() {
        return teachingName;
    }

    /**
     * Sets the teaching name in the form.
     * @param name the teaching name to set
     */
    public void setTeachingName(String name) {
        this.teachingName = name;
    }

    /**
     * Checks if the form is filled.
     * @return true if teaching name is not empty
     */
    public boolean isFilled() {
        return teachingName != null && !teachingName.trim().isEmpty();
    }
}