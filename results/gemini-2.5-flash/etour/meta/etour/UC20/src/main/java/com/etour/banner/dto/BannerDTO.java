package com.etour.banner.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for banner insertion requests.
 * This class encapsulates the data required to insert a new banner,
 * including the image file and the ID of the associated refreshment point.
 */
@Data
public class BannerDTO {
    /**
     * The ID of the refreshment point to which the banner will be associated.
     */
    private String refreshmentPointId;

    /**
     * The image file for the banner, typically received from a multipart form submission.
     */
    private MultipartFile image;
}