package com.touristGuide.usecase;

import com.touristGuide.model.SearchQuery;
import com.touristGuide.model.Site;
import java.util.List;

public interface AdvancedSearchUseCase {
    /**
     * <<trace>> execute method (requirement 5).
     */
    List<Site> execute(SearchQuery query);
}