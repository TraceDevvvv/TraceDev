package com.example.service;

import com.example.entity.Banner;

/**
 * Interface for backup service.
 */
public interface BackupService {
    /**
     * Backs up banner data.
     */
    void backupBannerData(Banner banner);
}