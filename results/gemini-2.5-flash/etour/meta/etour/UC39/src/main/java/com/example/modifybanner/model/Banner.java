package com.example.modifybanner.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a Banner entity in the system.
 * This class is mapped to a database table using JPA.
 * It includes fields for banner identification, associated restaurant,
 * image path, and timestamps for creation and last modification.
 */
@Entity
@Table(name = "banners")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the banner

    @Column(nullable = false)
    private Long restaurantId; // Identifier for the associated restaurant

    @Column(nullable = false)
    private String name; // Name of the banner

    @Column(nullable = false)
    private String imagePath; // Path or URL to the banner image

    @Column(nullable = false)
    private String imageUrl; // Publicly accessible URL of the banner image

    @Column(nullable = false)
    private LocalDateTime createdAt; // Timestamp when the banner was created

    @Column(nullable = false)
    private LocalDateTime updatedAt; // Timestamp when the banner was last updated

    /**
     * Pre-persist method to set creation and update timestamps before saving.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Pre-update method to set the update timestamp before updating.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}