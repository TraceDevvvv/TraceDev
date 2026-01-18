package com.example.service;

import com.example.entity.Banner;

/**
 * Implementation of backup service.
 */
public class BackupServiceImpl implements BackupService {
    private StorageService backupStorage;

    public BackupServiceImpl(StorageService backupStorage) {
        this.backupStorage = backupStorage;
    }

    @Override
    public void backupBannerData(Banner banner) {
        System.out.println("Backing up banner data: " + banner.getBannerId());
        // In a real implementation, this would use StorageService to backup
    }
}