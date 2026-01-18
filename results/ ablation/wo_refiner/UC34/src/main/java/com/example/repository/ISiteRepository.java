package com.example.repository;

import com.example.dto.SearchCriteriaDTO;
import com.example.model.Site;
import java.util.List;

/**
 * Repository interface for Site entities.
 */
public interface ISiteRepository {
    List<Site> findByCriteria(SearchCriteriaDTO criteria);
}