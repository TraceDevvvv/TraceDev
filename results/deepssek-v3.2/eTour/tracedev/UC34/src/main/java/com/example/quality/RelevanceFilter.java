package com.example.quality;

import com.example.domain.CulturalSite;
import com.example.application.SearchCriteria;
import java.util.List;

/**
 * Interface for relevance filtering and ranking.
 */
public interface RelevanceFilter {
    double calculateRelevance(CulturalSite site, SearchCriteria criteria);
    List<CulturalSite> filterAndRank(List<CulturalSite> sites, SearchCriteria criteria);
}