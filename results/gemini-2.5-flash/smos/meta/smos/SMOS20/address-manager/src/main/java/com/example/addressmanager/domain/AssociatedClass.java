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
 * Represents an AssociatedClass entity in the system.
 * This class is used to track associations with addresses.
 * It is mapped to a database table using JPA.
 */
@Entity
@Table(name = "associated_classes") // Specifies the table name in the database
@Getter // Lombok annotation to generate all getters
@Setter // Lombok annotation to generate all setters
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@ToString // Lombok annotation to generate a toString method
public class AssociatedClass {

    /**
     * Unique identifier for the associated class.
     * Marked as the primary key and configured for auto-increment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name or description of the associated class.
     */
    private String name;

    /**
     * The ID of the Address to which this class is associated.
     * This acts as a foreign key reference to the Address entity.
     */
    private Long addressId;

    // Lombok automatically generates getters, setters, no-arg constructor, all-arg constructor, and toString.
    // For example, getId(), setId(Long id), getName(), setName(String name), getAddressId(), setAddressId(Long addressId).
}