package com.example.repository;

import com.example.model.SearchCriteria;
import com.example.model.Location;
import com.example.model.Site;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Interface for site repository operations.
 */
public interface SiteRepository {
    List<Site> findByCriteria(SearchCriteria criteria, Location location) throws ConnectionException;
}