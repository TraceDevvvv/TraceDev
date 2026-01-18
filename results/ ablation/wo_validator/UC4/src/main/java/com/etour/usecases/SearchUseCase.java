package com.etour.usecases;

import com.etour.adapters.SearchQuery;
import com.etour.adapters.SearchResult;

/**
 * Use case port for searching cultural objects.
 * Part of the Use Cases layer (Application Serv) in Clean Architecture.
 */
public interface SearchUseCase {
    /**
     * Executes a search based on the provided query.
     * @param query the search criteria
     * @return the search result containing cultural objects and total count
     */
    SearchResult execute(SearchQuery query);
}