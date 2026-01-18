package com.example.application.interfaces;

import com.example.domain.Site;
import com.example.domain.SearchCriteria;
import java.util.List;

/**
 * Repository interface for site data operations.
 */
public interface ISiteRepository {
    List<Site> findByCriteria(SearchCriteria criteria);
    List<Site> findByLocation(double lat, double lon, double radius);
    Site save(Site site);
    void delete(String id);
}