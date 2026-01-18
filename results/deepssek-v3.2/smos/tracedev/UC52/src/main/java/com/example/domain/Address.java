package com.example.domain;

/**
 * Represents an Address entity in the domain.
 * Implements SearchableEntity to allow searching.
 */
public class Address implements SearchableEntity {
    private Long id;
    private String street;
    private String city;
    private String country;
    private String searchableContent;

    // Constructors
    public Address() {
    }

    public Address(Long id, String street, String city, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.searchableContent = generateSearchableContent();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.searchableContent = generateSearchableContent();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        this.searchableContent = generateSearchableContent();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        this.searchableContent = generateSearchableContent();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        this.searchableContent = generateSearchableContent();
    }

    // SearchableEntity implementation
    @Override
    public String getSearchableContent() {
        if (searchableContent == null) {
            searchableContent = generateSearchableContent();
        }
        return searchableContent;
    }

    /**
     * Generates the searchable content string from relevant fields.
     * @return concatenated searchable fields.
     */
    private String generateSearchableContent() {
        return (street != null ? street : "") + " " +
               (city != null ? city : "") + " " +
               (country != null ? country : "");
    }
}