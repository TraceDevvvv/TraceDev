package com.example.repository;

import com.example.dto.SiteDTO;

/**
 * Interface for repository pattern.
 * Defines contract for fetching site data.
 */
public interface ISiteRepository {
    SiteDTO getSiteById(String id);
}