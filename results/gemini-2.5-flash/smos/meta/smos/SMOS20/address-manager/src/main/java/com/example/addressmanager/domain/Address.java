package com.example.addressmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Represents an Address entity in the system.
 * This class is mapped to a database table using JPA.
 */
@Entity
@Table(name = "addresses") // Specifies the table name in the database
@Getter // Lombok annotation to generate all getters
@Setter // Lombok annotation to generate all setters
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@ToString // Lombok annotation to generate a toString method
public class Address {

    /**
     * Unique identifier for the address.
     * Marked as the primary key and configured for auto-increment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The street name and number of the address.
     */
    private String street;

    /**
     * The city of the address.
     */
    private String city;

    /**
     * The zip code or postal code of the address.
     */
    private String zipCode;

    /**
     * The country of the address.
     */
    private String country;

    // Lombok automatically generates getters, setters, no-arg constructor, all-arg constructor, and toString.
    // For example, getId(), setId(Long id), getStreet(), setStreet(String street), etc.
}