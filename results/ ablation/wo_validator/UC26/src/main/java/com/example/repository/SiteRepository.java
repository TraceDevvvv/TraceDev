package com.example.repository;

import com.example.model.Site;
import java.util.List;

/**
 * Interface for Site data access operations.
 */
public interface SiteRepository {
    List<Site> findAll();
    Site findById(int id);
}