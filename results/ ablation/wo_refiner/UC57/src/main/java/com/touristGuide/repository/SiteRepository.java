package com.touristGuide.repository;

import com.touristGuide.model.SearchCriteria;
import com.touristGuide.model.Site;
import java.util.List;

public interface SiteRepository {
    List<Site> findByCriteria(SearchCriteria criteria);
}