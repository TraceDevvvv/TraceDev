package com.etour.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for banner upload requests.
 * This object encapsulates the data required to insert a new banner,
 * typically received from the frontend.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class BannerUploadRequest {

    private String pointOfRestId; // The ID of the Point of Rest to associate the banner with
    private MultipartFile imageFile; // The actual image file to be uploaded
}