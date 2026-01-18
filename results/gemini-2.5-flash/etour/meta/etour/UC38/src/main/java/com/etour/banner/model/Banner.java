package com.etour.banner.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * JPA entity representing a banner.
 * This class maps to a 'banners' table in the database and stores
 * all relevant information about a banner, including its association
 * with a Point of Rest.
 */
@Entity
@Table(name = "banners")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Banner {

    @Id
    private String id; // Unique identifier for the banner

    @Column(name = "point_of_rest_id", nullable = false)
    private String pointOfRestId; // ID of the Point of Rest this banner is associated with

    @Column(name = "image_url", nullable = false)
    private String imageUrl; // URL or path where the banner image is stored

    @Column(name = "original_file_name")
    private String originalFileName; // Original name of the uploaded image file

    @Column(name = "file_type")
    private String fileType; // MIME type of the image file (e.g., image/jpeg)

    @Column(name = "file_size")
    private long fileSize; // Size of the image file in bytes

    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate; // Timestamp when the banner was uploaded
}