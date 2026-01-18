package com.example.service;

import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Service interface for searching sites.
 */
public interface ISearchService {
    List<SiteDTO> searchSites(SearchCriteriaDTO criteria);
}