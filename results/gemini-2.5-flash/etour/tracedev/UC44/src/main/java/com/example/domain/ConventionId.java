package com.example.domain;

import java.util.Objects;

/**
 * Value object representing a unique identifier for a Convention.
 * Encapsulates the ID value and provides equality based on its value.
 */
public class ConventionId {
    private String value;

    /**
     * Constructs a ConventionId with the given string value.
     *
     * @param value The string representation of the convention ID.
     */
    public ConventionId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ConventionId value cannot be null or empty.");
        }
        this.value = value;
    }

    /**
     * Returns the string value of this ConventionId.
     *
     * @return The string value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Compares this ConventionId to the specified object.
     * The result is true if and only if the argument is not null and is a ConventionId object
     * that contains the same string value as this object.
     *
     * @param o The object to compare this ConventionId against.
     * @return true if the given object represents a ConventionId equivalent to this ConventionId, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConventionId that = (ConventionId) o;
        return Objects.equals(value, that.value);
    }

    /**
     * Returns a hash code for this ConventionId.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Returns a string representation of this ConventionId.
     *
     * @return The string value of the ID.
     */
    @Override
    public String toString() {
        return value;
    }
}