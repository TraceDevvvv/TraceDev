package com.etour.banner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA entity representing a banner.
 * Each banner is associated with a specific refreshment point and stores
 * information about its image URL and upload date.
 */
@Entity
@Table(name = "banners")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    /**
     * Unique identifier for the banner.
     * Generated automatically by the persistence provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /**
     * The ID of the refreshment point to which this banner belongs.
     * This establishes a many-to-one relationship conceptually, though
     * it's stored as a String for simplicity and to avoid direct JPA
     * relationship mapping complexities with a potentially external
     * RefreshmentPoint entity if it were in a different service.
     */
    @Column(nullable = false)
    private String refreshmentPointId;

    /**
     * The URL or path where the banner image is stored and can be accessed.
     */
    @Column(nullable = false)
    private String imageUrl;

    /**
     * The timestamp when the banner was uploaded.
     */
    @Column(nullable = false)
    private LocalDateTime uploadDate;
}