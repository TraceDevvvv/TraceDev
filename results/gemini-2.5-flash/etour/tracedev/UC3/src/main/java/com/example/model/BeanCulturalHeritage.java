package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for Cultural Heritage.
 * Represents a cultural heritage object with its details.
 * Implements Serializable for potential use in RMI.
 */
public class BeanCulturalHeritage implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private int id;
    private String name;
    private String city;
    private String phone;
    private String description;
    private String street;
    private String cap; // Postal code
    private String province;
    private Date openingTime;
    private Date closingTime;
    private String closingDay; // e.g., "Monday"
    private String location; // e.g., "Museum A, Room B"

    /**
     * Default constructor.
     */
    public BeanCulturalHeritage() {
        // Default constructor
    }

    /**
     * Full constructor for BeanCulturalHeritage.
     * @param id Unique identifier for the cultural heritage.
     * @param name Name of the cultural heritage.
     * @param city City where the cultural heritage is located.
     * @param phone Contact phone number.
     * @param description Brief description.
     * @param street Street address.
     * @param cap Postal code.
     * @param province Province where the cultural heritage is located.
     * @param openingTime Opening time.
     * @param closingTime Closing time.
     * @param closingDay Day of the week when it's closed.
     * @param location Specific location details (e.g., floor, room).
     */
    public BeanCulturalHeritage(int id, String name, String city, String phone, String description,
                                String street, String cap, String province, Date openingTime,
                                Date closingTime, String closingDay, String location) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.description = description;
        this.street = street;
        this.cap = cap;
        this.province = province;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.closingDay = closingDay;
        this.location = location;
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public String getClosingDay() {
        return closingDay;
    }

    public void setClosingDay(String closingDay) {
        this.closingDay = closingDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanCulturalHeritage that = (BeanCulturalHeritage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BeanCulturalHeritage{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", city='" + city + '\'' +
               '}';
    }
}