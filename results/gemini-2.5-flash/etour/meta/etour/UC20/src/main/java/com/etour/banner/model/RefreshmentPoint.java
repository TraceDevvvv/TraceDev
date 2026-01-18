package com.etour.banner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

/**
 * JPA entity representing a refreshment point.
 * This entity stores information about a refreshment point, including its unique ID,
 * name, and the maximum number of banners it can display.
 */
@Entity
@Table(name = "refreshment_points")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshmentPoint {

    /**
     * Unique identifier for the refreshment point.
     * Generated automatically by the persistence provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /**
     * The name of the refreshment point.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The maximum number of banners that can be associated with this refreshment point.
     */
    @Column(nullable = false)
    private int maxBanners;
}