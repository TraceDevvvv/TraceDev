package com.example.application;

import com.example.dto.ChangeBannerImageRequest;
import com.example.dto.ChangeBannerImageResponse;

/**
 * Use case interface for changing banner image.
 */
public interface ChangeBannerImageUseCase {
    ChangeBannerImageResponse execute(ChangeBannerImageRequest request);
}