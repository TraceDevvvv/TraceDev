package com.example.data;

import com.example.domain.Site;
import java.util.List;

/**
 * Repository interface for Site data access.
 * Part of the Data Access Layer in the Class Diagram.
 */
public interface SiteRepository {
    List<Site> findAll();
    Site findById(int id);
}