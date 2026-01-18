package com.address_management_system;

import java.util.Objects;

/**
 * Represents an address entity within the system.
 * An address is uniquely identified by its name.
 */
public class Address {
    private final String name;

    /**
     * Constructs a new Address with the specified name.
     *
     * @param name The name of the address. Must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Address(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Address name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    /**
     * Returns the name of the address.
     *
     * @return The name of the address.
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Address objects are considered equal if their names are equal (case-sensitive).
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return name.equals(address.name);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of the Address object.
     *
     * @return A string in the format "Address{name='[addressName]'}"
     */
    @Override
    public String toString() {
        return "Address{name='" + name + "'}";
    }
}