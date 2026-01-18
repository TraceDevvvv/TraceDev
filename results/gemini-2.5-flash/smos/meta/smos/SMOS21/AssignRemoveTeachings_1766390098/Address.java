// Address.java
package com.example.teachingsystem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an address in the system, which can have one or more teachings associated with it.
 */
public class Address {
    private String addressId;
    private String name;
    private Set<Teaching> associatedTeachings; // Using a Set to ensure unique teachings and efficient lookups

    /**
     * Constructs a new Address instance.
     *
     * @param addressId A unique identifier for the address.
     * @param name The name of the address (e.g., "Main Campus", "Branch Office A").
     * @throws IllegalArgumentException if addressId or name is null or empty.
     */
    public Address(String addressId, String name) {
        if (addressId == null || addressId.trim().isEmpty()) {
            throw new IllegalArgumentException("Address ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Address name cannot be null or empty.");
        }
        this.addressId = addressId;
        this.name = name;
        this.associatedTeachings = new HashSet<>();
    }

    /**
     * Returns the unique identifier of the address.
     *
     * @return The address's ID.
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * Returns the name of the address.
     *
     * @return The address's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a teaching to this address.
     *
     * @param teaching The Teaching object to be associated with this address.
     * @return true if the teaching was successfully added (i.e., it was not already present), false otherwise.
     * @throws IllegalArgumentException if the teaching is null.
     */
    public boolean addTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching to add cannot be null.");
        }
        return associatedTeachings.add(teaching);
    }

    /**
     * Removes a teaching from this address.
     *
     * @param teaching The Teaching object to be disassociated from this address.
     * @return true if the teaching was successfully removed (i.e., it was present), false otherwise.
     * @throws IllegalArgumentException if the teaching is null.
     */
    public boolean removeTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching to remove cannot be null.");
        }
        return associatedTeachings.remove(teaching);
    }

    /**
     * Returns an unmodifiable set of teachings currently associated with this address.
     *
     * @return A Set of Teaching objects.
     */
    public Set<Teaching> getAssociatedTeachings() {
        return Collections.unmodifiableSet(associatedTeachings);
    }

    /**
     * Provides a string representation of the Address object.
     *
     * @return A string containing the address's ID, name, and associated teachings.
     */
    @Override
    public String toString() {
        return "Address{" +
               "addressId='" + addressId + '\'' +
               ", name='" + name + '\'' +
               ", associatedTeachings=" + associatedTeachings.stream().map(Teaching::getName).collect(java.util.stream.Collectors.joining(", ")) +
               '}';
    }

    /**
     * Checks if this Address object is equal to another object.
     * Equality is based on the addressId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId.equals(address.addressId);
    }

    /**
     * Returns a hash code value for the Address object.
     * The hash code is based on the addressId.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}