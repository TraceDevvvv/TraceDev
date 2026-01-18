package com.example.repository;

import com.example.entities.Site;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Site entities.
 */
public interface SiteRepository {
    List<Site> findAll();
    Optional<Site> findById(int siteId);
}